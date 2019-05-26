package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LongTypeHandler.
 *
 * @author zjj
 */
public final class LongTypeHandler implements TypeHandler<Long> {

  @Override
  public Long getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getLong(columnName);
  }

  @Override
  public Long getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getLong(columnIndex);
  }

  @Override
  public Long getDefault() {
    return 0L;
  }
}
