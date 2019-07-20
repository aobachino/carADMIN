package jp.co.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.Entity.StaffEntity;
import jp.co.Model.LoginModel;

@Repository
public class LoginDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterTemplate;

	private class LoginRowMapper extends BeanPropertyRowMapper<StaffEntity> {
		@Override
		public StaffEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			StaffEntity en = new StaffEntity();
			en.setStaffId(rs.getInt("STAFF_NO"));
			en.setClerck(rs.getString("CLASS"));
			en.setPass(rs.getString("PASSWORD"));
			en.setName(rs.getString("NAME"));
			en.setAge(rs.getString("AGE"));
			en.setSex(rs.getString("SEX"));
			en.setRegTime(rs.getDate("REGISTER_DAY"));
			en.setUpdStaff(rs.getString("LAST_UPD_STAFF"));
			en.setUpdTime(rs.getDate("LAST_UPD_DAY"));
			return en;
		}
	}
	public List<StaffEntity> findLogData(LoginModel logmo) {
		String sql = "select * from RENTAL_STAFF where STAFF_NO = :staffId AND PASSWORD = :pass ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("staffId", logmo.getStaffId());
		paramMap.addValue("pass", logmo.getPass());
		List<StaffEntity> infList = namedParameterTemplate.query(sql, paramMap, new LoginRowMapper());
		return infList;
	}

}
