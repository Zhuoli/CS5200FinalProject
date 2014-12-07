package com.bbq.db.project.action;


import com.bbq.db.project.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * action基础类
 *
 * @author : lishunlong
 *         Date: 11-2-3
 *         Time: 下午3:13
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    public static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
    protected HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;
    protected String code;
    protected String message;
    protected String host;
    protected String role;
    protected String currentDate;
    protected String accessDomain;
    protected String openApi;

    protected Integer pageNo;
    protected Integer pageSize;
    protected String orderBy;
    protected String order;
    protected User sessionUser;

    /**
     * request getter
     *
     * @return request
     */
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    /**
     * request setter
     *
     * @param servletRequest request
     */
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    /**
     * response getter
     *
     * @return response
     */
    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    /**
     * response setter
     *
     * @param servletResponse response
     */
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    /**
     * code getter
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * code setter
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * message getter
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * message setter
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getAccessDomain() {
        return accessDomain;
    }

    public void setAccessDomain(String accessDomain) {
        this.accessDomain = accessDomain;
    }

    public String getOpenApi() {
        return openApi;
    }

    public void setOpenApi(String openApi) {
        this.openApi = openApi;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
