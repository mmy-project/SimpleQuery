package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IntegerTypeHandler.
 *
 * @author zjj
 */
public final class IntegerTypeHandler implements TypeHandler<Integer> {

  @Override
  public Integer getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getInt(columnName);
  }

  @Override
  public Integer getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getInt(columnIndex);
  }

  @Override
  public Integer getDefault() {
    return 0;
  }
}
