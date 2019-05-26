package com.mumayuan.simple.query.utils.reflection;

import java.util.List;

/**
 * ObjectFactory.
 *
 * @author zjj
 */
public interface ObjectFactory {

  /**
   * 创建T对象
   *
   * @param clazz 类对象
   * @param <T> 泛型
   * @return T对象
   */
  <T> T create(Class<T> clazz);

  /**
   * 创建T对象
   *
   * @param clazz 类对象
   * @param constructorArgTypes 构造参数类型
   * @param constructorArgs 构造参数值
   * @param <T> 泛型
   * @return T对象
   */
  <T> T create(Class<T> clazz, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);
}
