package com.mumayuan.simple.query;

import com.mumayuan.simple.query.mapper.MapperBuilder;
import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import com.mumayuan.simple.query.utils.reflection.ObjectFactory;
import javax.sql.DataSource;

/**
 * 全局配置.
 *
 * @author zjj
 */
public class GlobalConfig {

  private static final Configuration GLOBAL_CONFIGURATION = new Configuration();

  /**
   * 获取全局配置.
   *
   * @return Configuration
   */
  public static Configuration getConfiguration() {
    return GLOBAL_CONFIGURATION;
  }

  /**
   * 添加数据源.
   *
   * @param name 数据源名
   * @param dataSource 数据源
   */
  public static void addDataSource(final String name, final DataSource dataSource) {
    GLOBAL_CONFIGURATION.addDataSource(name, dataSource);
  }

  /**
   * 注册类型处理器.
   *
   * @param clazz 类型
   * @param typeHandler 类型处理器
   */
  public static void registerTypeHandler(Class<?> clazz, TypeHandler<?> typeHandler) {
    GLOBAL_CONFIGURATION.registerTypeHandler(clazz, typeHandler);
  }

  /**
   * 设置实例工厂.
   *
   * @param objectFactory 实例工厂
   */
  public void setObjectFactory(final ObjectFactory objectFactory) {
    GLOBAL_CONFIGURATION.setObjectFactory(objectFactory);
  }

  /**
   * 设置Mapper构造器.
   *
   * @param mapperBuilder Mapper构造器
   */
  public void setMapperBuilder(final MapperBuilder mapperBuilder) {
    GLOBAL_CONFIGURATION.setMapperBuilder(mapperBuilder);
  }
}
