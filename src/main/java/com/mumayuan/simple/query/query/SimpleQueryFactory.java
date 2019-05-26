package com.mumayuan.simple.query.query;

/**
 * 查询对象工厂.
 *
 * @author zjj
 */
public interface SimpleQueryFactory {

  /**
   * 获取操作对象.
   *
   * @return SimpleQuery
   * @throws Exception error
   */
  SimpleQuery createSimpleQuery() throws Exception;
}
