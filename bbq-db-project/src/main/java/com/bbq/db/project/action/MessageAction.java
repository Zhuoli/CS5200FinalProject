package com.bbq.db.project.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbq.db.project.dao.utils.StrutsUtil;
import net.sf.json.JSONObject;
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
	Message msg;
    
    @Action(value = "view", results ={ @Result(name = "success", location = "view.jsp") })
    public String view(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User)session.get("user");
    	 msgs=messageService.getMessageByReceiverID(user);
    	 return SUCCESS;
    }
   
   
    @Action(value = "newMessage", results = { @Result(name = "success", location = "message.jsp") })
    public String newMessage(){

        try {

        } catch (Exception e) {
            logger.error("error: [module:MessageAction][action:newMessage][][error:{}]", e);
        }

        return SUCCESS;
    }

  @Action(value = "send")
  public String send(){
	  logger.error("send received");
      Map<String, Object> map = new HashMap<String, Object>();

      try {
          if(msg == null){
              logger.error("error: msg is null [module:MessageAction][action:send][][error:{empty params}]");
          } else {
          	messageService.insertMessage(msg);
          }
      } catch (Exception e) {
          logger.error("error: [module:BookOrderAction][action:get][][error:{}]", e);
      }

      StrutsUtil.renderJson(JSONObject.fromObject(map).toString());
      return null;
  }

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

	public Message getmsg() {
		return msg;
	}


	public void setmsg(Message msg) {
		this.msg = msg;
	}
}
