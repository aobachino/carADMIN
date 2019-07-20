package jp.co.Service;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.Dao.CarDao;
import jp.co.Dao.CategoryDao;
import jp.co.Dao.MemberDao;
import jp.co.Dao.ReserveInfoDao;
import jp.co.Dto.ReserveInfoDto;
import jp.co.Entity.CarEntity;
import jp.co.Entity.CategoryEntity;
import jp.co.Entity.MemberEntity;
import jp.co.Entity.ReserveInfoEntity;
import jp.co.Model.ReserveInfoModel;

@Service
public class ReserveInfoService {

	@Autowired
	private ReserveInfoDao reserveInfoDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CarDao carDao;

	public ReserveInfoDto findReserve(ReserveInfoModel resimo) {
		ReserveInfoDto rdt = new ReserveInfoDto();
		Map<String, String> map = new LinkedHashMap<>();
		if (resimo.getColleNum() != null && !"".equals(resimo.getColleNum())) {
			map.put("COLLECT_NO", resimo.getColleNum());
		}
		if (resimo.getStatus() != null && !"".equals(resimo.getStatus())) {
			map.put("STATE_FLG", resimo.getStatus());
		}
		List<ReserveInfoEntity> resList = reserveInfoDao.reserve(map);
		rdt.setResList(resList);
		List<MemberEntity> memList = null;
		List<CategoryEntity> categList = null;
		List<CarEntity> carList = null;
		for (int i = 0 ; i < resList.size();i++){
			memList = memberDao.findUser(resList.get(i).getMemNum());
			

			String carNum = resList.get(i).getCarNum();
			String categNum = carNum.substring(2, 3);
			 categList = categoryDao.selectCategory(categNum);
			

			carList = carDao.carFind(resList.get(i).getCarNum());
			
		}
		rdt.setMemList(memList);
		rdt.setCategList(categList);
		rdt.setCarList(carList);
		return rdt;
	}

	@Transactional(rollbackForClassName = "Exception")
	public boolean statusChange(ReserveInfoModel resimo) throws SQLException {

		boolean res = true;
		ReserveInfoEntity re = new ReserveInfoEntity();
		re.setColleNum(resimo.getColleNum());
		re.setReserveState(resimo.getStatus());
		int cnt = reserveInfoDao.changeSta(re);
		if (cnt == 0) {
			res = false;
		}

		return res;
	}

}
