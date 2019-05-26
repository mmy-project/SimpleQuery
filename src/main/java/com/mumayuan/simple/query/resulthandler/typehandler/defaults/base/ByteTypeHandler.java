package com.mumayuan.simple.query.resulthandler.typehandler.defaults.base;

import com.mumayuan.simple.query.resulthandler.typehandler.TypeHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ByteTypeHandler.
 *
 * @author zjj
 */
public final class ByteTypeHandler implements TypeHandler<Byte> {

  @Override
  public Byte getResult(final ResultSet rs, final String columnName) throws SQLException {
    return rs.getByte(columnName);
  }

  @Override
  public Byte getResult(final ResultSet rs, final int columnIndex) throws SQLException {
    return rs.getByte(columnIndex);
  }

  @Override
  public Byte getDefault() {
    return 0;
  }
}
