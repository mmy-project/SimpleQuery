package com.mumayuan.simple.query.executor;

import com.mumayuan.simple.query.Configuration;
import com.mumayuan.simple.query.Sql;
import com.mumayuan.simple.query.enums.OperationType;
import com.mumayuan.simple.query.jdbc.Transaction;
import com.mumayuan.simple.query.mapper.ColumnInfo;
import com.mumayuan.simple.query.mapper.FieldInfo;
import com.mumayuan.simple.query.mapper.Mapper;
import com.mumayuan.simple.query.resulthandler.ResultSetHandler;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AbsExecutor.
 *
 * @author zjj
 */
public abstract class AbsExecutor implements Executor {

  protected ResultSetHandler resultSetHandler;
  protected final Configuration configuration;
  protected Transaction transaction;

  public AbsExecutor(final Configuration configuration, final Transaction transaction) {
    this.configuration = configuration;
    this.transaction = transaction;
  }

  @Override
  public void checkSql(final Sql sql) throws SQLException {
    if (sql.getComment() == null) {
      throw new SQLException("comment对象不能为空");
    }
  }

  /**
   * 获取Mapper对象
   *
   * @param clazz 类对象
   * @return Mapper对象
   */
  protected Mapper getMapper(Class<?> clazz) {
    Mapper mapper = configuration.getMapper(clazz);
    if (mapper == null) {
      mapper = configuration.getMapperBuilder().build(clazz);
      if (mapper != null) {
        configuration.setMapper(clazz, mapper);
      }
    }
    return mapper;
  }

  /**
   * 获取sql
   *
   * @param handleType 操作类型
   * @param o 对象
   * @return sql
   * @throws SQLException error
   */
  protected Sql getAutoSql(final OperationType handleType, final Object o) throws SQLException {
    if (handleType == OperationType.SELECT) {
      return null;
    }
    if (o == null) {
      throw new SQLException("操作对象不能NULL");
    }
    Mapper mapper = getMapper(o.getClass());
    List<FieldInfo> fieldInfos = mapper.getFieldInfoMap().values().stream()
        .filter(FieldInfo::isTableColumn).collect(Collectors.toList());
    Sql sql = new Sql("simpleQuery", handleType.toString(), "getAutoSql", "autoSql");
    if (handleType == OperationType.INSERT) {
      insertSql(sql, mapper.getTableName(), fieldInfos, o);
    } else if (handleType == OperationType.DELETE) {
      deleteSql(sql, mapper.getTableName(), fieldInfos, o);
    } else {
      updateSql(sql, mapper.getTableName(), fieldInfos, o);
    }
    return sql;
  }

  /**
   * 生成Sql对象
   *
   * @param sql 对象
   * @param tbName 表名
   * @param fieldInfos 字段列表
   * @param o 对象
   */
  private void insertSql(final Sql sql, final String tbName, final List<FieldInfo> fieldInfos, final Object o) {
    List<ColumnVal> valList = getColumnValList(fieldInfos, o).stream()
        .filter(c -> !c.columnInfo.isPrimaryKey()).collect(Collectors.toList());
    StringBuilder sqlBuilder = new StringBuilder("insert into ").append(tbName);
    sqlBuilder.append(valList.stream().map(c -> c.columnInfo.getColumnName())
        .collect(Collectors.joining(",", "(", ")")));
    sqlBuilder.append(" values ");
    sqlBuilder.append(Collections.nCopies(valList.size(), "?").stream().collect(Collectors.joining(",", "(", ")")));
    sqlBuilder.append(";").append(System.lineSeparator());
    sql.appendSql(sqlBuilder.toString(), valList.stream().map(v -> v.data).collect(Collectors.toList()).toArray());
  }

  /**
   * 生成Sql对象
   *
   * @param sql 对象
   * @param tbName 表名
   * @param fieldInfos 字段列表
   * @param o 对象
   * @throws SQLException error
   */
  private void deleteSql(final Sql sql, final String tbName, final List<FieldInfo> fieldInfos, final Object o)
      throws SQLException {
    Optional<ColumnVal> primaryKeyOptional = getColumnValList(fieldInfos, o).stream()
        .filter(c -> c.columnInfo.isPrimaryKey()).findFirst();
    if (!primaryKeyOptional.isPresent()) {
      throw new SQLException("删除操作主键不能为空");
    }
    StringBuilder sqlBuilder = new StringBuilder("delete from ");
    sqlBuilder.append(tbName).append(" ");
    sqlBuilder.append("where ");
    sqlBuilder.append(primaryKeyOptional.get().columnInfo.getColumnName()).append("=?");
    sqlBuilder.append(";").append(System.lineSeparator());
    sql.appendSql(sqlBuilder.toString(), primaryKeyOptional.get().data);
  }

  /**
   * 生成Sql对象
   *
   * @param sql 对象
   * @param tbName 表名
   * @param fieldInfos 字段列表
   * @param o 对象
   * @throws SQLException error
   */
  private void updateSql(final Sql sql, final String tbName, final List<FieldInfo> fieldInfos, final Object o)
      throws SQLException {
    Optional<ColumnVal> primaryKeyOptional = getColumnValList(fieldInfos, o).stream()
        .filter(c -> c.columnInfo.isPrimaryKey()).findFirst();
    if (!primaryKeyOptional.isPresent()) {
      throw new SQLException("修改操作主键不能为空");
    }
    List<ColumnVal> valList = getColumnValList(fieldInfos, o).stream()
        .filter(c -> !c.columnInfo.isPrimaryKey()).collect(Collectors.toList());
    StringBuilder sqlBuilder = new StringBuilder("update ");
    sqlBuilder.append(tbName).append(" ");
    sqlBuilder.append("set ");
    sqlBuilder.append(valList.stream().map(c -> c.columnInfo.getColumnName())
        .collect(Collectors.joining("=?,", "", "=? ")));
    sqlBuilder.append("where ");
    sqlBuilder.append(primaryKeyOptional.get().columnInfo.getColumnName()).append("=?");
    sqlBuilder.append(";").append(System.lineSeparator());
    sql.appendSql(sqlBuilder.toString(), valList.stream().map(v -> v.data).collect(Collectors.toList()).toArray());
    sql.appendParams(primaryKeyOptional.get().data);
  }

  /**
   * 获取字段列表.
   *
   * @param fieldInfos List
   * @param o Object
   * @return List
   */
  private List<ColumnVal> getColumnValList(final List<FieldInfo> fieldInfos, Object o) {
    List<ColumnVal> list = new ArrayList<>();
    for (FieldInfo f : fieldInfos) {
      if (!f.isTableColumn()) {
        continue;
      }
      try {
        Object data = f.getField().get(o);
        if (data != null && f.getTypeHandler() != null) {
          list.add(new ColumnVal(f.getColumnInfo(), data));
        }
      } catch (Exception e) {
        // e.printStackTrace();
      }
    }
    return list;
  }

  /**
   * 获取 Statement 对象
   *
   * @param sql Sql
   * @return Statement
   * @throws SQLException error
   */
  public abstract Statement getStatement(Sql sql) throws SQLException;

  /**
   * 字段值.
   */
  class ColumnVal extends ColumnInfo {

    ColumnVal(final ColumnInfo columnInfo, final Object data) {
      this.columnInfo = columnInfo;
      this.data = data;
    }

    private ColumnInfo columnInfo;
    private Object data;
  }
}
