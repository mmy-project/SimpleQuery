package com.mumayuan.simple.query.enums;

import java.sql.Connection;

/**
 * 事务隔离级别.
 *
 * @author zjj
 */
public enum TransactionLevel {
  NONE(Connection.TRANSACTION_NONE),
  READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
  READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
  REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
  SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

  private final int level;

  TransactionLevel(final int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }
}
