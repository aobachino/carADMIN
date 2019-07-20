package jp.co.Controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.Dto.ReserveInfoDto;
import jp.co.Model.CarRegisterModel;
import jp.co.Model.CarSerchModel;
import jp.co.Service.CarSerchService;

@Controller
public class CarManagementController {

	@Autowired
	private CarSerchService carSerchService;

	@RequestMapping("/carMenu")
	public String carMenu() {

		return "CarManage/carMenu";
	}

	@RequestMapping(path = "/carInfoChange", method = RequestMethod.POST)
	public ModelAndView showForm(@ModelAttribute CarSerchModel carmo, HttpSession session) {
		if (session.getAttribute("requestURI").equals("/LastKadai_Admin/carDetail.html")) {
			ModelAndView mv = new ModelAndView("CarList/changeForm");
			return mv;

		} else {
			ReserveInfoDto rdt = carSerchService.carfind(carmo);
			session.setAttribute("carDetail", rdt);
			ModelAndView mv = new ModelAndView("CarList/changeForm");
			return mv;
		}

	}

	@RequestMapping(path = "/changeConf", method = RequestMethod.POST)
	public ModelAndView changeConf(@Valid @ModelAttribute CarRegisterModel carmo, BindingResult errors) {
		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("CarList/carChangeForm");
			return mv;
		} else {

			ModelAndView mv = new ModelAndView("CarList/changeConf", "infoConf", carmo);
			return mv;

		}
	}

	@RequestMapping(path = "/changeDo", method = RequestMethod.POST)
	public ModelAndView changeDo(@ModelAttribute CarRegisterModel carmo, HttpSession session) {

		try {
			boolean res = carSerchService.infoChange(carmo);
			if (res) {

				ReserveInfoDto rdt = carSerchService.changedDeta(carmo);
				session.setAttribute("carDetail", rdt);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("CarList/carDetail");
		return mv;
	}

	@RequestMapping(path = "/deleteConf", method = RequestMethod.POST)
	public ModelAndView deleteConf(@ModelAttribute CarRegisterModel carmo, HttpSession session) {
		if (session.getAttribute("requestURI").equals("/LastKadai_Admin/carDetail.html")) {
			ModelAndView mv = new ModelAndView("CarList/deleteConf");
			return mv;

		} else {
			ReserveInfoDto rdt = carSerchService.carfind(carmo);
			session.setAttribute("carDetail", rdt);
			ModelAndView mv = new ModelAndView("CarList/deleteConf");
			return mv;

		}

	}

	@RequestMapping(path = "/deleteDo", method = RequestMethod.POST)
	public ModelAndView deleteDo(HttpSession session) {
		ReserveInfoDto rdt = (ReserveInfoDto) session.getAttribute("carDetail");
		try {
			boolean res = carSerchService.carDelete(rdt);
			if (!(res)) {
				ModelAndView mv = new ModelAndView("Error/error");
				return mv;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("CarList/carSerch");
		session.removeAttribute("carDetail");
		return mv;

	}

}
