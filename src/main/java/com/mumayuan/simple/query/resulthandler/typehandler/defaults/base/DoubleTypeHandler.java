package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DoubleTypeHandler.
 *
 * @author zjj
 */
public final class DoubleTypeHandler implements TypeHandler<Double> {

  @Override
  public Double getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getDouble(columnName);
  }

  @Override
  public Double getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getDouble(columnIndex);
  }

  @Override
  public Double getDefault() {
    return 0.0;
  }
}
