package com.mumayuan.simple.query.mapper;

import java.util.Map;

/**
 * 实体映射.
 *
 * @author zjj
 */
public final class Mapper {

  private Class<?> clazz;
  private String tableName;
  private Map<String, FieldInfo> fieldInfoMap;

  public Class<?> getClazz() {
    return clazz;
  }

  public Mapper(final Class<?> clazz) {
    this.clazz = clazz;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(final String tableName) {
    this.tableName = tableName;
  }
  
  public Map<String, FieldInfo> getFieldInfoMap() {
    return fieldInfoMap;
  }

  public void setFieldInfoMap(final Map<String, FieldInfo> fieldInfoMap) {
    this.fieldInfoMap = fieldInfoMap;
  }
}
