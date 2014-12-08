package com.bbq.db.project.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbq.db.project.model.Message;
import com.bbq.db.project.model.User;
import com.bbq.db.project.service.MessageService;
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
	List<Message> msgs;


    
    @Action(value = "view", results ={ @Result(name = "success", location = "view.jsp") })
    public String view(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	 msgs=messageService.getMessageByReceiverID(user);
    	 return SUCCESS;
    }
    

  @Action(value = "send", results = { @Result(name = "success", location = "message.jsp") })
  public String send(Message message){
      try {
          if(message == null){
              logger.error("error: [module:MessageAction][action:send][][error:{empty params}]");
          } else {
          	messageService.insertMessage(message);
          }
      } catch (Exception e) {
          logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
      }
      return SUCCESS;
  }

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}
    
   


}
