package jp.co.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.Entity.CategoryEntity;

@Repository
public class CategoryDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterTemplate;

	private class CategryRowMapper extends BeanPropertyRowMapper<CategoryEntity> {
		@Override
		public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			CategoryEntity en = new CategoryEntity();
			en.setCategNum(rs.getInt("CTGR_ID"));
			en.setName(rs.getString("NAME"));
			en.setLstTime(rs.getDate("LAST_UPD_DAY"));
			return en;
		}
	}

	public List<CategoryEntity> selectCategory(String categNum) {
		String sql = "select * from RENTAL_CATEGORY where CTGR_ID = :categNum ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("categNum", Integer.parseInt(categNum));
		List<CategoryEntity> cate = this.namedParameterTemplate.query(sql, paramMap, new CategryRowMapper());

		return cate;
	}

	public List<CategoryEntity> selectAllCategory() {
		String sql = "select * from RENTAL_CATEGORY";
		List<CategoryEntity> cate = this.namedParameterTemplate.query(sql, new CategryRowMapper());
		return cate;
	}
}
