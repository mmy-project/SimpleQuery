package com.mumayuan.simple.query;

import com.mumayuan.simple.query.annotations.ResultColumn;

/**
 * 行数查询实体.
 *
 * @author zjj
 */
public final class Count extends BaseEntity {

  @ResultColumn("count")
  private int count;

  public int getCount() {
    return count;
  }

  public void setCount(final int count) {
    this.count = count;
  }
}
