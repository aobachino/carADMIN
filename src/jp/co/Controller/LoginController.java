package jp.co.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.Dto.StaffInfoDto;
import jp.co.Model.LoginModel;
import jp.co.Service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String showLog() {
		return "Login/loginForm";
	}
		
	@RequestMapping(path = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@Valid @ModelAttribute LoginModel logmo, BindingResult errors, HttpSession session) {
		boolean logModel = loginService.userFind(logmo);
		if(logModel) {
			StaffInfoDto sdt = loginService.findStaff(logmo);
			ModelAndView mv = new ModelAndView("Main/menu");
			session.setAttribute("staffInfo", sdt);
			return mv;
		}else {
			String message = "ログインできませんでした";
			ModelAndView pr = new ModelAndView("Login/loginForm", "loginnot", message);
			return pr;
		}

	}
	
}
