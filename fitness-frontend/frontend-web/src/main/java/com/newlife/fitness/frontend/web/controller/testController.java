package com.newlife.fitness.frontend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlife.fitness.entity.AuditingType;

@Controller
public class testController {


	@RequestMapping("/index.html")
	public String add(AuditingType auditingType) {
		return "success";
	}
}
