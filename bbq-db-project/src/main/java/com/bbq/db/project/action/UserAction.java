package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.ResultMessageConstants;
import bbq.db.project.dao.utils.StrutsUtil;

import com.bbq.db.project.model.Book;
import com.bbq.db.project.model.UserRole;
import com.bbq.db.project.model.mongo.UserLog;
import com.bbq.db.project.mongodb.MongoDBManager;
import com.bbq.db.project.service.BookService;
import com.bbq.db.project.service.UserRoleService;
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
	private UserRoleService userRoleService;
    @Autowired
    private BookService bookService;

	private String userName;
	private String password;
	private User user;
    private Integer userId;
    private Integer userRoleId;
    private List<Book> books;
    private List<User> users;
    private List<UserLog> userLogs;
	
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
                UserRole userRole = userRoleService.getUserRoleById(1);
                user.setUserRole(userRole);
                userService.insertUser(user);
                Map<String, Object> session = ActionContext.getContext().getSession();
                session.put("user", user);
                map.put("code", Constants.CODE_SUCCESS);
                MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Add User",
                        JSONObject.fromObject(user).toString());
            } else if(user.getUserId() > 0){
                UserRole userRole = userRoleService.getUserRoleById(userRoleId);
                user.setUserRole(userRole);
                userService.updateUser(user);
                MongoDBManager.getDBInstance().add(user.getUserId(), user.getUserName(), user.getUserRole().getRoleId(), "Update User",
                        JSONObject.fromObject(user).toString());
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

    @Action(value = "showAllUsers", results = { @Result(name = "success", location = "showAllUsers.jsp"),
                                                @Result(name = "error", location = "preLogin.jsp")})
    public String showAllUsers(){

        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            User user = (User)session.get("user");
            if(user != null && user.getUserRole().getRoleId() == Constants.ADMIN) {
                 users = userService.getUsersByType(userRoleService.getUserRoleById(Constants.NORMAL_USER));
            } else {
                 this.message = ResultMessageConstants.NOT_ADMIN;
                 return ERROR;
            }
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:userInfo][][error:{}]", e);
        }

        return SUCCESS;
    }

    @Action(value = "getUserById")
    public String getUserById(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user = userService.getUserById(userId);
            if(user != null) {
                map.put("user", user);
                map.put("code", Constants.CODE_SUCCESS);
            } else {
                map.put("code", Constants.NO_DATA);
            }
        } catch (Exception e) {
            logger.error("error::module:UserAction][action:addUser][][error:{}]", e);
            map.put("code", Constants.INNER_ERROR);
        }

        StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
        return null;
    }

    @Action(value = "userLogs", results = { @Result(name = "success", location = "userLogs.jsp"),
                                                @Result(name = "error", location = "preLogin.jsp")})
    public String userLogs(){

        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            User user = (User)session.get("user");
            if(user != null && user.getUserRole().getRoleId() == Constants.ADMIN) {
                userLogs = MongoDBManager.getDBInstance().getUserLogs();
            } else {
                this.message = ResultMessageConstants.NOT_ADMIN;
                return ERROR;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	
    public List<UserLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<UserLog> userLogs) {
        this.userLogs = userLogs;
    }
}
