package com.mumayuan.simple.query.jdbc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源容器.
 *
 * @author zjj
 */
public class DataSourceContainer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContainer.class);

  /**
   * 数据源缓存.
   */
  protected static final Map<String, DataSource> DATA_SOURCE_CACHE = new ConcurrentHashMap<>();
  /**
   * 数据源默认名.
   */
  private static final String DATA_SOURCE_CACHE_DEFAULT_KEY = "DEFAULT_DATA_SOURCE";

  /**
   * 添加数据源.
   *
   * @param dsName 数据源名
   * @param ds 数据源
   * @param isCover 是否可以覆盖
   */
  public static synchronized void addDataSource(final String dsName, final DataSource ds, final boolean isCover) {
    try {
      if (DATA_SOURCE_CACHE.containsKey(dsName)) {

        if (isCover) {
          DATA_SOURCE_CACHE.put(dsName, ds);
          LOGGER.debug("覆盖数据源 -> {}", dsName);
        } else {
          LOGGER.warn("数据源已存在 -> {}", dsName);
        }
      } else {
        DATA_SOURCE_CACHE.put(dsName, ds);
        LOGGER.debug("新增数据源 -> {}", dsName);
      }
    } catch (Exception e) {
      LOGGER.error("新增数据源失败", e);
    }
  }

  /**
   * 添加数据源.
   *
   * @param dsName 数据源名
   * @param ds 数据源
   */
  public static void addDataSource(final String dsName, final DataSource ds) {
    addDataSource(dsName, ds, true);
  }

  /**
   * 设置默认数据源.
   *
   * @param ds 数据源
   */
  public static void setDefaultDataSource(final DataSource ds) {
    addDataSource(DATA_SOURCE_CACHE_DEFAULT_KEY, ds);
  }

  /**
   * 根据名称获取数据源.
   *
   * @param dsName 数据源名
   * @return DataSource 数据源
   * @throws Exception error
   */
  public static DataSource getDataSource(final String dsName) throws Exception {
    if (dsName == null || dsName.length() == 0) {
      return getDefaultDataSource();
    } else if (!DATA_SOURCE_CACHE.containsKey(dsName)) {
      throw new Exception("数据源不存在.");
    }
    return DATA_SOURCE_CACHE.get(dsName);
  }

  /**
   * 获取默认数据源.
   *
   * @return DataSource 数据源
   * @throws Exception error
   */
  public static DataSource getDefaultDataSource() throws Exception {
    return getDataSource(DATA_SOURCE_CACHE_DEFAULT_KEY);
  }
}
