package com.qiqiao.service.impl;

import java.net.IDN;
import java.util.List;



import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Board;
import com.qiqiao.model.Section;
import com.qiqiao.model.Topic;
import com.qiqiao.service.TopicService;

@Service
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	public List<Topic> findByBoard(Board board) {
		// TODO 根据板块寻找主题列表
		return getSession().createQuery(//
				"FROM Topic t WHERE t.board=? ORDER BY t.postTime DESC")
				.setParameter(0, board)
				.list();
	}

	@Override
	public void save(Topic topic) {
		//设置剩余属性
		topic.setDigest(false);//不是精华帖
		topic.setHighColor(null);
		topic.setLastReply(null);
		topic.setRecommend(false);
		topic.setReplies(null);
		topic.setReplyCount(0);
		topic.setState(Topic.STATE_NORMAL);
		topic.setTopScope(Topic.TOP_0);
		topic.setVisits(0);
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic);
		//维护论坛数据，板块主题数，板块文章数,板块最后主题
		Board board = topic.getBoard();
		board.setTopicCount(board.getTopicCount() + 1);
		board.setArticleCount(board.getArticleCount() + 1);
		board.setLastTopic(topic);
		getSession().update(board);
		// TODO 维护个人数据，个人主题数
		
	}

	public void updateTopicSection(Board board, Section section) {
		String hql = "UPDATE Topic t SET t.section=? WHERE t.board=?";
		getSession().createQuery(hql).setParameter(0, section).setParameter(1, board).executeUpdate();
	}

	public void deleteByBoardId(Long id) {
		getSession().createQuery(//
				"UPDATE Topic t SET t.state=? WHERE t.board.id=?")//
				.setParameter(0, Topic.STATE_DELETE)//
				.setParameter(1, id)
				.executeUpdate();
	}

	public void toTop(List<Long> ids, int state) throws Exception {
		String id = "";
		for(int i=0;i<ids.size();i++) {
			if(i == (ids.size()-1)) {
				id += ids.get(i)+"";
			}else id += ids.get(i)+",";
		}
		getSession().createQuery(//
				"update Topic t set t.topScope="+state+" where t.id in ("+id+")")//
				.executeUpdate();
	}

	public void digest(List<Long> ids, String scope) {
		String id = "";
		for(int i=0;i<ids.size();i++) {
			if(i == (ids.size()-1)) {
				id += ids.get(i)+"";
			}else id += ids.get(i)+",";
		}
		getSession().createQuery(//
				"update Topic t set t.digest="+scope+" where t.id in ("+id+")")//
				.executeUpdate();
	}

	public void recommend(List<Long> ids, String scope) {
		String id = "";
		for(int i=0;i<ids.size();i++) {
			if(i == (ids.size()-1)) {
				id += ids.get(i)+"";
			}else id += ids.get(i)+",";
		}
		getSession().createQuery(//
				"update Topic t set t.recommend="+scope+" where t.id in ("+id+")")//
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public void delete(List<Long> ids) {
		String id = "";
		int topicNumber = ids.size() ;
		int replyNumber = 0;
		long topicID = 0 ;
		for(int i=0;i<ids.size();i++) {
			if(i == (ids.size()-1)) {
				id += ids.get(i)+"";
			}else id += ids.get(i)+",";
		}
	
		//先删除关联的附件
		getSession().createQuery(//
				"delete from Attach t where t.topic.id in ("+id+")")//
				.executeUpdate();
		//将最后回帖置为空
		getSession().createQuery(//
				"update Topic t set t.lastReply=? where t.id in ("+id+")")//
				.setParameter(0, null)
				.executeUpdate();
		//再删关联的回帖
		getSession().createQuery(//
				"delete from Reply t where t.topic.id in ("+id+")")//
				.executeUpdate();
		//再更新板块表里面的最后发表
		getSession().createQuery(//
				"update Board t set t.lastTopic=null where t.lastTopic.id in ("+id+")")//
				.executeUpdate();
		//再更新板块表里面的主题数量
		Query query = getSession().createQuery(
				"from Topic where id in("+id+")");
		List<Topic> list = query.list();
		for (Topic  topic  : list) {
			topicID = topic.getBoard().getId();
			replyNumber = replyNumber + topic.getReplyCount() ;
		}
		
		getSession().createQuery(
				"update Board t set t.topicCount = t.topicCount- "+topicNumber+" where t.id =("+topicID+") ")
				.executeUpdate();
		
		//再更新板块里面的文章数量 也就是算上回复数 
		getSession().createQuery(
				"update Board t set t.articleCount = t.articleCount - ("+replyNumber+"+"+topicNumber+") where t.id=("+topicID+")")
				.executeUpdate();
		
		
		
		
		//最后删除主题
		getSession().createQuery(//
				"delete from Topic t where t.id in ("+id+")")//
				.executeUpdate();
	}

	public void deleteBySectionId(Long id) {
		//获取分区下的主题
		List<Long> ids = getSession().createQuery(//
				"select new java.lang.Long(t.id) from Topic t where t.section.id=?")//
				.setParameter(0, id)
				.list();
		if(ids.size() > 0) {
			//删除主题
			delete(ids);
		}
	}

	public void setLastReply(Long id) {
		getSession().createQuery(//
				"update Topic t set t.lastReply=? where t.section.id=?")//
				.setParameter(0, null)
				.setParameter(1, id)
				.executeUpdate();
	}
	
	
	
}
