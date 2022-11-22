package team.asd.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import team.asd.constant.ValueType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({ValueType.class})
public class ValueTypeEnumTypeHandler extends BaseTypeHandler<ValueType> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ValueType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public ValueType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return rs.wasNull() ? null : ValueType.getByInt(value);
	}

	@Override
	public ValueType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer value = rs.getInt(columnIndex);
		return rs.wasNull() ? null : ValueType.getByInt(value);
	}

	@Override
	public ValueType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return cs.wasNull() ? null : ValueType.getByInt(value);
	}
}
