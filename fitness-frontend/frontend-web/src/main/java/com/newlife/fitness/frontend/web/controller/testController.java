package com.newlife.fitness.frontend.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;


import com.alibaba.fastjson.JSON;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.newlife.fitness.entity.Comments;
import com.newlife.fitness.entity.FUser;
import com.newlife.fitness.entity.Order;
import com.newlife.fitness.frontend.biz.Fuserservice;
import com.newlife.fitness.frontend.biz.commentsserviceimpl;
import com.newlife.fitness.frontend.biz.orderserviceimpl;
import com.newlife.fitness.frontend.web.utils.AlipayConfig;

import com.wzp.sendcode.MD5Util;

@Controller
@RequestMapping("/user")
public class testController {
	Logger logger= LoggerFactory.getLogger(testController.class);
	@Autowired
	@Qualifier("fuserservice")
	private    Fuserservice service;
	public Fuserservice getService() {
		return service;
	}
	public void setService(Fuserservice service) {
		this.service = service;
	}

	@Resource
	private  orderserviceimpl   order;
	public orderserviceimpl getOrder() {
		return order;
	}
	public void setOrder(orderserviceimpl order) {
		this.order = order;
	}
	@Resource
	private  commentsserviceimpl    comservice;
	
	
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public commentsserviceimpl getComservice() {
		return comservice;
	}
	public void setComservice(commentsserviceimpl comservice) {
		this.comservice = comservice;
	}
	@RequestMapping("/login")
	public String add(@RequestParam("name")String name,@RequestParam("pwd")String  pwd) {



		FUser u=service.select(name, pwd);

		System.out.println("---------------ok"+u.getF_userName());




		return "index";
	}

	@RequestMapping(value="/phone.html",method=RequestMethod.GET)
	@ResponseBody
	public String phone(@RequestParam("phone") String haoma){




		int shuzi=service.FUser(haoma);







		if(shuzi>0){
			return JSON.toJSONString("ok");
		}else{
			return JSON.toJSONString("no");
		}









	}
	@RequestMapping(value="/yan.html",method=RequestMethod.GET)
	@ResponseBody
	public String yan(@RequestParam("value") String value,@RequestParam("phone") String haoma){


		System.out.println("验证码"+value  +"号码"+haoma);


		return JSON.toJSONString("ok");

	} 
	@RequestMapping(value="/name.html",method=RequestMethod.GET)
	@ResponseBody
	public String name(@RequestParam("name") String name,@RequestParam("id")int id){


		System.out.println("名字"+name+ "id"+id);
		String mingzi=service.login(0,name);

		if(mingzi!=null){
			return JSON.toJSONString("no");
		}else{
			return JSON.toJSONString("ok");
		}
	} 

	@RequestMapping(value="/zhuce.html",method=RequestMethod.GET)

	public String zhuce(@RequestParam("pwd") String pwd,@RequestParam("phone") String phone,Model model,HttpSession req){
		MD5Util  jiami=new MD5Util();
		String mi=jiami.md5Pass5(pwd);

		int max=1000;
		int min=10;
		Random random = new Random();

		int s = random.nextInt(max)%(max-min+1) + min;
		System.out.println(s);

		String name="用户"+s;

		int shuzi=service.insert(mi, phone,name);
		if (shuzi>0) {


			System.out.println("---"+name);

			model.addAttribute("success"," <div class='alert alert-success alert-dismissable'><button type='button' class='close' data-dismiss='alert'aria-hidden='true'>&times;</button> 尊敬的用户"+s+",你好注册成功！</div>");

			return "deng";
		}else{


			return "zhuce";


		}








	}
	@RequestMapping(value="/denglu.html",method=RequestMethod.GET)

	public String denglu(@RequestParam("pwd") String pwd,@RequestParam("phone") String phone,Model model,HttpSession req){
		MD5Util  jiami=new MD5Util();
		String mi=jiami.md5Pass5(pwd);


		System.out.println("进入登录");
		System.out.println("====================="+mi);
		System.out.println("====================="+phone);

		FUser  shuzi=new FUser();	

		shuzi=service.select(mi, phone);
		ServletContext application = req.getServletContext();
		String name=null;

		if(shuzi!=null){
			name=shuzi.getF_loginName();

			if(shuzi.getF_isVip() !=null  && shuzi.getF_isVip().equals("是")){
				application.setAttribute("color","red"); 

				model.addAttribute("welcome"," <div class='alert alert-success alert-dismissable'><button type='button' class='close' data-dismiss='alert'aria-hidden='true'>&times;</button> 尊敬的vip用户"+name+",欢迎您！！</div>");
			}else{

				application.setAttribute("color","black");
			}




			application.setAttribute("isname",name);
			if (shuzi.getF_imgUrl()!=null) {
				application.setAttribute("f_imgUrl", shuzi.getF_imgUrl());
			}else{

				application.setAttribute("f_imgUrl", "static/images/noimage.jpg");

			}



			System.out.println("---"+name);


			return "index";


		}else{
			System.out.println("密码或用户名错误!");
			model.addAttribute("error"," <div class='alert alert-danger alert-dismissable'><button type='button' class='close' data-dismiss='alert'aria-hidden='true'>&times;</button> 密码或用户名错误!</div>");

			return "deng";



		}









	}
	@RequestMapping(path = {"/changeinfo"}, method = {RequestMethod.POST})

	public  String  changeinfo(@RequestParam("pic1") MultipartFile data,HttpServletRequest re,@RequestParam("username")String username,
			@RequestParam("loginname")String loginname,@RequestParam("email")String email,@RequestParam("sex")String sex,
			@RequestParam("phone")String phone,@RequestParam("address")String address,Model  model,@RequestParam("id")String id,HttpSession req){

		String picpath=null;
		int i=0;
		if(id!=null){
			i=Integer.valueOf(id);
		}
		// 获得文件：        email,sex,birthday,address

		System.out.println("================进入了方法"+loginname);

		String ispicpath=null;

		if(!data.isEmpty()){
			String path=re.getSession().getServletContext().getRealPath("static"+File.separator+"uploadfiles");
			picpath="static"+File.separator+"uploadfiles";
			logger.info("path----"+path);//路径
			String oldfilename=data.getOriginalFilename();
			logger.info("oldfile----"+oldfilename);// 原文件名
			String prefix=FilenameUtils.getExtension(oldfilename);
			logger.info("原文件后缀"+prefix);//原文件后缀
			int filesize=500000000;
			logger.info("uploadfile size"+data.getSize());
			if(data.getSize()>filesize){
				re.setAttribute("fileUploadError", "上传文件不能大于 50000kb");
				return "app_add";
			}else if(prefix.equalsIgnoreCase("jpg")
					||prefix.equalsIgnoreCase("png")
					||prefix.equalsIgnoreCase("pneg")
					){

				//	re.setAttribute("fileUploadError", "格式上传错误,请重新选择");
				String filename=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
				logger.info("new filename--"+data.getName());
				File targetfile=new File(path,filename);
				if(!targetfile.exists()){

					targetfile.mkdirs();
				}
				try{
					data.transferTo(targetfile);
				}catch(Exception e){
					e.printStackTrace();
					re.setAttribute("fileUploadError", "上传失败");
					return "add_info" ;
				}
				ispicpath=picpath+File.separator+filename;
			}else {
				re.setAttribute("fileUploadError", "格式上传错误,请重新选择");
				return  "person";
			}


		}
		FUser f=new FUser();
		f.setF_address(address);
		f.setF_loginName(loginname);
		f.setF_email(email);
		f.setF_sex(sex);
		f.setF_imgUrl(ispicpath);
		f.setF_phone(phone);
		f.setF_userName(username);
		f.setId(i);

		int shuzi=service.insertSelective(f);

		ServletContext application = req.getServletContext();

		if(shuzi>0){

			System.out.println("进入了修改页面");


			application.setAttribute("isname", loginname);

			application.setAttribute("f_imgUrl",ispicpath);
		}
		return "index";

	}


	/**
	 * 查看个人信息
	 * 
	 * 
	 */
	@RequestMapping("/showpersoninfo")
	public   String  showpersoninfo(@RequestParam("loginname")String loginname,Model  model){

		System.out.println("进入 个人信息页面"+loginname);


		if(loginname==null){

			return "index";

		}else{

			FUser  showperson=service.showperson(loginname);
			if (showperson!=null) {
				if(showperson.getF_sex()!=null){
					if(showperson.getF_sex().equals("男")){
						model.addAttribute("nan","checked='checked'");
					}else if(showperson.getF_sex().equals("女")){
						model.addAttribute("nv","checked='checked'");
					}
				}else{
					model.addAttribute("nan","checked");
				}
				model.addAttribute("fuser", showperson);



				return "person";
			}else{


				return "index";


			}

		}










	}

	@RequestMapping("/zhifu")
	public  void pa(HttpServletRequest request,HttpServletResponse response,Model  model) throws IOException, AlipayApiException{
		System.out.println("================进入了支付方法");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.paytest);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//商品描述，可空
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		//输出
		PrintWriter out;

		out = response.getWriter();
		out.println(result);

	}

	@RequestMapping("/paytest")
	public  String  paytest(HttpServletRequest request,HttpServletResponse response,HttpSession req,Model  model) throws AlipayApiException, IOException{
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		PrintWriter out;

		out = response.getWriter();


		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");


			ServletContext application = req.getServletContext();

			String loginname=(String) application.getAttribute("isname");

			FUser  f=new FUser();
			//ordeNumber,orderName,orderPrice,createDate,isPay,fUId
			//isvip,bgintime,endtime

			f.setF_isVip("是");
			f.setF_vipBegiTtime(new Date());
			f.setF_vipEndTime(new Date());
			f.setF_loginName(loginname);
			service.updatevip(f);

			String  orderNumber=out_trade_no;
			String orderName=loginname+"购买了vip";
			String  orderPrice=total_amount;
			Date  createDate=new Date();
			char  isPlay=1;
			int fUId= service.showperson(loginname).getId();
			Order  o= new Order();
			o.setOrderPrice(orderPrice);
			o.setOrderNumber(orderNumber);
			o.setOrderName(orderName);
			o.setCreateDate(createDate);
			o.setfUId(fUId);
			o.setIsPlay(isPlay);
			order.insertorder(o);

			application.setAttribute("color", "red");
			model.addAttribute("actionvip"," <div class='alert alert-success alert-dismissable'><button type='button' class='close' data-dismiss='alert'aria-hidden='true'>&times;</button> 尊敬的用户"+loginname+",你好vip开通成功！</div>");

			return  "index";



			//out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
		}else {
			out.println("验签失败");
			return  "ii";
		}
		//——请在这里编写您的程序（以上代码仅作参考）——



	}
	@RequestMapping("/notify_url")
	public   void  notic(HttpServletRequest request,HttpServletResponse response) throws IOException, AlipayApiException{
		/* *
		 * 功能：支付宝服务器异步通知页面
		 * 日期：2017-03-30
		 * 说明：
		 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
		 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


		 *************************页面功能说明*************************
		 * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
		 * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
		 * 如果没有收到该页面返回的 success 
		 * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
		 */

		//获取支付宝POST过来反馈信息

		PrintWriter out = response.getWriter();


		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
			1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			4、验证app_id是否为该商户本身。
		 */
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}


			out.println("success");

		}else {//验证失败
			out.println("fail");

			//调试用，写文本函数记录程序运行情况是否正常
			//String sWord = AlipaySignature.getSignCheckContentV1(params);
			//AlipayConfig.logResult(sWord);
		}

		//——请在这里编写您的程序（以上代码仅作参考）——


	}
	@RequestMapping("/exist")
	public  String exist(HttpSession req){

		System.out.println("进入了清空");


		ServletContext application = req.getServletContext();

		application.removeAttribute("isname");
		application.removeAttribute("f_imgUrl");

		return "index";









	}

	@RequestMapping("/sendinfo01")
	public   void sendinfo01(@RequestParam("content_img") MultipartFile[]multipartfiles,@RequestParam("title")String title,@RequestParam("content")String content,HttpServletRequest  req) throws IllegalStateException, IOException{
		String path=req.getSession().getServletContext().getRealPath("static"+File.separator+"uploadfiles");
		String[] filename=new String[multipartfiles.length];
		if(multipartfiles != null && multipartfiles.length != 0){
			if(null != multipartfiles && multipartfiles.length > 0){
				//遍历并保存文件


				for (int i = 0; i < multipartfiles.length; i++) {
					MultipartFile file=multipartfiles[i];
					file.transferTo(new File(path,file.getOriginalFilename()));
					filename[i]="static"+File.separator+"uploadfiles" +File.separator+file.getOriginalFilename();
				}



			}
		}
		String endpath=null;//最后存到数据库的路径以$为分隔符
		for (int i = 0; i < filename.length; i++) {
			System.out.println("==========================="+filename[i]);


		}
		StringBuffer s4 = new StringBuffer();
		for (String string : filename) {
			s4.append(string+"$");
		}

		endpath=s4.toString();

		System.out.println("最终路径=============================="+endpath);
		
		/*Comments   co=new Comments();
		
		co.setComment_img(endpath);
		
		co.setComment_title(title);
		
		co.setCommentTime(new Date());*/
		
		




	}







	
	
	
	
	
	
}
	
	
	
	

	
	
	
	
	
	
	
	

