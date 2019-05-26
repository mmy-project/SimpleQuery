package com.mumayuan.simple.query.query.defaults;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.Configuration;
import com.mumayuan.simple.query.executor.defaults.DefaultExecutor;
import com.mumayuan.simple.query.Sql;
import com.mumayuan.simple.query.jdbc.Transaction;
import com.mumayuan.simple.query.jdbc.defaults.DefaultTransaction;
import com.mumayuan.simple.query.executor.Executor;
import com.mumayuan.simple.query.query.SimpleQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultSimpleQuery .
 *
 * @author zjj
 */
public final class DefaultSimpleQuery implements SimpleQuery {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSimpleQuery.class);
  private Configuration configuration;
  private Transaction transaction;
  private Executor executor;
  private String dataSourceName;

  public DefaultSimpleQuery(final Configuration configuration, final String dataSourceName) throws Exception {
    this.configuration = configuration;
    this.dataSourceName = dataSourceName;
    this.transaction = new DefaultTransaction(configuration.getDataSource(dataSourceName), null);
    this.executor = new DefaultExecutor(this.configuration, this.transaction);
  }

  @Override
  public String getDataSourceName() {
    return dataSourceName;
  }

  @Override
  public Connection getConnection() throws Exception {
    return transaction.getConnection();
  }

  @Override
  public void beginTransaction() throws Exception {
    transaction.beginTransaction();
  }

  @Override
  public void commitTransaction() throws Exception {
    transaction.commitTransaction();
  }

  @Override
  public void rollbackTransaction() throws Exception {
    transaction.commitTransaction();
  }

  @Override
  public void close() {
    try {
      transaction.close();
    } catch (SQLException e) {
      LOGGER.error("连接池关闭失败", e);
    }
  }

  @Override
  public <T extends BaseEntity> List<T> select(final Class<T> clazz, final Sql sql) throws Exception {
    return executor.query(clazz, sql);
  }

  @Override
  public <T extends BaseEntity> int insert(final T obj) throws Exception {
    return executor.insert(obj);
  }

  @Override
  public int insert(final Sql sql) throws Exception {
    return executor.update(sql);
  }


  @Override
  public <T extends BaseEntity> int update(final T obj) throws Exception {
    return executor.update(obj);
  }

  @Override
  public int update(final Sql sql) throws Exception {
    return executor.update(sql);
  }


  @Override
  public <T extends BaseEntity> int delete(final T obj) throws Exception {
    return executor.delete(obj);
  }

  @Override
  public int delete(final Sql sql) throws Exception {
    return executor.update(sql);
  }
}
