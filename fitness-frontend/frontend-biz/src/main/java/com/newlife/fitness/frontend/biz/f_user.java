package com.newlife.fitness.frontend.biz;

import org.apache.ibatis.annotations.Param;

import com.newlife.fitness.entity.AuditingType;
import com.newlife.fitness.entity.FUser;

public interface f_user {
	
	public int save(AuditingType auditingType);
	int FUser (@Param("phone") String phone);
	FUser select(@Param("pwd")String pwd,@Param("phone") String phone);
	   int insert(@Param("pwd")String pwd,@Param("phone") String phone,@Param("f_loginName")String f_loginName);
	   int insertSelective(FUser record);
	FUser showperson(@Param("loginname")String loginname);
	 public String  login (@Param("id") int id,@Param ("name") String name);
	 
	 
	 int updatevip(FUser record);
	
}
