package com.qiqiao.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Board;
import com.qiqiao.model.Section;
import com.qiqiao.model.Topic;
import com.qiqiao.model.User;
import com.qiqiao.util.HqlHelper;
import com.qiqiao.vo.BoardVo;
import com.qiqiao.vo.BoardVo2;
import com.qiqiao.vo.SectionVo;

@Controller
@Scope("prototype")
public class BoardAction extends ModelDrivenBaseAction<Board> {
	
	private static final long serialVersionUID = 1L;
	private int flag = 0;
	private Long sid;
	private String sname;
	private String result;
	private String userIds;
	private Long sourcebid;
	private Long objbid;
	/**
	  * 板块主题列表
	  * @return String
	  * @throws
	 */
	public String show() throws Exception{
		//准备数据
		//1，版块信息
		Board board = boardService.getById(model.getId());
		if(board == null) {
			return "noexist";
		}
		ActionContext.getContext().put("board", board);
		//2，今日文章数
		int todayArticleCount = boardService.todayArticle(model.getId());
		ActionContext.getContext().put("todayArticleCount", todayArticleCount);
		//3，主题信息列表
		//List<Topic> topicList = topicService.findByBoard(board);
		//准备数据：主题列表（分页信息,使用公共的方法，最终版）
//		List<Topic> testPageList = topicService.getPageBean(1,
//				new HqlHelper(Topic.class, "t")
//				.addCondition("t.board=?", board)
//				.addOrder("t.topScope",false)
//				.addOrder("t.lastUpdateTime",false))
//				.getRecordList();
//		for(Topic topic : testPageList) {
//			System.out.println(topic.getTitle());
//		}
		new HqlHelper(Topic.class, "t")
			.addCondition("t.board=?", board)
			.addCondition("t.state=?", 0)
			.addCondition("t.topScope=?", 0)
			.addOrder("t.topScope",false)
			.addOrder("t.lastUpdateTime",false)
			.buildPageBeanForStruts2(pageNum, topicService);
		//ActionContext.getContext().put("topicList", topicList);
		//System.out.println(model.getId());
		//查询置顶主题
		List<Topic> topList = boardService.findTopList(board.getSection().getId(),model.getId());
		ActionContext.getContext().put("topList", topList);
		return "show";
	}
	
	/**
	 * 添加板块页面
	 * @return
	 */
	public String addUI() throws Exception {
		//准备数据，分区信息列表
		List<SectionVo> sectionVos = sectionService.getSectionVos();
		ActionContext.getContext().put("sectionVos", sectionVos);
		return "addUI";
	}
	
	/**
	 * 添加版块
	 * @return
	 */
	public String add() throws Exception {
		//判断该分区板块数量是否已达到9个
		int count = sectionService.getBoardCount(model.getSection().getId());
		if(count >= 9) {
			ActionContext.getContext().put("operationInfo", "添加失败，该分区板块已满9个!");
		}else {
			//自动注入的属性：名称,所属分区和描述
	//		System.out.println("板块名称："+model.getName());
	//		System.out.println("板块所属分区id："+model.getSection().getId());
	//		System.out.println("板块所属分区名称："+model.getSection().getName());
	//		System.out.println("板块描述："+model.getDescription());
			//手动注入属性如下：
			model.setArticleCount(0);
			model.setCreateTime(new Date());
			model.setState(Board.STATE_NORMAL);
			model.setTopicCount(0);
			model.setSortNum(1);
			boardService.save(model);
			ActionContext.getContext().put("operationInfo", "添加成功!");
		}
		ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
		return "toForward";
	}
	
	/**
	 * 合并版块页面
	 * @return
	 * @throws Exception
	 */
	public String mergerUI() throws Exception {
		//准备数据，所有版块信息
		List<BoardVo2> boards = boardService.findAllBoardsVo2();
		ActionContext.getContext().put("boards", boards);
		return "mergerUI";
	}
	
	/**
	 * 板块合并
	 * @return
	 * @throws Exception
	 */
	public String merger() throws Exception {
		boardService.merger(sourcebid,objbid);
		return "toEdit";
	}
	
	/**
	 * 板块编辑页面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		//准备数据，分区信息，包括分区下的板块信息
		List<Section> sections = sectionService.findAll();
		List<Section> deletedSections = new ArrayList<Section>();
		List<Board> deletedBoards = new ArrayList<Board>();
		for(Section s : sections) {
			//如果分区状态为隐藏或删除则移去该分区
			if(s.getState() == Section.STATE_HIDE || s.getState() == Section.STATE_DELETE) {
				//sectionList.remove(s);在执行便利的时候不能进行remove，否则会抛ConcurrentModificationException异常
				deletedSections.add(s);
			}else {
				//s.setModerator(new HashSet<User>(userService.getModeratorsBySection(s.getId())));
				for(Board b : s.getBoards()) {
					if(b.getState() == Board.STATE_HIDE || b.getState() == Board.STATE_DELETE) {//假如该板块是隐藏或删除状态，则不予显示
						//s.getBoards().remove(b);
						deletedBoards.add(b);
					}
				}
			}
			s.getBoards().removeAll(deletedBoards);
			deletedBoards.clear();
		}
		sections.removeAll(deletedSections);
		ActionContext.getContext().put("sections", sections);
		return "editUI";
	}
	
	/**
	 * 板块编辑
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		
		return "edit";
	}
	
	/**
	 * 板块设置页面
	 * @return
	 * @throws Exception
	 */
	public String setUI() throws Exception {
		//准备数据，板块的VO对象
		BoardVo boardVo = boardService.getBoardVo(model.getId());
		ActionContext.getContext().getValueStack().push(boardVo);
		List<SectionVo> sectionVos = sectionService.getSectionVos();
		ActionContext.getContext().put("sectionVos", sectionVos);
		
		return "setUI";
	}
	
	/**
	 * 板块设置
	 * @return
	 * @throws Exception
	 */
	public String set() throws Exception {
		//System.out.println("规则长度：------------"+model.getDescription().length());
		//System.out.println(model.getDescription());
		Board board = null;
		if(flag == 0) {
			board = boardService.getById(model.getId());
			if(board != null) {
				//设置要更新的数据
				board.setName(model.getName());
				board.setState(model.getState());
				board.setDescription(model.getDescription());
				board.setHighColor(model.getHighColor());
				board.setSection(model.getSection());
				boardService.update(board);//持久化到数据库
				//将该板块所有主题的分区改成新的分区
				topicService.updateTopicSection(board,model.getSection());
				ActionContext.getContext().put("operationInfo", "板块基本设置更新成功");
			}else {
				ActionContext.getContext().put("operationInfo", "板块基本设置更新失败");
			}
			
		}else if(flag == 1) {
			ActionContext.getContext().put("operationInfo", "板块扩展设置更新成功");
		}else {
			
		}
		ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
		return "toForward";
	}
	
	/**
	 * 分区设置页面
	 * @return
	 * @throws Exception
	 */
	public String sectionSetUI() throws Exception {
		//准备数据，分区信息
		SectionVo sectionVo = sectionService.getSectionVoById(sid);
		ActionContext.getContext().put("sectionVo", sectionVo);
		//ActionContext.getContext().getValueStack().push(sectionVo);
		return "sectionSetUI";
	}
	
	/**
	 * 分区设置
	 * @return
	 * @throws Exception
	 */
	public String sectionSet() throws Exception {
		//分区更新
		Section section = sectionService.getById(sid);
		if(section != null) {
			section.setName(sname);
			sectionService.update(section);
			ActionContext.getContext().put("operationInfo", "分区名称更新成功");
		}else {
			ActionContext.getContext().put("operationInfo", "分区名称更新失败");
		}
		ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
		return "toForward";
	}
	
	/**
	 * 更新板块排序
	 * @return
	 * @throws Exception
	 */
	public String setBoardSort() throws Exception {
		Board board = boardService.getById(model.getId());
		if(board != null) {
			board.setSortNum(model.getSortNum());
			boardService.update(board);
			result = "{result:'success'}";
			
		}else {
			result = "{result:'error'}";
		}
		return "setBoardSort";
	}
	/**
	 * 板块的删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		//获取要删除的板块
		Board board = boardService.getById(model.getId());
		if(board != null) {
			//将板块的状态设置为删除
			boardService.delete(board.getId());
			//将该板块的主题设置为删除
			topicService.deleteByBoardId(board.getId());
			//将该板块的所有回帖设置为删除
			replyService.deleteByBoardId(board.getId());
			//TODO 有点小麻烦，根据每个主题删回帖
			ActionContext.getContext().put("operationInfo", "板块删除成功");
		}else {
			ActionContext.getContext().put("operationInfo", "板块删除失败");
		}
		ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
		return "toForward";
	}
	
	/**
	 * 设置版主页面
	 * @return
	 * @throws Exception
	 */
	public String setModeratorUI() throws Exception {
		//准备数据，板块信息
		Board board = boardService.getById(model.getId());
		if(board.getState() != Board.STATE_DELETE && board != null ) {
			ActionContext.getContext().put("board", board);
			return "setModeratorUI";
		}else {
			ActionContext.getContext().put("operationInfo", "该板块不存在或已被删除");
			ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
			return "toForward";
		}
		
	}

	/**
	 * 设置板块版主
	 * @return
	 * @throws Exception
	 */
	public String setModerator() throws Exception {
		//System.out.println(userIds+"------------------------------");
		//获取用户id
		String[] ids = userIds.split(",");
		//获取板块信息
		Board board = boardService.getById(model.getId());
		//System.out.println(ids+"------------------------------");
		if(board != null) {
			//将用户设置成版主
			for(String s : ids) {
				//System.out.println("用户id---------"+Long.getLong(s.trim())+"-------");
				//获取用户信息
				User user = userService.getById(Long.parseLong(s));
				if(user != null) {
					//System.out.println(user.getName());
					user.setBoard(board);
					userService.update(user);
				}else {
					System.out.println("user等于空");
				}
			}
			result = "版主设置成功！";
		}else {
			result = "该板块不存在或已被删除！";
		}
		return "setModerator";
	}
	
	/**************************************/

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Long getSourcebid() {
		return sourcebid;
	}

	public void setSourcebid(Long sourcebid) {
		this.sourcebid = sourcebid;
	}

	public Long getObjbid() {
		return objbid;
	}

	public void setObjbid(Long objbid) {
		this.objbid = objbid;
	}
	
}
