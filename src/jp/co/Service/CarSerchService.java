package jp.co.Service;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.Dao.CarDao;
import jp.co.Dto.ReserveInfoDto;
import jp.co.Entity.CarEntity;
import jp.co.Model.CarRegisterModel;
import jp.co.Model.CarSerchModel;

@Service
public class CarSerchService {
	@Autowired
	private CarDao carDao;

	public ReserveInfoDto carSerch(CarSerchModel casmo) {

		ReserveInfoDto rdt = new ReserveInfoDto();
		Map<String, String> map = new LinkedHashMap<>();
		if (casmo.getCarNum() != null && !"".equals(casmo.getCarNum())) {
			map.put("CAR_CODE", casmo.getCarNum());
		}
		if (!"".equals(casmo.getCategNum())) {
			map.put("CTGR_ID", casmo.getCategNum());
		}
		if (casmo.getCarName() != null && !"".equals(casmo.getCarName())) {
			map.put("CAR_NAME", casmo.getCarName());
		}
		if (casmo.getMaker() != null && !"".equals(casmo.getMaker())) {
			map.put("CAR_MAKER", casmo.getMaker());
		}
		if (casmo.getGas() != null && !"".equals(casmo.getGas())) {
			map.put("ENGINE", casmo.getGas());
		}
		if (casmo.getRegDate() != null && !"".equals(casmo.getRegDate())) {
			map.put("REGISTER_DAY", casmo.getRegDate());
		}
		if (casmo.getMaxPrice() != null && !"".equals(casmo.getMaxPrice())) {
			map.put("MAX_PRICE", casmo.getMaxPrice());
		}
		if (casmo.getMinPrice() != null && !"".equals(casmo.getMinPrice())) {
			map.put("MIN_PRICE", casmo.getMinPrice());
		}

		List<CarEntity> carList = carDao.findCar(map);
		rdt.setCarList(carList);
		return rdt;
	}

	public ReserveInfoDto carfind(CarSerchModel carmo) {
		List<CarEntity> carList = carDao.carFind(carmo.getCarNum());
		ReserveInfoDto rdt = new ReserveInfoDto();
		rdt.setCarList(carList);
		return rdt;
	}

	public ReserveInfoDto carfind(CarRegisterModel carmo) {
		List<CarEntity> carList = carDao.carFind(carmo);
		ReserveInfoDto rdt = new ReserveInfoDto();
		rdt.setCarList(carList);
		return rdt;
	}

	@Transactional(rollbackForClassName = "Exception")
	public boolean infoChange(CarRegisterModel carmo) throws SQLException {
		boolean res = true;
		CarEntity me = new CarEntity();
		me.setCarNum(carmo.getCarNum());
		me.setCategNum(Integer.parseInt(carmo.getCategNum()));
		me.setCarName(carmo.getCarName());
		me.setMaker(carmo.getMaker());
		me.setGas(Integer.parseInt(carmo.getGas()));
		me.setPrice(Integer.parseInt(carmo.getPrice()));
		me.setMemo(carmo.getMemo());
		me.setDelflg(0);
		me.setUpdStaff("11111");
		int cnt = carDao.changeData(me);
		if (cnt == 0) {
			res = false;
		}

		return res;
	}

	public ReserveInfoDto changedDeta(CarRegisterModel carmo) {
		List<CarEntity> carList = carDao.carFind(carmo);
		ReserveInfoDto rdt = new ReserveInfoDto();
		rdt.setCarList(carList);
		return rdt;
	}

	@Transactional(rollbackForClassName = "Exception")
	public boolean carDelete(ReserveInfoDto rdt) throws SQLException {
		boolean result = true;
		CarEntity me = new CarEntity();
		for (CarEntity c : rdt.getCarList()) {
			me.setCarNum(c.getCarNum());
			int cnt = carDao.deleteData(me);
			if (cnt == 0) {
				result = false;
			}
		}
		return result;
	}
}
