package com.newlife.fitness.frontend.dao;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.newlife.fitness.entity.FUser;

@Component("fuserdao")
public interface FUserDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(@Param("pwd")String pwd,@Param("phone") String phone,@Param("f_loginName")String f_loginName);
    
    int insertSelective(FUser record);
    
    int updatevip(FUser record);

     int FUser(@Param("phone") String phone);
     
    int updateByPrimaryKeySelective(FUser record);

    int updateByPrimaryKey(FUser record);
    
   public  FUser select(@Param("pwd")String pwd,@Param("phone") String phone);
   
   public  FUser showperson(@Param("loginname")String loginname);
   
   
   public String  login (@Param("id") int id,@Param ("name") String name);
   
   
   
   
   
   
}