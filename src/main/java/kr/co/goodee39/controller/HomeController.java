package kr.co.goodee39.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	 //서블릿에서 이동하는 페이지 ,
//	get방식으로 받은 것을 value의 경로에 담아 "index"에 태워서 이동하겠다.
// ServletAppContext의 /"WEB-INF/views/"이 "index"의 앞에 .jsp가 뒤에 온다
     @RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index";
		
	}
}
