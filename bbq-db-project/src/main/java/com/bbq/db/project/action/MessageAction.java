package com.bbq.db.project.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbq.db.project.dao.utils.Constants;
import bbq.db.project.dao.utils.StrutsUtil;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbq.db.project.model.Message;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.MessageService;
import com.bbq.db.project.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author zhuoli
 *
 */

@Namespace("/message")
public class MessageAction extends BaseAction {
	@Autowired
    private MessageService messageService;
	@Autowired
	private UserService userService;
	List<Message> msgs;
	String receiver,title,content;
    
    @Action(value = "view", results ={ @Result(name = "success", location = "view.jsp") })
    public String view(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	 msgs=messageService.getMessageByReceiverID(user);
    	 return SUCCESS;
    }
   
   
    @Action(value = "newMessage", results = { @Result(name = "success", location = "message.jsp") })
    public String newMessage(){

        return SUCCESS;
    }

  @Action(value = "send")
  public String send(){
      Map<String, Object> map = new HashMap<String, Object>();
      Map<String, Object> session = ActionContext.getContext().getSession();
      User user = (User)session.get("user");
      Message msg=new Message();
      User receiver =userService.getUserByUserNameAndPassword(this.receiver, null);

      if(user==null || receiver == null|| title==null || content==null){
          logger.error("error::module:MessageAction][action:send][][error:{receiver name not exist}]");
          map.put("code", Constants.INVALID_PARAMS);
      }
	  else{
	      msg.setSender(user);
	      msg.setContent(content);
	      msg.setTitle(title);
	      msg.setReceiver(receiver);
	      msg.setTime(new Date());
	      try {
	          	messageService.insertMessage(msg);
	      } catch (Exception e) {
	          logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
	      }
	
	      StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
      }
      return null;
  }

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}




	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
