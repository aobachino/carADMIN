package jp.co.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.Dao.CategoryDao;
import jp.co.Dto.ReserveInfoDto;
import jp.co.Entity.CategoryEntity;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public ReserveInfoDto findCategory() {
		 List<CategoryEntity> categorys = categoryDao.selectAllCategory();
		 ReserveInfoDto categdt = new ReserveInfoDto();
		 categdt.setCategList(categorys);
		 return categdt;	
	}
}
