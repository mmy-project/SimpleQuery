package com.mumayuan.simple.query.mapper.defaults;

import com.mumayuan.simple.query.Configuration;
import com.mumayuan.simple.query.annotations.Column;
import com.mumayuan.simple.query.annotations.PrimaryKey;
import com.mumayuan.simple.query.annotations.ResultColumn;
import com.mumayuan.simple.query.annotations.Table;
import com.mumayuan.simple.query.mapper.FieldInfo;
import com.mumayuan.simple.query.mapper.Mapper;
import com.mumayuan.simple.query.mapper.MapperBuilder;
import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandlerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DefaultMapperBuilder.
 *
 * @author zjj
 */
public final class DefaultMapperBuilder implements MapperBuilder {

  private TypeHandlerFactory typeHandlerFactory;

  public DefaultMapperBuilder(final Configuration configuration) {
    this.typeHandlerFactory = configuration.getTypeHandlerFactory();
  }

  /**
   * 构建mapper.
   *
   * @param mapper Mapper
   */
  private void buildMapper(final Mapper mapper) {
    final Class<?> clazz = mapper.getClazz();
    if (clazz.isAnnotationPresent(Table.class)) {
      mapper.setTableName(clazz.getAnnotation(Table.class).value());
    }
    Map<String, FieldInfo> fieldInfoMap = new HashMap<>();
    Field[] fields = mapper.getClazz().getDeclaredFields();
    Map<String, Method> methodMap = Arrays.stream(mapper.getClazz().getMethods())
        .collect(Collectors.toMap(m -> m.getName().replace("set", "").toLowerCase(), m -> m, (k1, k2) -> k1));
    for (Field f : fields) {
      String filedName = f.getName();
      FieldInfo fieldInfo = new FieldInfo();
      fieldInfo.setTypeHandler(this.typeHandlerFactory.getTypeHandler(f.getType()));
      if (f.isAnnotationPresent(Column.class)) {
        fieldInfo.setIsTableColumn(true);
        fieldInfo.setColumnName(f.getAnnotation(Column.class).value());
      } else if (f.isAnnotationPresent(ResultColumn.class)
          && f.getAnnotation(ResultColumn.class).value().length() > 0) {
        fieldInfo.setColumnName(f.getAnnotation(ResultColumn.class).value());
      } else {
        fieldInfo.setColumnName(filedName);
      }
      if (f.isAnnotationPresent(PrimaryKey.class)) {
        fieldInfo.setIsTableColumn(true);
        fieldInfo.setIsPrimaryKey(true);
        fieldInfo.setColumnName(f.getAnnotation(PrimaryKey.class).value());
      }
      f.setAccessible(true);
      fieldInfo.setField(f);
      if (methodMap.containsKey(filedName)) {
        fieldInfo.setMethod(methodMap.get(filedName));
      }
      fieldInfoMap.put(fieldInfo.getColumnName(), fieldInfo);
    }
    mapper.setFieldInfoMap(fieldInfoMap);

  }

  /**
   * 创建Mapper对象.
   *
   * @param clazz 类对象
   * @return Mapper
   */
  @Override
  public Mapper build(final Class<?> clazz) {
    Mapper mapper = new Mapper(clazz);
    buildMapper(mapper);
    return mapper;
  }

  /**
   * 创建Mapper对象.
   *
   * @param clazzName 类名
   * @return Mapper
   * @throws Exception error
   */
  @Override
  public Mapper build(final String clazzName) throws Exception {
    Mapper mapper = new Mapper(Class.forName(clazzName));
    buildMapper(mapper);
    return mapper;
  }
}
