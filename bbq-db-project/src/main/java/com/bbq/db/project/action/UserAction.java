package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbq.db.project.model.User;
import com.bbq.db.project.service.UserService;

@Namespace("/user")
public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
	
	


	private String userName;
	private String password;
	private User user;
	
	 @Action(value = "getUser")
	    public String getUser(){
		   
		    Map<String, Object> map = new HashMap<String, Object>();
	        try {
	            if(userName == null | password == null){
	                logger.error("error::module:BookOrderAction][action:get][][error:{empty params}]");
	            } else {
	                user = userService.getUserByUserNameAndPassword(userName, password);
	                map.put("user", user);
	            }
	        } catch (Exception e) {
	            logger.error("error::module:BookOrderAction][action:get][][error:{}]", e);
	        }
	        
	        Struts2Utils.renderJson(JSON.toJSONString(map));
	        return null;
	    }
	 
	 public void setUserService(UserService userService) {
			this.userService = userService;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserService getUserService() {
			return userService;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}
}
