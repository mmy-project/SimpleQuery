package com.mumayuan.simple.query.executor.defaults;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.Configuration;
import com.mumayuan.simple.query.Sql;
import com.mumayuan.simple.query.enums.OperationType;
import com.mumayuan.simple.query.executor.AbsExecutor;
import com.mumayuan.simple.query.jdbc.Transaction;
import com.mumayuan.simple.query.resulthandler.defaults.DefaultResultSetHandler;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultExecutor.
 *
 * @author zjj
 */
public class DefaultExecutor extends AbsExecutor {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExecutor.class);

  public DefaultExecutor(final Configuration configuration, final Transaction transaction) {
    super(configuration, transaction);
    resultSetHandler = new DefaultResultSetHandler(configuration.getObjectFactory());
  }

  @Override
  public <T extends BaseEntity> int insert(final T obj) throws Exception {
    Sql sql = getAutoSql(OperationType.INSERT, obj);
    return update(sql);
  }

  @Override
  public <T extends BaseEntity> int delete(final T obj) throws Exception {
    Sql sql = getAutoSql(OperationType.DELETE, obj);
    return update(sql);
  }

  @Override
  public <T extends BaseEntity> int update(final T obj) throws Exception {
    Sql sql = getAutoSql(OperationType.UPDATE, obj);
    return update(sql);
  }

  @Override
  public int update(final Sql sql) throws Exception {
    PreparedStatement stmt = (PreparedStatement) getStatement(sql);
    stmt.execute();
    return stmt.getUpdateCount();
  }

  @Override
  public <T extends BaseEntity> List<T> query(final Class<T> clazz, final Sql sql) throws Exception {
    return resultSetHandler.handleResultSets(clazz, getMapper(clazz), getStatement(sql));
  }

  @Override
  public Statement getStatement(final Sql sql) throws SQLException {
    checkSql(sql);
    String s = sql.toString();
    LOGGER.debug(s);
    PreparedStatement stmt = transaction.getConnection().prepareStatement(s);
    List<Object> params = sql.getParams();
    for (int i = 0; i < params.size(); i++) {
      stmt.setObject(i + 1, params.get(i));
    }
    return stmt;
  }
}
