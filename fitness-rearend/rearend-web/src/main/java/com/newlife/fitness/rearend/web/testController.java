package com.newlife.fitness.rearend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController{
	
	@RequestMapping("/index.html")
	public String test_index() {
		System.out.println("1234");
		return "index2";
	}
}



