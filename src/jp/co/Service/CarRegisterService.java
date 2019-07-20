package jp.co.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.Dao.CarDao;
import jp.co.Entity.CarEntity;
import jp.co.Model.CarRegisterModel;

@Service
public class CarRegisterService {

	@Autowired
	private CarDao carDao;

	@Transactional(rollbackForClassName = "Exception")
	public void insert(CarRegisterModel carmo) throws SQLException {
		List<CarEntity> carList = carDao.findAll();
		List<CarEntity> countList = carDao.countCategory(carmo);
		int count;
		if (carList.size() == 0) {
			count = 1;
		} else {
			count = countList.size() + 1;
		}

		String countNum = String.format("%02d", count);

		CarEntity me = new CarEntity();
		LocalDateTime d = LocalDateTime.now();
		DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyMMdd");
		String today = df1.format(d);

		String categNum = String.format("%03d", carmo.getCategNum());

		String carNum = categNum + "-" + today + countNum;

		me.setCarNum(carNum);
		me.setCategNum(Integer.parseInt(carmo.getCategNum()));
		me.setCarName(carmo.getCarName());
		me.setMaker(carmo.getMaker());
		me.setGas(Integer.parseInt(carmo.getGas()));
		me.setPrice(Integer.parseInt(carmo.getPrice()));
		me.setMemo(carmo.getMemo());
		me.setDelflg(0);
		me.setUpdStaff("11111");

		carDao.insert(me);

	}

}
