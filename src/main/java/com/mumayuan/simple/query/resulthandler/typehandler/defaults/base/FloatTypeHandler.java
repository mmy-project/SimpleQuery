package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FloatTypeHandler.
 *
 * @author zjj
 */
public final class FloatTypeHandler implements TypeHandler<Float> {

  @Override
  public Float getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getFloat(columnName);
  }

  @Override
  public Float getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getFloat(columnIndex);
  }

  @Override
  public Float getDefault() {
    return 0f;
  }
}
