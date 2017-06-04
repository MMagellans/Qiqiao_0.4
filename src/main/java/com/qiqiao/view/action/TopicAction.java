package com.qiqiao.view.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Attach;
import com.qiqiao.model.Board;
import com.qiqiao.model.Reply;
import com.qiqiao.model.Topic;
import com.qiqiao.util.HqlHelper;
import com.qiqiao.vo.BoardVo2;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {

	private static final long serialVersionUID = 1L;
	private Long boardId = 0L;
	private String author;//主题发表作者
	private String titleKey;//标题关键字
	private int visitsLess = 0;//浏览次数少于
	private int visitsGreater = 0;//浏览次数大于
	private int repliesLess = 0;//回复次数少于
	private int repliesGreater = 0;//回复次数大于
	private String createTimeLess;//发表时间早于
	private String createTimeGreater;//发表时间晚于
	private int isDigest;//是否精华
	private int state;//主题状态
	
	private int option;//批量操作
	
	private List<File> uploads;
	private List<String> uploadsFileName;
	private List<String> uploadsContentType;
	
	private String tids;
	
	/**
	 * 发表主题页面
	 */
	public String addUI() {
		try{
			//准备数据
			//版块信息
			Board board = boardService.getById(boardId);
			ActionContext.getContext().put("board", board);
			return "addUI";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 发表主题
	 */
	public String add() {
		try{
			//设置属性
			//自动封装的属性
			//model.setId(id);
			//model.setTitle(title);
			//model.setContent(content);
			int isAttach = 0;
			//手动获取的属性
			Board board = boardService.getById(boardId);
			model.setUser(getCurrentUser());
			//System.out.println(getCurrentUser());
			model.setBoard(board);
			model.setSection(board.getSection());
			model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
			model.setPostTime(new Date());
			Attach attach = null;
			if(uploads != null) {
				isAttach = uploads.size();
			}
			model.setIsAttach(isAttach);
			topicService.save(model);
			//保存附件
			for(int i=0;i<isAttach;i++) {
				if(uploads.get(i) != null && uploads.get(i).getName() != null && !"".equals(uploads.get(i).getName())) {
					attach = new Attach();
					String path = saveUploadTempFile(uploads.get(i));
					attach.setName(uploadsFileName.get(i));
					String aa[] = uploadsFileName.get(i).split("\\.");
					String suffix = aa[aa.length-1];
					attach.setPath(path);
					attach.setSuffix(suffix);
					attach.setTopic(model);
					attachService.save(attach);
				}
			}
			ActionContext.getContext().put("operationInfo", "发帖成功");
			ActionContext.getContext().put("toWhere", "topicAction_show.action?id="+model.getId());
			return "toForward";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	  * 显示单个主题，回帖列表 
	  * @return String
	  * @throws
	 */
	public String show() {
		try{
			//准备数据
			//1,主题信息
			Topic topic = topicService.getById(model.getId());
			topic.setVisits(topic.getVisits() + 1);
			topicService.update(topic);
			ActionContext.getContext().put("topic", topic);
			//2,回帖列表
			//List<Reply> replyList = replyService.findByTopic(topic);
			//ActionContext.getContext().put("replyList", replyList);
			new HqlHelper(Reply.class, "r")
				.addCondition("r.topic=?", topic)
				.addOrder("r.postTime",true)
				.buildPageBeanForStruts2(pageNum, topicService);
			
			return "show";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 批量主题查询页面
	 * @return
	 * @throws Exception
	 */
	public String managerUI() {
		try{
		//准备数据，所有版块信息
		List<BoardVo2> boards = boardService.findAllBoardsVo2();
		boards.add(new BoardVo2(0L, "全部"));
		ActionContext.getContext().put("boards", boards);
		return "managerUI";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 批量主题查询
	 * @return
	 * @throws Exception
	 */
	public String manager() {
		try{
		HqlHelper hqlHelper = getHqlHelper();
//		HqlHelper hqlHelper = new HqlHelper(Topic.class, "t");
//		if(boardId != null&&boardId != 0) {
//			hqlHelper.addCondition("t.board.id=?", boardId);//板块条件
//		}else if(author != null && author.length() != 0) {
//			hqlHelper.addCondition("t.user.username = ?", author);//板块条件
//		}else if(titleKey != null && titleKey.length() != 0) {
//			hqlHelper.addCondition("t.title like ?", "'%"+titleKey+"%'");//标题关键字
//		}else if(visitsLess != 0) {
//			hqlHelper.addCondition("t.visits < ?", visitsLess);//浏览次数少于
//		}else if(visitsGreater != 0) {
//			hqlHelper.addCondition("t.visits > ?", visitsGreater);//浏览次数大于
//		}else if(repliesLess != 0) {
//			hqlHelper.addCondition("t.replies < ?", repliesLess);//回复次数少于
//		}else if(repliesGreater != 0) {
//			hqlHelper.addCondition("t.replies > ?", repliesGreater);//回复次数大于
//		}else if(createTimeLess != null && createTimeLess.length() != 0) {
//			hqlHelper.addCondition("t.postTime < ?", createTimeLess);//发帖时间早于
//		}else if(createTimeGreater != null && createTimeGreater.length() != 0) {
//			hqlHelper.addCondition("t.postTime > ?", createTimeGreater);//发帖时间晚于
//		}
//		hqlHelper.addCondition("t.state=?", state);//状态条件
//		hqlHelper.addOrder("t.lastUpdateTime",false);
		hqlHelper.buildPageBeanForStruts2(pageNum, topicService);
		//准备数据，所有版块信息
		List<BoardVo2> boards = boardService.findAllBoardsVo2();
		ActionContext.getContext().put("boards", boards);
		return "resultList";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 批量主题操作
	 * @return
	 * @throws Exception
	 */
	public String piliangOption(){
		try{
			//System.out.println(option);
			HqlHelper hqlHelper = getHqlHelper();
			String hql = hqlHelper.getQueryListHql().substring(4);
			System.out.println(hql);
			if(option==1) {
				hql = "update Topic t set t.state=1 "+getWhereHql();
				System.out.println(hql);
			}else if(option==3) {
				System.out.println("批量打开关闭操作,操作："+state);
			}else if(option==4) {
				System.out.println("批量置顶操作,操作："+model.getTopScope());
			}else if(option==5) {
				System.out.println("批量精华操作,操作："+model.isDigest());
			}
			ActionContext.getContext().put("operationInfo", "发帖成功");
			ActionContext.getContext().put("toWhere", "topicAction_managerUI.action");
			return "toForward";
		}catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	public HqlHelper getHqlHelper() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HqlHelper hqlHelper = new HqlHelper(Topic.class, "t");
		if(boardId != null&&boardId != 0) {
			hqlHelper.addCondition("t.board.id=?", boardId);//板块条件
		}
		if(author != null && author.length() != 0) {
			hqlHelper.addCondition("t.user.username = ?", author);//板块条件
		}
		if(titleKey != null && titleKey.length() != 0) {
			hqlHelper.addCondition("t.title like ?", "'%"+titleKey+"%'");//标题关键字
		}
		if(visitsLess != 0) {
			hqlHelper.addCondition("t.visits < ?", visitsLess);//浏览次数少于
		}
		if(visitsGreater != 0) {
			hqlHelper.addCondition("t.visits > ?", visitsGreater);//浏览次数大于
		}
		if(repliesLess != 0) {
			hqlHelper.addCondition("t.replies < ?", repliesLess);//回复次数少于
		}
		if(repliesGreater != 0) {
			hqlHelper.addCondition("t.replies > ?", repliesGreater);//回复次数大于
		}
		if(createTimeLess != null && createTimeLess.length() != 0) {
			Date date = format.parse(createTimeLess);
			hqlHelper.addCondition("t.postTime < ?", date);//发帖时间早于
		}
		if(createTimeGreater != null && createTimeGreater.length() != 0) {
			Date date = format.parse(createTimeGreater);
			hqlHelper.addCondition("t.postTime > ?", date);//发帖时间晚于
		}
		hqlHelper.addCondition("t.state=?", state);//状态条件
		hqlHelper.addOrder("t.lastUpdateTime",false);
		return hqlHelper;
	}
	
	public String getWhereHql() throws Exception {
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String hql = " where 1=1 ";
		if(boardId != null&&boardId != 0) {
			hql = hql + " and t.board.id="+boardId;
		}
		if(author != null && author.length() != 0) {
			hql = hql + " and t.user.username='"+author+"'";
		}
		if(titleKey != null && titleKey.length() != 0) {
			hql = hql + " and t.title like "+"'%"+titleKey+"%'";
		}
		if(visitsLess != 0) {
			hql = hql + " and t.visits < "+visitsLess;
		}
		if(visitsGreater != 0) {
			hql = hql + " and t.visits > "+visitsGreater;
		}
		if(repliesLess != 0) {
			hql = hql + " and t.replies < "+repliesLess;
		}
		if(repliesGreater != 0) {
			hql = hql + " and t.replies > "+repliesGreater;
		}
		if(createTimeLess != null && createTimeLess.length() != 0) {
			//Date date = format.parse(createTimeLess);
			hql = hql + " and t.postTime < '"+createTimeLess+"'";
		}
		if(createTimeGreater != null && createTimeGreater.length() != 0) {
			//Date date = format.parse(createTimeGreater);
			hql = hql + " and t.postTime > '"+createTimeGreater+"'";
		}
		hql = hql + " and t.state="+state;
		return hql;
	}
	
	/**
	 * 置顶主题
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	public String topTopic() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String vscope = ServletActionContext.getRequest().getParameter("scope");
		int scope = 0;
		if(vscope != null && !vscope.equals("")) {
			scope = Integer.parseInt(vscope);
		}
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			String[] tIds = tids.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i=0;i<tIds.length;i++){
				if(tIds[i] != null && !"".equals(tIds[i])) {
					Long id = Long.parseLong(tIds[i]);
					ids.add(id);
				}
			}
			topicService.toTop(ids,scope);
			if(scope == 0) {
				xmlDoc = "解除置顶成功！";
			}else xmlDoc = "置顶成功！";
		}catch(Exception e) {
			e.printStackTrace();
			if(scope == 0) {
				xmlDoc = "解除置顶失败！";
			}else xmlDoc = "置顶失败！";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 精华主题
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	public String digest() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String scope = ServletActionContext.getRequest().getParameter("scope");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			String[] tIds = tids.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i=0;i<tIds.length;i++){
				if(tIds[i] != null && !"".equals(tIds[i])) {
					Long id = Long.parseLong(tIds[i]);
					ids.add(id);
				}
			}
			topicService.digest(ids,scope);
			if("0".equals(scope)) {
				xmlDoc = "解除精华成功！";
			}else xmlDoc = "精华成功！";
		}catch(Exception e) {
			e.printStackTrace();
			if("0".equals(scope)) {
				xmlDoc = "解除置顶失败！";
			}else xmlDoc = "置顶失败！";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 推荐主题
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	public String recommend() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String scope = ServletActionContext.getRequest().getParameter("scope");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			String[] tIds = tids.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i=0;i<tIds.length;i++){
				if(tIds[i] != null && !"".equals(tIds[i])) {
					Long id = Long.parseLong(tIds[i]);
					ids.add(id);
				}
			}
			topicService.recommend(ids,scope);
			if("0".equals(scope)) {
				xmlDoc = "解除推荐成功！";
			}else xmlDoc = "推荐成功！";
		}catch(Exception e) {
			e.printStackTrace();
			if("0".equals(scope)) {
				xmlDoc = "解除推荐失败！";
			}else xmlDoc = "推荐失败！";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除主题
	 * 
	 * @return
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	public String deleteTopic() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String xmlStrat = "<xml><span>";
		String xmlEnd = "</span></xml>";
		String xmlDoc = "";
		try{
			String[] tIds = tids.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i=0;i<tIds.length;i++){
				if(tIds[i] != null && !"".equals(tIds[i])) {
					Long id = Long.parseLong(tIds[i]);
					ids.add(id);
				}
			}
			topicService.delete(ids);
				xmlDoc = "删除成功！";
		}catch(Exception e) {
			e.printStackTrace();
			xmlDoc = "删除失败！";
		}
		out.write(xmlStrat + xmlDoc + xmlEnd);
		out.flush();
		out.close();
		return null;
	}
	
	
	/*********************************/
	
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitleKey() {
		return titleKey;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public int getVisitsLess() {
		return visitsLess;
	}
	public void setVisitsLess(int visitsLess) {
		this.visitsLess = visitsLess;
	}
	public int getVisitsGreater() {
		return visitsGreater;
	}
	public void setVisitsGreater(int visitsGreater) {
		this.visitsGreater = visitsGreater;
	}
	public int getRepliesLess() {
		return repliesLess;
	}
	public void setRepliesLess(int repliesLess) {
		this.repliesLess = repliesLess;
	}
	public int getRepliesGreater() {
		return repliesGreater;
	}
	public void setRepliesGreater(int repliesGreater) {
		this.repliesGreater = repliesGreater;
	}
	public String getCreateTimeLess() {
		return createTimeLess;
	}
	public void setCreateTimeLess(String createTimeLess) {
		this.createTimeLess = createTimeLess;
	}
	public String getCreateTimeGreater() {
		return createTimeGreater;
	}
	public void setCreateTimeGreater(String createTimeGreater) {
		this.createTimeGreater = createTimeGreater;
	}
	public int getIsDigest() {
		return isDigest;
	}
	public void setIsDigest(int isDigest) {
		this.isDigest = isDigest;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<File> getUploads() {
		return uploads;
	}
	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}
	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}
	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}
	public List<String> getUploadsContentType() {
		return uploadsContentType;
	}
	public void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}

	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}
	
}
