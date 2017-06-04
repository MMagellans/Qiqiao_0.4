package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Reply;
import com.qiqiao.model.Topic;

public interface ReplyService extends BaseDao<Reply> {
	
	/**
	  * 查询某一主题的全部回帖
	  * @param @param topic
	  * @return List<Reply>
	  * @throws
	 */
	List<Reply> findByTopic(Topic topic);

	void deleteByBoardId(Long id);

	/**
	 * 删除某一分区下的所有回帖
	 * @param id
	 */
	void deleteBySectionId(Long id) throws Exception;

}
