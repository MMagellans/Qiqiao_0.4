package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Board;
import com.qiqiao.model.Topic;
import com.qiqiao.vo.BoardVo;
import com.qiqiao.vo.BoardVo2;

public interface BoardService extends BaseDao<Board> {
	
	/**
	  * 今日文章数（包括主题和回帖）
	  * @param id
	  * @return int
	  * @throws
	 */
	int todayArticle(Long id) throws Exception;
	
	/**
	 * 根据板块id获取板块的值对象
	 * @param id
	 * @return
	 */
	BoardVo getBoardVo(Long id);
	
	public List<BoardVo2> findAllBoardsVo2();

	/**
	 * 合并版块
	 * @param sourcebid 源板块id
	 * @param objbid 目标版块id
	 */
	void merger(Long sourcebid, Long objbid);

	/**
	 * 查询置顶主题（全局置顶，分区置顶，板块置顶）
	 * @param id 分区id
	 * @param id2 板块id
	 * @return
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	List<Topic> findTopList(Long id, Long id2);
	
	/**
	 * 删除某一分区下的版块
	 * @param id
	 */
	void deleteBySectionId(Long id);

}
