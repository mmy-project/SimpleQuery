package com.mumayuan.simple.query.resulthandler.typehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型处理器.
 *
 * @param <T> type
 * @author zjj
 */
public interface TypeHandler<T> {

  /**
   * 获取字段值.
   *
   * @param rs ResultSet
   * @param columnName 字段名
   * @return T
   * @throws SQLException error
   */
  T getResult(ResultSet rs, String columnName) throws SQLException;

  /**
   * 获取字段值.
   *
   * @param rs ResultSet
   * @param columnIndex 字段序号
   * @return T
   * @throws SQLException error
   */
  T getResult(ResultSet rs, int columnIndex) throws SQLException;

  /**
   * 字段默认值.
   *
   * @return T
   */
  T getDefault();
}
