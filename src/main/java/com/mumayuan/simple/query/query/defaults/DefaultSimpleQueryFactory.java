package com.mumayuan.simple.query.query.defaults;

import com.mumayuan.simple.query.Configuration;
import com.mumayuan.simple.query.GlobalConfig;
import com.mumayuan.simple.query.query.SimpleQuery;
import com.mumayuan.simple.query.query.SimpleQueryFactory;

/**
 * DefaultSimpleQueryFactory.
 *
 * @author zjj
 */
public final class DefaultSimpleQueryFactory implements SimpleQueryFactory {

  private Configuration configuration;
  private String dataSourceName;

  public DefaultSimpleQueryFactory(final Configuration configuration, final String dataSourceName) {
    this.configuration = configuration;
    this.dataSourceName = dataSourceName;
  }

  public DefaultSimpleQueryFactory(final String dataSourceName) {
    this(GlobalConfig.getConfiguration(), dataSourceName);
  }

  /**
   * 获取操作对象.
   *
   * @return SimpleQuery
   * @throws Exception error
   */
  @Override
  public SimpleQuery createSimpleQuery() throws Exception {
    return new DefaultSimpleQuery(this.configuration, this.dataSourceName);
  }
}
