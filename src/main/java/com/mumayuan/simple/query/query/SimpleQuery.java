package com.mumayuan.simple.query.query;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.Sql;
import java.io.Closeable;
import java.sql.Connection;
import java.util.List;

/**
 * 操作入口.
 *
 * @author zjj
 * @since JDK1.8
 */
public interface SimpleQuery extends Closeable {

  /**
   * 获取数据源名.
   *
   * @return 数据源名
   */
  String getDataSourceName();

  /**
   * 获取数据库连接.
   *
   * @return Connection
   * @throws Exception error
   */
  Connection getConnection() throws Exception;

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
   * 释放连接.
   *
   * @throws Exception exp
   */
  @Override
  void close();

  /**
   * 查询.
   *
   * @param <T> 泛型
   * @param clazz 类对象
   * @param sql sql对象
   * @return 集合
   * @throws Exception error
   */
  <T extends BaseEntity> List<T> select(Class<T> clazz, Sql sql) throws Exception;

  /**
   * 插入
   *
   * @param <T> 泛型
   * @param obj T
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int insert(T obj) throws Exception;

  /**
   * 插入
   *
   * @param sql Sql
   * @return 行数
   * @throws Exception error
   */
  int insert(Sql sql) throws Exception;

  /**
   * 批量插入
   *
   * @param <T> 泛型
   * @param list T
   * @return 行数
   * @throws Exception error
   */
  default <T extends BaseEntity> int insertBatch(List<T> list) throws Exception {
    return 0;
  }

  /**
   * 更新
   *
   * @param <T> 泛型
   * @param obj T
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int update(T obj) throws Exception;

  /**
   * 更新
   *
   * @param sql Sql
   * @return 行数
   * @throws Exception error
   */
  int update(Sql sql) throws Exception;

  /**
   * 批量更新
   *
   * @param <T> 泛型
   * @param list T
   * @return 行数
   * @throws Exception error
   */
  default <T extends BaseEntity> int updateBatch(List<T> list) throws Exception {
    return 0;
  }

  /**
   * 删除
   *
   * @param <T> 泛型
   * @param obj T
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int delete(T obj) throws Exception;

  /**
   * 删除
   *
   * @param sql Sql
   * @return 行数
   * @throws Exception error
   */
  int delete(Sql sql) throws Exception;

  /**
   * 批量删除
   *
   * @param <T> 泛型
   * @param list T
   * @return 行数
   * @throws Exception error
   */
  default <T extends BaseEntity> int deleteBatch(List<T> list) throws Exception {
    return 0;
  }
}
