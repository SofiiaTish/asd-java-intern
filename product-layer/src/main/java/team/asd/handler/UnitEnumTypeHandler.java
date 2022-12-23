package team.asd.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import team.asd.constant.Unit;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Unit.class})
public class UnitEnumTypeHandler extends BaseTypeHandler<Unit> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Unit parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public Unit getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer value = rs.getInt(columnName);
		return rs.wasNull() ? null : Unit.getByInt(value);
	}

	@Override
	public Unit getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer value = rs.getInt(columnIndex);
		return rs.wasNull() ? null : Unit.getByInt(value);
	}

	@Override
	public Unit getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer value = cs.getInt(columnIndex);
		return cs.wasNull() ? null : Unit.getByInt(value);
	}
}
