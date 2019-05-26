package com.mumayuan.simple.query.resulthandler.typehandler;

import com.mumayuan.simple.query.resulthandler.typehandler.defaults.DateTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.StringTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.BooleanTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.ByteTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.DoubleTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.FloatTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.IntegerTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.LongTypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.defaults.base.ShortTypeHandler;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类型处理器工厂.
 *
 * @author zjj
 */
public final class TypeHandlerFactory {

  private final Map<Class<?>, TypeHandler<?>> typeHandlerCache = new ConcurrentHashMap<>();

  public TypeHandlerFactory() {
    registerDefaultTypeHandler();
  }

  /**
   * 注册默认类型处理器.
   */
  private void registerDefaultTypeHandler() {
    // base
    registerTypeHandler(byte.class, new ByteTypeHandler());
    registerTypeHandler(Byte.class, new ByteTypeHandler());
    registerTypeHandler(short.class, new ShortTypeHandler());
    registerTypeHandler(Short.class, new ShortTypeHandler());
    registerTypeHandler(int.class, new IntegerTypeHandler());
    registerTypeHandler(Integer.class, new IntegerTypeHandler());
    registerTypeHandler(long.class, new LongTypeHandler());
    registerTypeHandler(Long.class, new LongTypeHandler());
    registerTypeHandler(float.class, new FloatTypeHandler());
    registerTypeHandler(Float.class, new FloatTypeHandler());
    registerTypeHandler(double.class, new DoubleTypeHandler());
    registerTypeHandler(Double.class, new DoubleTypeHandler());
    registerTypeHandler(boolean.class, new BooleanTypeHandler());
    registerTypeHandler(Boolean.class, new BooleanTypeHandler());

    // Object
    registerTypeHandler(String.class, new StringTypeHandler());
    registerTypeHandler(Date.class, new DateTypeHandler());
  }

  /**
   * 注册类型处理器.
   *
   * @param clazz 类型
   * @param typeHandler 类型处理器
   */
  public void registerTypeHandler(Class<?> clazz, TypeHandler<?> typeHandler) {
    typeHandlerCache.put(clazz, typeHandler);
  }

  /**
   * 创建TypeHandler.
   *
   * @param clazz Class
   * @return TypeHandler
   */
  public TypeHandler<?> getTypeHandler(Class<?> clazz) {
//    if (!typeHandlerCache.containsKey(clazz)) {
//      throw new Exception("类型解析器不存在");
//    }
    return typeHandlerCache.get(clazz);
  }
}
