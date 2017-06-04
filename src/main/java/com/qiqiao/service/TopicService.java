package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Board;
import com.qiqiao.model.Section;
import com.qiqiao.model.Topic;

public interface TopicService extends BaseDao<Topic> {
	
	/**
	  * 某一板块的主题列表
	  * @param board
	  * @return List<Topic>
	  * @throws
	 */
	List<Topic> findByBoard(Board board);

	/**
	 * 将某一板块的主题所属的分区换成section分区
	 * @param board
	 * @param section
	 */
	void updateTopicSection(Board board, Section section);

	/**
	 * 删除某一板块的所有主题
	 * @param id 板块id
	 */
	void deleteByBoardId(Long id);

	/**
	 * 置顶主题
	 * 
	 * @param ids 需要置顶的主题id
	 * 
	 * @param state 置顶范围
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	void toTop(List<Long> ids, int state) throws Exception;

	/**
	 * 精华主题
	 * 
	 * @param ids 主题ids
	 * 
	 * @param scope 0/解除精华 1/精华主题
	 */
	void digest(List<Long> ids, String scope);
	
	/**
	 * 推荐主题
	 * 
	 * @param ids 主题ids
	 * 
	 * @param scope 0/解除推荐 1/推荐主题
	 */
	void recommend(List<Long> ids, String scope);

	/**
	 * 删除主题
	 * 
	 * @param ids
	 * 
	 * @author wangwei(wangwei_baosight@163.com)
	 */
	void delete(List<Long> ids);

	/**
	 * 删除某一分区下的所有主题
	 * @param id 分区Id
	 */
	void deleteBySectionId(Long id);

	/**
	 * 将某一分区下的主题最后回帖置为空
	 * @param id
	 */
	void setLastReply(Long id);

}
