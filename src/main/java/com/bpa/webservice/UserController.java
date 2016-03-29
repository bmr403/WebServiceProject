package com.bpa.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpa.dao.UserDAO;
import com.bpa.model.User;

public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	
	protected final Log logger
	
	
	
	@RequestMapping(value="/loginValidate",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginValidate(HttpServletRequest request) {
		logger.info("Login Validate Method Strarted.,");
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		try
		{
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			User user = null;
			if(userName == "" ||!userName.equals(null) || userName != null){
				user = new User();
				user.setUserName(userName);
			}
			if(password == "" ||!password.equals(null) || password != null){
				user.setPassword(password);
			}
			
			User loggedInUser = userDAO.getLoginVerificationByEmail(user);
			if(loggedInUser != null){
				logger.info("Login Validate Method Executed.,");
				modelMap.put("data", loggedInUser);
				modelMap.put("success", true);
			}
			
			return modelMap;
		}
		catch(Exception ex)
		{
			String msg = "Sorry problem in Login";
			modelMap.put("success", false);
			modelMap.put("message", msg);
			return modelMap;
		}
	}
	
}
