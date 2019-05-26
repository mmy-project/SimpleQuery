package com.mumayuan.simple.query;

import java.util.List;

/**
 * 分页.
 *
 * @param <T> 泛型
 * @author zjj
 */
public class Page<T extends BaseEntity> {

  /**
   * 每页显示的条数.
   */
  private int pageSize;
  /**
   * 当前页.
   */
  private int currentPageNum;
  /**
   * 总页数.
   */
  private int totalPageNum;
  /**
   * 总记录条数.
   */
  private int totalRecords;
  /**
   * 上一页.
   */
  private int prePageNum;
  /**
   * 下一页.
   */
  private int nextPageNum;
  /**
   * data
   */
  private List<T> data;

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(final int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPageNum() {
    return currentPageNum;
  }

  public void setCurrentPageNum(final int currentPageNum) {
    this.currentPageNum = currentPageNum;
  }

  public int getTotalPageNum() {
    return totalPageNum;
  }

  public void setTotalPageNum(final int totalPageNum) {
    this.totalPageNum = totalPageNum;
  }

  public int getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(final int totalRecords) {
    this.totalRecords = totalRecords;
  }

  public int getPrePageNum() {
    return prePageNum;
  }

  public void setPrePageNum(final int prePageNum) {
    this.prePageNum = prePageNum;
  }

  public int getNextPageNum() {
    return nextPageNum;
  }

  public void setNextPageNum(final int nextPageNum) {
    this.nextPageNum = nextPageNum;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(final List<T> data) {
    this.data = data;
  }
}
