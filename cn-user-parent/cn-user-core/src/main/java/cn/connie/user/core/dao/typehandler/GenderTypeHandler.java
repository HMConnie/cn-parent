package cn.connie.user.core.dao.typehandler;

import cn.connie.common.type.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedJdbcTypes(JdbcType.INTEGER)
public class GenderTypeHandler extends BaseTypeHandler<Gender> {

    public void setNonNullParameter(PreparedStatement ps, int i, Gender status, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, status.getId());
    }

    public Gender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Gender.getInstance(rs.getInt(columnName));
    }

    public Gender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Gender.getInstance(rs.getInt(columnIndex));
    }

    public Gender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Gender.getInstance(cs.getInt(columnIndex));
    }

}
