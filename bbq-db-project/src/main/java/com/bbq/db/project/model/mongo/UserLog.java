package com.bbq.db.project.model.mongo;

import java.util.Date;

import com.bbq.db.project.model.UserRole;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-8
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */
public class UserLog {

    private Integer userId;
    private String userName;
    private int userRoleId;
    private String actionName;
    private String actionTime;
    private String params;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    


	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
