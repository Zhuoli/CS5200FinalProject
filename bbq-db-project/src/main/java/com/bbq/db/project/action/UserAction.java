package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.Map;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
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
	
	@Action(value = "login")
	public String getUserByUserAndPassword(){
		   
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(userName == null || password == null){
                logger.error("error::module:UserAction][action:login][][error:{empty params}]");
                map.put("code", Constants.INVALID_PARAMS);
            } else {
                user = userService.getUserByUserNameAndPassword(userName, password);
                if(user != null) {
                    Map<String, Object> session = ActionContext.getContext().getSession();
                    session.put("user", user);
                    map.put("user", user);
                    map.put("code", Constants.CODE_SUCCESS);
                } else {
                    map.put("code", Constants.NO_DATA);
                }
            }
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:login][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
        }

        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
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
