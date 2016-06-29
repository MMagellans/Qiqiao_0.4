package com.qiqiao.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Board;
import com.qiqiao.model.Topic;
import com.qiqiao.service.BoardService;
import com.qiqiao.vo.BoardVo;
import com.qiqiao.vo.BoardVo2;

@Service
public class BoardServiceImpl extends BaseDaoImpl<Board> implements BoardService {

	/**
	 * 查询今日某一板块的文章数（包括主题和回帖）
	 */
	public int todayArticle(Long id) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());
		String todayStartTime = today + " 00:00:00";
		String todayEndTime = today + " 23:59:59";
		Long todayTopicCount = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.postTime<? AND t.postTime>? AND t.board.id=?")
				//
				.setParameter(0, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todayEndTime)).setParameter(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todayStartTime))
				.setParameter(2, id).uniqueResult();
		Long todayReplyCount = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.postTime<? AND r.postTime>? AND r.topic.board.id=?")
				//
				.setParameter(0, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todayEndTime)).setParameter(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todayStartTime))
				.setParameter(2, id).uniqueResult();
		return (todayTopicCount.intValue() + todayReplyCount.intValue());
	}

	public BoardVo getBoardVo(Long id) {
		// System.out.println("热部署测试，struts2的热部署测试");
		BoardVo boardVo = (BoardVo) getSession().createQuery(//
				"SELECT new com.wangwei.qiqiao.vo.BoardVo(b.id,b.name,b.description,b.state,b.highColor,b.allowRole,b.section) FROM Board b WHERE b.id=?")//
				.setParameter(0, id).uniqueResult();
		return boardVo;
	}

	/**
	 * 重写删除方法，只要将板块的状态标记为删除即可
	 */
	@Override
	public void delete(Long id) {
		getSession().createQuery(//
				"UPDATE Board b SET b.state=? WHERE b.id=?")//
				.setParameter(0, Board.STATE_DELETE)//
				.setParameter(1, id).executeUpdate();
	}

	/**
	 * 重写查询所有版块方法，不要查询出版块状态为删除的板块
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAll() {
		return getSession().createQuery(//
				"FROM Board b WHERE b.state not in (?,?)")
		//
				.setParameter(0, Board.STATE_HIDE).setParameter(1, Board.STATE_DELETE).list();
	}

	@SuppressWarnings("unchecked")
	public List<BoardVo2> findAllBoardsVo2() {
		return getSession().createQuery(//
				"SELECT new com.wangwei.qiqiao.vo.BoardVo2(b.id,b.name) FROM Board b WHERE b.state not in (?,?)")
		//
				.setParameter(0, Board.STATE_HIDE).setParameter(1, Board.STATE_DELETE).list();
	}

	public void merger(Long sourcebid, Long objbid) {
		// 将源板块的主题全部更改成目标版块
		getSession().createQuery(//
				"UPDATE Topic t SET t.board=? WHERE t.board.id=?").setParameter(0, getById(objbid))//
				.setParameter(1, sourcebid)//
				.executeUpdate();
		// 将源板块的回帖全都改成目标版块
		getSession().createQuery(//
				"UPDATE Reply r SET r.boardId=? WHERE r.boardId=?").setParameter(0, objbid)//
				.setParameter(1, sourcebid)//
				.executeUpdate();
		// 将源板块的版主全都全都置为空
		getSession().createQuery(//
				"UPDATE User u SET u.board = null WHERE u.board.id=?").setParameter(0, sourcebid)//
				.executeUpdate();
		// 删除源板块(彻底删除)
		getSession().createQuery(//
				"DELETE FROM Board b WHERE b.id = ?")//
				.setParameter(0, sourcebid)//
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Topic> findTopList(Long id, Long id2) {
		List<Topic> list = new ArrayList<Topic>();
		String hql = "from Topic t where t.topScope=3";
		list = getSession().createQuery(hql).list();
		String hql1 = "from Topic t where t.section.id=? and t.topScope=2 order by t.topScope desc";
		Query query1 = getSession().createQuery(hql1);
		query1.setLong(0, id);
		list.addAll(query1.list());
		String hql2 = "from Topic t where t.board.id=? and t.topScope=1 order by t.topScope desc";
		Query query = getSession().createQuery(hql2);
		query.setLong(0, id2);
		list.addAll(query.list());
		return list;
	}

	public void deleteBySectionId(Long id) {
		// 删除分区下的板块
		getSession().createQuery(//
				"DELETE FROM Board b WHERE b.section.id = ?")//
				.setParameter(0, id)//
				.executeUpdate();
	}
	
	
}
