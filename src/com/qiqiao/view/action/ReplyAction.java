package com.qiqiao.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Reply;
import com.qiqiao.model.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {
	
	private static final long serialVersionUID = 1L;
	private Long topicId;
	/**
	  * 回帖界面
	  * @return String
	  * @throws
	 */
	public String addUI() throws Exception {

		return "addUI";
	}
	
	/**
	 * 回帖
	 * @return String
	 * @throws
	 */
	public String add() throws Exception {
		//获取回帖的主题
		Topic topic = topicService.getById(topicId);
		//设置属性
		//model.setId(id);
		//model.setContent(content);
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		model.setState(Reply.STATE_NORMAL);
		model.setTopic(topic);
		model.setUser(getCurrentUser());
		model.setBoardId(topic.getBoard().getId());
		model.setSectionId(topic.getSection().getId());
		//存入数据库
		replyService.save(model);
		//维护论坛数据（在save方法里实现）
		return "toTopicShow";
	}
	
	/**
	 * 屏蔽回复
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 * 
	 */
	public String shieldReply() {
		Reply reply = replyService.getById(model.getId());
		reply.setState(1);
		replyService.save(reply);
		return "toTopicShow";
	}

	//--------------------------------------
	
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
}
