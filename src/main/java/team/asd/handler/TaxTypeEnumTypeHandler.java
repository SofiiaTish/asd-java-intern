package team.asd.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import team.asd.constant.TaxType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@MappedTypes({TaxType.class})
public class TaxTypeEnumTypeHandler extends BaseTypeHandler<TaxType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, TaxType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public TaxType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return rs.wasNull() ? null : TaxType.getByInt(value);
	}

	@Override
	public TaxType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer value = rs.getInt(columnIndex);
		return rs.wasNull() ? null : TaxType.getByInt(value);
	}

	@Override
	public TaxType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return cs.wasNull() ? null : TaxType.getByInt(value);
	}
}
