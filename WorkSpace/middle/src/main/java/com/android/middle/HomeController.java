package com.android.middle;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import customer.CustomerDAO;
import customer.CustomerVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@Autowired CustomerDAO dao;
	@ResponseBody
	@RequestMapping("/test")
	public String test(HttpServletRequest req , HttpServletResponse res) {
		System.out.println("여까지옴");
		int list_size = Integer.parseInt( req.getParameter("idx") );
		for (int i = 0; i < list_size; i++) {
			System.out.println(req.getParameter("param" + (i+1)));
		}
		// req <= 요청을 받음 ( 유알엘 + 엔터 )
		// res <= 응답을 줌 ( 페이지 연결 , 값만 return ※)
		
		
		
		
	
		return "home";
	}
	
	//			? 		Paramter json ? Eclipse <-
	//			"true" : "false"
	
}
