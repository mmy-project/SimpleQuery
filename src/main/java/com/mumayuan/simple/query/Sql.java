package com.mumayuan.simple.query;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * SQL对象.
 *
 * @author zjj
 */
public final class Sql {

  private StringBuilder sql;
  private Comment comment;
  private List<Object> params;

  public Sql(String sql) {
    this();
    this.sql.append(sql);
  }

  public Sql() {
    this.sql = new StringBuilder();
    this.params = new LinkedList<>();
  }

  public Sql(final String project, final String module, final String method, final String description)
      throws SQLException {
    this();
    initComment(project, module, method, description);
  }

  public Comment getComment() {
    return comment;
  }

  public List<Object> getParams() {
    return params;
  }

  /**
   * 拼接SQL.
   *
   * @param childSql 子句
   * @return SQL对象
   */
  public Sql appendSql(String childSql) {
    return appendSql(childSql, new Object[0]);
  }

  /**
   * 拼接SQL 注意参数顺序.
   *
   * @param childSql 子句
   * @param params 参数
   * @return SQL对象
   */
  public Sql appendSql(String childSql, Object... params) {
    if (sql.length() != 0) {
      sql.append(" ");
    }
    sql.append(childSql);
    if (params != null && params.length > 0) {
      this.params.addAll(Arrays.asList(params));
    }
    return this;
  }

  /**
   * 拼接参数.
   *
   * @param params 参数
   * @return SQL对象
   */
  public Sql appendParams(Object... params) {
    this.params.addAll(Arrays.asList(params));
    return this;
  }

  /**
   * 清除sql保留Comment.
   */
  public void clear() {
    sql.setLength(0);
    params.clear();
  }

  /**
   * 初始化 Comment 对象.
   *
   * @param project 项目
   * @param module 模块
   * @param method 方法
   * @param description 描述
   * @throws SQLException error
   */
  public void initComment(final String project, final String module, final String method, final String description)
      throws SQLException {
    if (project == null || project.length() == 0) {
      throw new SQLException("project 不能空");
    }
    if (module == null || module.length() == 0) {
      throw new SQLException("module 不能空");
    }
    if (method == null || method.length() == 0) {
      throw new SQLException("method 不能空");
    }
    comment = new Comment(project, module, method, description);
  }

  /**
   * SQL 描述.
   */
  public class Comment {

    private String project;
    private String module;
    private String method;
    private String description;

    Comment(final String project, final String module, final String method, final String description) {
      this.project = project;
      this.module = module;
      this.method = method;
      this.description = description;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("-- ");
      sb.append("project=").append(project).append(" ");
      sb.append("module=").append(module).append(" ");
      sb.append("method=").append(method).append(" ");
      sb.append("description=").append(description).append(" ");
      return sb.toString();
    }
  }

  @Override
  public String toString() {
    return sql + comment.toString();
  }
}
