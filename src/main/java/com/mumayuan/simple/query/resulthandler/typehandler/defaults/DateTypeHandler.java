package com.mumayuan.simple.query.resulthandler.typehandler.defaults;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * DateTypeHandler.
 *
 * @author zjj
 */
public final class DateTypeHandler implements TypeHandler<Date> {

  // 1900-1-1
  private static final Date DEFAULT = new Date(-2209017600000L);

  @Override
  public Date getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getDate(columnName);
  }

  @Override
  public Date getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getDate(columnIndex);
  }

  @Override
  public Date getDefault() {
    return DEFAULT;
  }
}
