package jp.co.Controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.Dto.ReserveInfoDto;
import jp.co.Model.ReserveInfoModel;
import jp.co.Service.ReserveInfoService;

@Controller
public class ReserveInfoController {

	@Autowired
	private ReserveInfoService reserveInfoService;

	@RequestMapping("/serchForm")
	public String showForm() {
		return "ReserveInfo/reserveInfo";
	}

	@RequestMapping(path = "/serchReserve", method = RequestMethod.POST)
	public ModelAndView serchInfo(@ModelAttribute ReserveInfoModel resimo, HttpSession session) {
		ReserveInfoDto rdt = reserveInfoService.findReserve(resimo);
		if (rdt.getResList().size() == 0) {
			String message = "条件に該当する件数は0件です";
			ModelAndView mv = new ModelAndView("ReserveInfo/reserveInfo", "resZero", message);
			return mv;
		} else {
			session.setAttribute("reserve", rdt);
			ModelAndView mv = new ModelAndView("ReserveInfo/reserveInfo");
			return mv;
		}
	}

	@RequestMapping(path = "/statusChange", method = RequestMethod.POST)
	public ModelAndView changeStatus(@ModelAttribute ReserveInfoModel resimo, HttpSession sessions) {
		ModelAndView mv = null;
		try {
			boolean res = reserveInfoService.statusChange(resimo);
			if (res) {
				mv = new ModelAndView("ReserveInfo/reserveInfo");
			} else {
				mv = new ModelAndView("Error/error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;

	}
}
