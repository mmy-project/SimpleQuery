package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ShortTypeHandler.
 *
 * @author zjj
 */
public final class ShortTypeHandler implements TypeHandler<Short> {

  @Override
  public Short getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getShort(columnName);
  }

  @Override
  public Short getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getShort(columnIndex);
  }

  @Override
  public Short getDefault() {
    return 0;
  }
}
