package com.qiqiao.model;

import java.util.Date;
import java.util.Set;

import com.qiqiao.util.TimeUtil;

/**
 * 主题实体
 * 
 */
public class Topic {

	public static final int STATE_NORMAL = 0;//普通状态
	public static final int STATE_DELETE = 1;//删除状态
	public static final int STATE_COLSE = 2;//关闭状态（不可回复）

	public static final int TOP_0 = 0;//不置顶
	public static final int TOP_1 = 1;//板块置顶
	public static final int TOP_2 = 2;//分区置顶
	public static final int TOP_3 = 3;//全局置顶
	
	private Long id;//
	private String title;//主题标题
	private String content;//主题内容
	private Date postTime;//主题发表时间
	private String ipAddr;//发表主题的ip地址
	private int topScope;//主题置顶范围
	private int replyCount;//主题回复数
	private int visits;//主题查看次数
	private String highColor;//主题高亮颜色
	private boolean digest;//主题是否精华(0,代表精华，1，代表普通)
	private boolean recommend;//主体是否被推荐
	private int state;//主题的状态（正常 or 删除）
	private int isAttach;//是否有附件
	private Board board;//主题所在板块
	private Section section;//主题所在分区
	private User user;//主题发表人
	private Reply lastReply;//最后回复人
	private Date lastUpdateTime;//最后更新时间
	private Set<Attach> attaches;//主题附件
	private Set<Reply> replies;//主题的回复
	private String showPostTime;//发帖时间显示
	private String showLastUpdateTime;//最后更新时间显示
	private Long param = 0L;//主题的其他一些属性，0，什么都没有，1，图片主题
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getTopScope() {
		return topScope;
	}
	public void setTopScope(int topScope) {
		this.topScope = topScope;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public String getHighColor() {
		return highColor;
	}
	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}
	public boolean isDigest() {
		return digest;
	}
	public void setDigest(boolean digest) {
		this.digest = digest;
	}
	public boolean isRecommend() {
		return recommend;
	}
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	
	public int getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(int isAttach) {
		this.isAttach = isAttach;
	}
	public Set<Attach> getAttaches() {
		return attaches;
	}
	public void setAttaches(Set<Attach> attaches) {
		this.attaches = attaches;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String getShowPostTime() {
		//先判断发帖时间是否为空
		if(postTime == null) {
			return showPostTime = "";
		}else {
			showPostTime = TimeUtil.getShowTime(postTime, new Date());
		}
		return showPostTime;
	}
	public void setShowPostTime(String showPostTime) {
		this.showPostTime = showPostTime;
	}
	public String getShowLastUpdateTime() {
		//先判断最后回帖是否为空
		if(lastReply == null) {
			return showLastUpdateTime = TimeUtil.getShowTime(lastUpdateTime, new Date());
		}else {
			showLastUpdateTime = TimeUtil.getShowTime(lastReply.getPostTime(), new Date());
		}
		return showLastUpdateTime;
	}
	public void setShowLastUpdateTime(String showLastUpdateTime) {
		this.showLastUpdateTime = showLastUpdateTime;
	}
	public Long getParam() {
		return param;
	}
	public void setParam(Long param) {
		this.param = param;
	}
	
}
