package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.service.BookService;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbq.db.project.model.User;
import com.bbq.db.project.service.UserService;

@Namespace("/user")
public class UserAction extends BaseAction{

	@Autowired
	private UserService userService;
    @Autowired
    private BookService bookService;

	private String userName;
	private String password;
	private User user;
    private Integer userId;
    private List<Book> books;
	
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

    @Action(value = "preRegister", results = { @Result(name = "success", location = "register.jsp") })
    public String preRegister(){

        return SUCCESS;
    }

    @Action(value = "addUser")
    public String addUser(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User dbUser = userService.getUserByUserNameAndPassword(user.getUserName(), null);
            if(dbUser == null) {
                user.setRegisterTime(new Date());
                userService.insertUser(user);
                Map<String, Object> session = ActionContext.getContext().getSession();
                session.put("user", user);
                map.put("code", Constants.CODE_SUCCESS);
            } else {
                map.put("code", Constants.USER_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:addUser][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
        }

        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
        return null;
    }

    @Action(value = "preLogin", results = { @Result(name = "success", location = "preLogin.jsp") })
    public String preLogin(){

        return SUCCESS;
    }

    @Action(value = "loginOut")
    public String loginOut(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.remove("user");
            map.put("code", Constants.CODE_SUCCESS);
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:loginOut][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
        }

        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
        return null;
    }

    @Action(value = "userInfo", results = { @Result(name = "success", location = "userInfo.jsp") })
    public String userInfo(){

        try {
            if(userId == null) {
                return ERROR;
            } else {
                user = userService.getUserById(userId);
                books = bookService.getBookByUserId(userId);
            }
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:userInfo][][error:{}]", e);
        }

        return SUCCESS;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
