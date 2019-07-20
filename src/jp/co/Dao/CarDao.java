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

import jp.co.Entity.CarEntity;
import jp.co.Model.CarRegisterModel;

@Repository
public class CarDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterTemplate;

	private class CarRowMapper extends BeanPropertyRowMapper<CarEntity> {
		@Override
		public CarEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			CarEntity en = new CarEntity();
			en.setCarNum(rs.getString("CAR_CODE"));
			en.setCategNum(rs.getInt("CTGR_ID"));
			en.setCarName(rs.getString("CAR_NAME"));
			en.setMaker(rs.getString("CAR_MAKER"));
			en.setGas(rs.getInt("ENGINE"));
			en.setRegDay(rs.getDate("REGISTER_DAY"));
			en.setPrice(rs.getInt("RENTAL_MONEY"));
			en.setMemo(rs.getString("MEMO"));
			en.setDelflg(rs.getInt("DELETE_FLG"));
			en.setUpdStaff(rs.getString("LAST_UPD_STAFF"));
			en.setLstDay(rs.getDate("LAST_UPD_DAY"));
			return en;
		}
	}

	public List<CarEntity> carFind(String carNum) {
		String sql = "select * from RENTAL_CAR where CAR_CODE = :carNum";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("carNum", carNum);
		List<CarEntity> carList = namedParameterTemplate.query(sql, paramMap, new CarRowMapper());
		return carList;
	}

	public List<CarEntity> countCategory(CarRegisterModel carmo) {
		String sql = "select * from RENTAL_CAR where CTGR_ID = :categNum";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("categNum", carmo.getCategNum());
		List<CarEntity> carList = namedParameterTemplate.query(sql, paramMap, new CarRowMapper());
		return carList;

	}

	public List<CarEntity> findAll() {
		String sql = "select * from RENTAL_CAR ";
		List<CarEntity> carList = namedParameterTemplate.query(sql, new CarRowMapper());
		return carList;
	}

	public void insert(CarEntity me) {
		String sql = "insert into RENTAL_CAR (CAR_CODE,CTGR_ID,CAR_NAME,CAR_MAKER,ENGINE,REGISTER_DAY,RENTAL_MONEY,MEMO,DELETE_FLG,LAST_UPD_STAFF,LAST_UPD_DAY)"
				+ "values(:carNum,:categNum,:carName,:maker,:gas,NOW(),:price,:memo,:delflg,:updStaff,NOW()) ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("carNum", me.getCarNum());
		paramMap.addValue("categNum", me.getCategNum());
		paramMap.addValue("carName", me.getCarName());
		paramMap.addValue("maker", me.getMaker());
		paramMap.addValue("gas", me.getGas());
		paramMap.addValue("price", me.getPrice());
		paramMap.addValue("memo", me.getMemo());
		paramMap.addValue("delflg", me.getDelflg());
		paramMap.addValue("updStaff", me.getUpdStaff());

		this.namedParameterTemplate.update(sql, paramMap);
	}

	public List<CarEntity> findCar(Map<String, String> map) {
		String sql = "select * from RENTAL_CAR";
		StringBuffer aaa = new StringBuffer(sql);
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		if (map.size() > 0) {
			aaa.append(" where ");

			int count = 1;
			for (String key : map.keySet()) {
				if ("MAX_PRICE".equals(key)) {
					aaa.append(" RENTAL_MONEY <=  :maxPrice");
					paramMap.addValue("maxPrice", map.get(key));

				} else if ("MIN_PRICE".equals(key)) {
					aaa.append(" RENTAL_MONEY >=  :minPrice");
					paramMap.addValue("minPrice", map.get(key));

				} else if ("CAR_CODE".equals(key)) {
					aaa.append("CAR_CODE = :carNum");
					paramMap.addValue("carNum", map.get(key));

				} else if ("CTGR_ID".equals(key)) {
					aaa.append("CTGR_ID LIKE :goodsName");
					paramMap.addValue("goodsName", "%" + map.get(key) + "%");

				} else if ("CAR_NAME".equals(key)) {
					aaa.append("CAR_NAME LIKE :carName");
					paramMap.addValue("carName", "%" + map.get(key) + "%");

				} else if ("CAR_MAKER".equals(key)) {
					aaa.append("CAR_MAKER LIKE :maker");
					paramMap.addValue("maker", "%" + map.get(key) + "%");

				} else if ("ENGINE".equals(key)) {
					aaa.append("ENGINE = :gas");
					paramMap.addValue("gas", map.get(key));

				} else if ("REGISTER_DAY".equals(key)) {
					aaa.append("REGISTER_DAY = :regDate");
					paramMap.addValue("regDate", map.get(key));

				}
				if (count < map.size()) {
					aaa.append(" AND ");
				}
				count++;
			}

			List<CarEntity> infList = namedParameterTemplate.query(aaa.toString(), paramMap,
					new CarRowMapper());
			return infList;
		} else {

			List<CarEntity> infList = namedParameterTemplate.query(sql, new CarRowMapper());
			return infList;
		}
	}

	public List<CarEntity> carFind(CarRegisterModel carmo) {
		String sql = "select * from RENTAL_CAR where CAR_CODE = :carNum";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("carNum", carmo.getCarNum());
		List<CarEntity> carList = namedParameterTemplate.query(sql, paramMap, new CarRowMapper());
		return carList;
	}

	public int changeData(CarEntity me) {
		String sql = "update RENTAL_CAR set CTGR_ID = :categNum,CAR_NAME = :carName,CAR_MAKER =:maker,ENGINE = :gas,RENTAL_MONEY = :price,MEMO = :memo,LAST_UPD_DAY = NOW() where CAR_CODE = :carNum ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("carNum", me.getCarNum());
		paramMap.addValue("categNum", me.getCategNum());
		paramMap.addValue("carName", me.getCarName());
		paramMap.addValue("maker", me.getMaker());
		paramMap.addValue("gas", me.getGas());
		paramMap.addValue("price", me.getPrice());
		paramMap.addValue("memo", me.getMemo());
		int cnt = this.namedParameterTemplate.update(sql, paramMap);
		return cnt;
	}

	public int deleteData(CarEntity me) {
		String sql = "delete from RENTAL_CAR where CAR_CODE = :carNum ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("carNum", me.getCarNum());
		int cnt = this.namedParameterTemplate.update(sql, paramMap);
		return cnt;
	}

}
