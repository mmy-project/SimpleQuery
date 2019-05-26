package com.mumayuan.simple.query.mapper;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 字段信息.
 *
 * @author zjj
 */
public final class FieldInfo {

  private ColumnInfo columnInfo = new ColumnInfo();
  private boolean isTableColumn;
  private Field field;
  private Method method;

  public ColumnInfo getColumnInfo() {
    return columnInfo;
  }

  public void setColumnInfo(final ColumnInfo columnInfo) {
    this.columnInfo = columnInfo;
  }

  public boolean isTableColumn() {
    return isTableColumn;
  }

  public void setIsTableColumn(final boolean isTableColumn) {
    this.isTableColumn = isTableColumn;
  }

  public boolean isPrimaryKey() {
    return this.columnInfo.isPrimaryKey();
  }

  /**
   * setIsPrimaryKey.
   *
   * @param isPrimaryKey boolean
   */
  public void setIsPrimaryKey(final boolean isPrimaryKey) {
    this.columnInfo.setIsPrimaryKey(isPrimaryKey);
  }

  public Field getField() {
    return field;
  }

  public void setField(final Field field) {
    this.field = field;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(final Method method) {
    this.method = method;
  }

  public String getColumnName() {
    return this.columnInfo.getColumnName();
  }

  /**
   * setColumnName.
   *
   * @param columnName String
   */
  public void setColumnName(final String columnName) {
    this.columnInfo.setColumnName(columnName);
  }

  public int getIndex() {
    return this.columnInfo.getIndex();
  }

  /**
   * setIndex.
   *
   * @param index int
   */
  public void setIndex(final int index) {
    this.columnInfo.setIndex(index);
  }

  public TypeHandler getTypeHandler() {
    return this.columnInfo.getTypeHandler();
  }

  /**
   * setTypeHandler.
   *
   * @param typeHandler TypeHandler
   */
  public void setTypeHandler(final TypeHandler typeHandler) {
    this.columnInfo.setTypeHandler(typeHandler);
  }
}
