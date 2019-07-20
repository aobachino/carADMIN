package jp.co.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.Dao.LoginDao;
import jp.co.Dto.StaffInfoDto;
import jp.co.Entity.StaffEntity;
import jp.co.Model.LoginModel;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public boolean userFind(LoginModel logmo) {
		boolean check = true;
		List<StaffEntity> infList = loginDao.findLogData(logmo);
		if (infList.size() == 0) {

			check = false;
		}

		return check;
	}

	public StaffInfoDto findStaff(LoginModel logmo) {
		List<StaffEntity> infList = loginDao.findLogData(logmo);
		StaffInfoDto sdt = new StaffInfoDto();
		for (StaffEntity en : infList) {
			sdt.setStaffId(en.getStaffId());
			sdt.setClerck(en.getClerck());
			sdt.setPass(en.getPass());
			sdt.setName(en.getName());
			sdt.setAge(en.getAge());
			sdt.setSex(en.getSex());
			sdt.setRegTime(en.getRegTime());
			sdt.setUpdStaff(en.getUpdStaff());
			sdt.setUpdTime(en.getUpdTime());
		}
		return sdt;
	}

}
