package com.qiqiao.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Board;
import com.qiqiao.model.Reply;
import com.qiqiao.model.Topic;
import com.qiqiao.model.User;
import com.qiqiao.service.ReplyService;

@Service
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

	@SuppressWarnings("unchecked")
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		//TODO 维护论坛数据（用户个人回帖数、论坛板块的文章数，主题的回复次数，主题的最后回复,用户积分）
		//用户积分数添加，这里先固定为2，用户的回帖数加一
		User user = reply.getUser();
		user.setCredits(user.getCredits() + 2);
		user.setReplyCount(user.getReplyCount() + 1);
		getSession().update(user);
		//论坛版块文章数加一
		Board board = reply.getTopic().getBoard();
		board.setArticleCount(board.getArticleCount() + 1);
		getSession().update(board);
		//主题的回复次数加一,最后回复的更新
		Topic topic = reply.getTopic();
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastReply(reply);
		getSession().update(topic);
		
		getSession().save(reply);
	}

	public void deleteByBoardId(Long id) {
		getSession().createQuery(//
				"UPDATE Reply r SET r.state=? WHERE r.boardId=?")//
				.setParameter(0, Reply.STATE_DELETE)//
				.setParameter(1, id)
				.executeUpdate();
	}

	public void deleteBySectionId(Long id) throws Exception {
		getSession().createQuery(//
				"DELETE Reply r WHERE r.sectionId=?")//
				.setParameter(0, id)
				.executeUpdate();
	}
}
