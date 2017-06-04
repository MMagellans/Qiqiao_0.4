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

@Controller
@Scope("prototype")
public class SectionAction extends ModelDrivenBaseAction<Section> {
	
	private static final long serialVersionUID = 1L;
	private String result;
	/**
	  * 分区列表
	  * @return String
	  * @throws
	 */
	public String list() throws Exception {
		//准备数据
		//分区信息列表
		List<Section> sectionList = sectionService.findAll();
		ActionContext.getContext().put("sectionList", sectionList);
		List<Section> deletedSections = new ArrayList<Section>();
		List<Board> deletedBoards = new ArrayList<Board>();
		// TODO 今日帖数
		for(Section s : sectionList) {
			//如果分区状态为隐藏或删除则移去该分区
			if(s.getState() == Section.STATE_HIDE || s.getState() == Section.STATE_DELETE) {
				//sectionList.remove(s);在执行便利的时候不能进行remove，否则会抛ConcurrentModificationException异常
				deletedSections.add(s);
			}
			for(Board b : s.getBoards()) {
				if(b.getState() == Board.STATE_HIDE || b.getState() == Board.STATE_DELETE) {//假如该板块是隐藏或删除状态，则不予显示
					//s.getBoards().remove(b);
					deletedBoards.add(b);
				}else {
					b.setTodayArticleCount(boardService.todayArticle(b.getId()));
				}
			}
			s.getBoards().removeAll(deletedBoards);
			deletedBoards.clear();
		}
		sectionList.removeAll(deletedSections);
		return "list";
	}
	
	public String add() {
		try{
			//自动注入的属性：名称,描述
			//手动注入属性如下：
			model.setState(0);
			model.setCreateTime(new Date());
			model.setSortNum(100);
			sectionService.save(model);
			ActionContext.getContext().put("operationInfo", "添加成功!");
			ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
			return "toForward";
		}catch(Exception e) {
			ActionContext.getContext().put("operationInfo", "添加失败!");
			ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
			return "toForward";
		}
	}
	
	/**
	 * 更新分区排序
	 * @return
	 * @throws Exception
	 */
	public String setSectionSort() throws Exception {
		Section section = sectionService.getById(model.getId());
		if(section != null) {
			section.setSortNum(model.getSortNum());
			sectionService.update(section);
			result = "{result:'success'}";
		}else {
			result = "{result:'error'}";
		}
		return "setSectionSort";
	}
	
	public String delete() {
		try{
			//获取要删除的分区
			Section section = sectionService.getById(model.getId());
			if(section != null) {
				//将分区下所有主题的最户回帖置为空
				topicService.setLastReply(model.getId());
				//删除分区的所有回帖
				replyService.deleteBySectionId(model.getId());
				//删除分区下的所有主题
				topicService.deleteBySectionId(model.getId());
				//删除分区下的所有版块
				boardService.deleteBySectionId(model.getId());
				//删除分区
				sectionService.delete(model.getId());
				ActionContext.getContext().put("operationInfo", "板块删除成功");
			}else {
				ActionContext.getContext().put("operationInfo", "板块删除失败,分区不存在或已被删除");
			}
			ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
			return "toForward";
		}catch(Exception e) {
			ActionContext.getContext().put("operationInfo", "板块删除失败");
			ActionContext.getContext().put("toWhere", "boardAction_editUI.action");
			return "toForward";
		}
		
	}

	//-----------------------------------------------
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
