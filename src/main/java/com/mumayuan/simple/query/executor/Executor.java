package com.mumayuan.simple.query.executor;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.Sql;
import java.util.List;

/**
 * Executor.
 *
 * @author zjj
 */
public interface Executor {

  /**
   * 新增.
   *
   * @param <T> 泛型
   * @param obj T对象
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int insert(T obj) throws Exception;

  /**
   * 删除.
   *
   * @param <T> 泛型
   * @param obj T对象
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int delete(T obj) throws Exception;

  /**
   * 修改.
   *
   * @param <T> 泛型
   * @param obj T对象
   * @return 行数
   * @throws Exception error
   */
  <T extends BaseEntity> int update(T obj) throws Exception;

  /**
   * 修改.
   *
   * @param sql sql对象
   * @return 行数
   * @throws Exception error
   */
  int update(Sql sql) throws Exception;

  /**
   * 查询
   *
   * @param <T> 泛型
   * @param clazz 类对象
   * @param sql sql对象
   * @return list
   * @throws Exception error
   */
  <T extends BaseEntity> List<T> query(Class<T> clazz, Sql sql) throws Exception;

  /**
   * 验证QuerySql对象.
   *
   * @param sql sql对象
   * @throws Exception error
   */
  void checkSql(Sql sql) throws Exception;
}
