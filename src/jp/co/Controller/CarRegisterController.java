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
import jp.co.Service.CarRegisterService;
import jp.co.Service.CategoryService;

@Controller
public class CarRegisterController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CarRegisterService carRegisterService;
	
	
	@RequestMapping("/carRegForm")
	public ModelAndView showForm(HttpSession session) {
		ReserveInfoDto categoryInfo = categoryService.findCategory();
		session.setAttribute("category", categoryInfo);
		return new ModelAndView("CarRegister/registerForm");
	}

	@RequestMapping(path = "/registerConf", method = RequestMethod.POST)
	public ModelAndView regConf(@Valid @ModelAttribute CarRegisterModel carmo, BindingResult errors) {
		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("CarRegister/registerForm");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("CarRegister/registerConf", "formInfo", carmo);
			return mv;
		}
	}
	@RequestMapping(path = "/registerDo", method = RequestMethod.POST)
	public ModelAndView registerDo(@ModelAttribute CarRegisterModel carmo) {
		
		try {
			carRegisterService.insert(carmo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("CarList/carSerch");
		return mv;
	}

}
