package jp.co.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.Entity.MemberEntity;

@Repository
public class MemberDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterTemplate;

	private class UserRowMapper extends BeanPropertyRowMapper<MemberEntity> {
		@Override
		public MemberEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberEntity en = new MemberEntity();
			en.setMemNum(rs.getInt("MEMBER_NO"));
			en.setName(rs.getString("NAME"));
			en.setAge(rs.getString("AGE"));
			en.setSex(rs.getString("SEX"));
			en.setPostCode(rs.getString("ZIP"));
			en.setAddress(rs.getString("ADDRESS"));
			en.setPhoneNum(rs.getString("TEL"));
			en.setRegTime(rs.getDate("REGISTER_DAY"));
			en.setDelflig(rs.getInt("DELETE_FLG"));
			en.setPoint(rs.getInt("RENTAL_POINT"));
			en.setUpdstaff(rs.getString("LAST_UPD_STAFF"));
			en.setLstTime(rs.getDate("LAST_UPD_DAY"));
			return en;
		}
	}

	public List<MemberEntity> findUser(int memNum) {
		String sql = "select * from RENTAL_MEMBER where MEMBER_NO = :memNum";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memNum", memNum);
		List<MemberEntity> memList = namedParameterTemplate.query(sql, paramMap, new UserRowMapper());
		return memList;
	}

}
