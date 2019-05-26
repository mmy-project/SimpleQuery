package com.mumayuan.simple.query.mapper;

/**
 * Mapper构造器.
 *
 * @author zjj
 */
public interface MapperBuilder {

  /**
   * 构造Mapper对象.
   *
   * @param clazz 类对象
   * @return Mapper 对象
   * @throws Exception error
   */
  Mapper build(Class<?> clazz);

  /**
   * 构造Mapper对象.
   *
   * @param clazzName 全类名
   * @return Mapper 对象
   * @throws Exception error
   */
  default Mapper build(String clazzName) throws Exception {
    return null;
  }
}
