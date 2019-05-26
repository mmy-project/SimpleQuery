package com.mumayuan.simple.query.mapper;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;

/**
 * 表字段.
 *
 * @author zjj
 */
public class ColumnInfo {

  private ThreadLocal<Integer> index = ThreadLocal.withInitial(() -> 0);
  private boolean isPrimaryKey;
  private String columnName;
  private TypeHandler<?> typeHandler;

  public int getIndex() {
    return index.get();
  }

  /**
   * setIndex.
   *
   * @param index int
   */
  public void setIndex(final int index) {
    this.index.set(index);
  }

  public boolean isPrimaryKey() {
    return isPrimaryKey;
  }

  public void setIsPrimaryKey(final boolean isPrimaryKey) {
    this.isPrimaryKey = isPrimaryKey;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(final String columnName) {
    this.columnName = columnName;
  }

  public TypeHandler<?> getTypeHandler() {
    return typeHandler;
  }

  public void setTypeHandler(final TypeHandler<?> typeHandler) {
    this.typeHandler = typeHandler;
  }
}
