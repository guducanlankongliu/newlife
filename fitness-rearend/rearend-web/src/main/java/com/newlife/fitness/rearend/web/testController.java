package com.newlife.fitness.rearend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlife.fitness.entity.AuditingType;
import com.newlife.fitness.rearend.biz.AuditingTypeService;


@Controller
public class testController {

	@Autowired
	@Qualifier("auditingTypeService")
	private AuditingTypeService auditingTypeService;

	@RequestMapping("addAuditingType")
	public String add(AuditingType auditingType) {
		 System.out.println(auditingTypeService); 
		 System.out.println(auditingType);
		 int save = auditingTypeService.save(auditingType); 
		 System.out.println(save);
		return "success";
	}
}
