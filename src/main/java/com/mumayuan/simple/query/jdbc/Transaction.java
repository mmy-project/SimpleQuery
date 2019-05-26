package com.mumayuan.simple.query.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库事务.
 *
 * @author zjj
 */
public interface Transaction {

  /**
   * 获取数据库连接.
   *
   * @return Connection
   * @throws SQLException error
   */
  Connection getConnection() throws SQLException;

  /**
   * 关闭连接.
   *
   * @throws SQLException error
   */
  void close() throws SQLException;

  /**
   * 开启事务.
   *
   * @throws Exception error
   */
  void beginTransaction() throws Exception;

  /**
   * 提交事务.
   *
   * @throws Exception error
   */
  void commitTransaction() throws Exception;

  /**
   * 回滚事务.
   *
   * @throws Exception error
   */
  void rollbackTransaction() throws Exception;

  /**
   * 是否已开启事务.
   *
   * @return true/false
   */
  boolean isBeginTransaction();

  /**
   * 连接是否已关闭.
   *
   * @return true/false
   * @throws SQLException error
   */
  boolean isClosed() throws SQLException;
}
