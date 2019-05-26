package com.mumayuan.simple.query.utils.reflection;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * DefaultObjectFactory.
 *
 * @author zjj
 */
public class DefaultObjectFactory implements ObjectFactory {

  @Override
  public <T> T create(final Class<T> clazz) {
    return create(clazz, null, null);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(final Class<T> clazz, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    Class<?> c = convertInterface(clazz);
    Constructor<?> constructor;
    try {
      if (constructorArgTypes == null || constructorArgs == null) {
        constructor = c.getDeclaredConstructor();
//        if (!constructor.isAccessible()) {
//          constructor.setAccessible(true);
//        }
        return (T) constructor.newInstance();
      } else {
        constructor = c.getDeclaredConstructor(constructorArgTypes.toArray(new Class[0]));
//        if (!constructor.isAccessible()) {
//          constructor.setAccessible(true);
//        }
        return (T) constructor.newInstance(constructorArgs.toArray());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 转换类对象.
   *
   * @param clazz 类对象
   * @return 类对象
   */
  protected Class<?> convertInterface(Class<?> clazz) {
    Class<?> c;
    if (clazz == List.class || clazz == Collection.class || clazz == Iterable.class) {
      c = ArrayList.class;
    } else if (clazz == Map.class) {
      c = HashMap.class;
    } else if (clazz == SortedSet.class) {
      c = TreeSet.class;
    } else if (clazz == Set.class) {
      c = HashSet.class;
    } else {
      c = clazz;
    }
    return c;
  }
}
