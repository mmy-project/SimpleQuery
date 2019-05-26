package com.mumayuan.simple.query.resulthandler.defaults;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.mapper.FieldInfo;
import com.mumayuan.simple.query.mapper.Mapper;
import com.mumayuan.simple.query.resulthandler.ResultSetHandler;
import com.mumayuan.simple.query.utils.reflection.ObjectFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ResultSetHandler.
 *
 * @author zjj
 */
public class DefaultResultSetHandler implements ResultSetHandler {

  private ObjectFactory objectFactory;

  public DefaultResultSetHandler(final ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  @Override
  public <T extends BaseEntity> List<T> handleResultSets(final Class<T> clazz, final Mapper mapper,
      final Statement stmt) throws SQLException {
    List<T> list = new ArrayList<>();
    PreparedStatement preparedStatement = (PreparedStatement) stmt;
    preparedStatement.execute();
    ResultSet rs = stmt.getResultSet();
    Map<String, FieldInfo> fieldInfoMap = mapper.getFieldInfoMap();
    ResultSetMetaData md = rs.getMetaData();
    int columnCount = md.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
      String name = md.getColumnName(i);
      if (fieldInfoMap.containsKey(name)) {
        fieldInfoMap.get(name).setIndex(i);
      }
    }
    List<FieldInfo> fieldInfos = fieldInfoMap.values().stream()
        .filter(f -> f.getIndex() > 0 && f.getTypeHandler() != null).collect(Collectors.toList());
    try {
      while (rs.next()) {
        T o = objectFactory.create(clazz);
        if (o == null) {
          throw new SQLException("对象创建异常");
        }
        for (FieldInfo fieldInfo : fieldInfos) {
          Object val = fieldInfo.getTypeHandler().getResult(rs, fieldInfo.getIndex());
          if (fieldInfo.getMethod() != null) {
            Object[] params = {val};
            fieldInfo.getMethod().invoke(o, params);
          } else {
            fieldInfo.getField().set(o, val);
          }
        }
        list.add(o);
      }
    } catch (Exception e) {
      throw new SQLException("ResultSet解析异常:" + e.getMessage());
    }
    return list;
  }
}
