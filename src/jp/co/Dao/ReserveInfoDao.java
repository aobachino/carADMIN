package jp.co.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.Entity.ReserveInfoEntity;

@Repository
public class ReserveInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterTemplate;

	private class ReserveRowMapper extends BeanPropertyRowMapper<ReserveInfoEntity> {
		@Override
		public ReserveInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReserveInfoEntity en = new ReserveInfoEntity();
			en.setRentNum(rs.getInt("RENTAL_NO"));
			en.setMemNum(rs.getInt("MEMBER_NO"));
			en.setCarNum(rs.getString("CAR_CODE"));
			en.setStartDay(rs.getString("START_DAY"));
			en.setFinishDay(rs.getString("END_DAY"));
			en.setReserveState(rs.getString("STATE_FLG"));
			en.setPrice(rs.getInt("RENTAL_MONEY"));
			en.setReserveDay(rs.getDate("RESERVE_DAY"));
			en.setColleNum(rs.getString("COLLECT_NO"));
			en.setStaffNum(rs.getInt("STAFF_NO"));
			en.setUpdStaff(rs.getString("LAST_UPD_STAFF"));
			en.setLstDay(rs.getDate("LAST_UPD_DAY"));

			return en;
		}
	}

	public List<ReserveInfoEntity> reserve(Map<String, String> map) {
		String sql = "select * from RENTAL_RESERVE";
		StringBuffer aaa = new StringBuffer(sql);
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		if (map.size() > 0) {
			aaa.append(" where ");

			int count = 1;
			for (String key : map.keySet()) {
				if ("COLLECT_NO".equals(key)) {
					aaa.append("COLLECT_NO =  :colleNum");
					paramMap.addValue("colleNum", map.get(key));

				} else if ("STATE_FLG".equals(key)) {
					aaa.append("STATE_FLG =  :status");
					paramMap.addValue("status", map.get(key));
				}
				if (count < map.size()) {
					aaa.append(" AND ");
				}
				count++;
			}

			List<ReserveInfoEntity> resList = namedParameterTemplate.query(aaa.toString(), paramMap,
					new ReserveRowMapper());
			return resList;
		} else {

			List<ReserveInfoEntity> resList = namedParameterTemplate.query(sql, new ReserveRowMapper());
			return resList;
		}
	}
	public int changeSta(ReserveInfoEntity re) {
		String sql = "update RENTAL_CAR set STATE_FLG = :status where COLLECT_NO = :colleNum";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("ccolleNum", re.getColleNum());
		paramMap.addValue("status", re.getReserveState());
		int cnt = this.namedParameterTemplate.update(sql, paramMap);
		return cnt;
	}
}
