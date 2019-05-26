package com.mumayuan.simple.query.resulthandler;

import com.mumayuan.simple.query.BaseEntity;
import com.mumayuan.simple.query.mapper.Mapper;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 结果处理器.
 *
 * @author zjj
 */
public interface ResultSetHandler {

  /**
   * 获取结果.
   *
   * @param stmt Statement
   * @param clazz 类对象
   * @param mapper Mapper对象
   * @param <T> 泛型
   * @return list
   * @throws SQLException error
   */
  <T extends BaseEntity> List<T> handleResultSets(Class<T> clazz, Mapper mapper, Statement stmt) throws SQLException;
}
