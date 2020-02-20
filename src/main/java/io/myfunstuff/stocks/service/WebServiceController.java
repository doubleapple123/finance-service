package io.myfunstuff.stocks.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebServiceController{
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getIndex(){
		return "hello";
	}
}
