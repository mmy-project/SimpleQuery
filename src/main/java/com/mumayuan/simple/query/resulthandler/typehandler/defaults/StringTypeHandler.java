package com.mumayuan.simple.query.resulthandler.typehandler.defaults;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StringTypeHandler.
 *
 * @author zjj
 */
public final class StringTypeHandler implements TypeHandler<String> {

  private static final String DEFAULT = "";

  @Override
  public String getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getString(columnName);
  }

  @Override
  public String getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getString(columnIndex);
  }

  @Override
  public String getDefault() {
    return DEFAULT;
  }
}
