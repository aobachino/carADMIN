package jp.co.Controller;

import javax.servlet.http.HttpServletRequest;
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
import jp.co.Model.CarSerchModel;
import jp.co.Service.CarSerchService;
import jp.co.Service.CategoryService;

@Controller
public class CarInfoController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CarSerchService carSerchService;

	@RequestMapping("/carSerch")
	public ModelAndView showForm(HttpSession session) {
		ReserveInfoDto categoryInfo = categoryService.findCategory();
		session.setAttribute("category", categoryInfo);
		return new ModelAndView("CarList/carSerch");

	}

	@RequestMapping(path = "/serchDo", method = RequestMethod.POST)
	public ModelAndView serchDo(@Valid @ModelAttribute CarSerchModel casmo, BindingResult errors,
			HttpServletRequest req, HttpSession session) {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String requestURI = httpRequest.getRequestURI();
		session.setAttribute("requestURI", requestURI);
		ModelAndView mv = null;
		if (!(casmo.getMaxPrice().equals("")) && (!(casmo.getMinPrice().equals("")))) {
			if (Integer.parseInt(casmo.getMaxPrice()) <= Integer.parseInt(casmo.getMinPrice())) {
				String message = "上限金額は下限金額より大きい金額を入力してください";
				mv = new ModelAndView("CarList/carSerch", "priceError", message);
				return mv;
			} else {
				ReserveInfoDto rdt = carSerchService.carSerch(casmo);
				if (rdt.getCarList().size() == 0) {
					String message = "条件に該当する商品は0件です";
					mv = new ModelAndView("CarList/carSerch", "gdZero", message);
				} else {
					mv = new ModelAndView("CarList/carSerch", "gdInfo", rdt);
				}
			}
		} else {
			ReserveInfoDto rdt = carSerchService.carSerch(casmo);
			if (rdt.getCarList().size() == 0) {
				String message = "条件に該当する商品は0件です";
				mv = new ModelAndView("CarList/carSerch", "carZero", message);
			} else {
				mv = new ModelAndView("CarList/carSerch", "carInfo", rdt);
			}
		}
		return mv;
	}

	@RequestMapping(path = "/carDetail.html", method = RequestMethod.POST)
	public ModelAndView showDetail(@ModelAttribute CarSerchModel carmo, HttpSession session, HttpServletRequest req) {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String requestURI = httpRequest.getRequestURI();
		session.setAttribute("requestURI", requestURI);

		ReserveInfoDto rdt = carSerchService.carfind(carmo);
		session.setAttribute("carDetail", rdt);
		ModelAndView mv = new ModelAndView("CarList/carDetail");
		return mv;
	}
}
