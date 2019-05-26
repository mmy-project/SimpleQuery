package com.mumayuan.simple.query.jdbc.defaults;

import com.mumayuan.simple.query.enums.TransactionLevel;
import com.mumayuan.simple.query.jdbc.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * DefaultTransaction.
 *
 * @author zjj
 */
public class DefaultTransaction implements Transaction {

  private DataSource dataSource;
  private Connection connection;
  private TransactionLevel level;
  private boolean beginTransaction;

  public DefaultTransaction(final DataSource dataSource, final TransactionLevel level) {
    this.dataSource = dataSource;
    this.level = level;
  }

  public DefaultTransaction(final Connection connection) {
    this.connection = connection;
  }

  /**
   * 获取数据库连接.
   *
   * @return Connection
   * @throws SQLException error
   */
  @Override
  public Connection getConnection() throws SQLException {
    openConnection();
    return connection;
  }

  /**
   * 关闭连接.
   *
   * @throws SQLException error
   */
  @Override
  public void close() throws SQLException {
    if (connection != null && !isBeginTransaction()) {
      resetAutoCommit();
      connection.close();
    }
  }

  /**
   * 重置事务自动提交.
   *
   * @throws SQLException error
   */
  protected void resetAutoCommit() throws SQLException {
    if (connection != null) {
      beginTransaction = false;
      connection.setAutoCommit(true);
    }
  }

  /**
   * 打开数据库连接.
   *
   * @throws SQLException error
   */
  protected void openConnection() throws SQLException {
    if (connection == null) {
      connection = dataSource.getConnection();
      if (level != null) {
        connection.setTransactionIsolation(level.getLevel());
      }
    }
  }

  /**
   * 开启事务.
   *
   * @throws Exception error
   */
  @Override
  public void beginTransaction() throws Exception {
    if (connection == null) {
      openConnection();
    }
    beginTransaction = true;
    connection.setAutoCommit(false);
  }

  /**
   * 提交事务.
   *
   * @throws Exception error
   */
  @Override
  public void commitTransaction() throws Exception {
    connection.commit();
    resetAutoCommit();
  }

  /**
   * 回滚事务.
   *
   * @throws Exception error
   */
  @Override
  public void rollbackTransaction() throws Exception {
    connection.rollback();
    resetAutoCommit();
  }

  @Override
  public boolean isBeginTransaction() {
    return beginTransaction;
  }

  @Override
  public boolean isClosed() throws SQLException {
    return connection.isClosed();
  }
}
