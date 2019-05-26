package com.mumayuan.simple.query;

import com.mumayuan.simple.query.jdbc.DataSourceContainer;
import com.mumayuan.simple.query.mapper.Mapper;
import com.mumayuan.simple.query.mapper.MapperBuilder;
import com.mumayuan.simple.query.mapper.defaults.DefaultMapperBuilder;
import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandlerFactory;
import com.mumayuan.simple.query.utils.reflection.DefaultObjectFactory;
import com.mumayuan.simple.query.utils.reflection.ObjectFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;

/**
 * 配置.
 *
 * @author zjj
 */
public final class Configuration {

  private final Map<Class<?>, Mapper> mappers = new ConcurrentHashMap<>();
  private ObjectFactory objectFactory = new DefaultObjectFactory();
  private TypeHandlerFactory typeHandlerFactory = new TypeHandlerFactory();
  private MapperBuilder mapperBuilder = new DefaultMapperBuilder(this);

  public Configuration() {
  }

  /**
   * 根据名称获取数据源.
   *
   * @param dsName 数据源名
   * @return DataSource 数据源
   * @throws Exception error
   */
  public DataSource getDataSource(final String dsName) throws Exception {
    return DataSourceContainer.getDataSource(dsName);
  }

  /**
   * 添加数据源.
   *
   * @param dsName 数据源名
   * @param dataSource 数据源
   */
  public void addDataSource(final String dsName, final DataSource dataSource) {
    DataSourceContainer.addDataSource(dsName, dataSource);
  }

  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  public void setObjectFactory(final ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  /**
   * 注册类型处理器.
   *
   * @param clazz 类型
   * @param typeHandler 类型处理器
   */
  public void registerTypeHandler(Class<?> clazz, TypeHandler<?> typeHandler) {
    typeHandlerFactory.registerTypeHandler(clazz, typeHandler);
  }

  public TypeHandlerFactory getTypeHandlerFactory() {
    return typeHandlerFactory;
  }

  public MapperBuilder getMapperBuilder() {
    return mapperBuilder;
  }

  public void setMapperBuilder(final MapperBuilder mapperBuilder) {
    this.mapperBuilder = mapperBuilder;
  }

  /**
   * 缓存mapper对象.
   *
   * @param clazz 类对象
   * @param mapper mapper对象
   */
  public void setMapper(final Class<?> clazz, final Mapper mapper) {
    mappers.put(clazz, mapper);
  }

  /**
   * 获取mapper对象.
   *
   * @param clazz 类对象
   * @return mapper对象
   */
  public Mapper getMapper(final Class<?> clazz) {
    return mappers.get(clazz);
  }
}
