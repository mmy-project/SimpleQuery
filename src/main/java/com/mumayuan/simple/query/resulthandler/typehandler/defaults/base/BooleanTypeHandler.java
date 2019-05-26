package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BooleanTypeHandler.
 *
 * @author zjj
 */
public final class BooleanTypeHandler implements TypeHandler<Boolean> {

  @Override
  public Boolean getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getBoolean(columnName);
  }

  @Override
  public Boolean getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getBoolean(columnIndex);
  }

  @Override
  public Boolean getDefault() {
    return false;
  }
}
