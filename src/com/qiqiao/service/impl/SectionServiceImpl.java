package com.qiqiao.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qiqiao.base.BaseDaoImpl;
import com.qiqiao.model.Section;
import com.qiqiao.service.SectionService;
import com.qiqiao.vo.SectionVo;

@Service
public class SectionServiceImpl extends BaseDaoImpl<Section> implements
		SectionService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> findAll() {
		return getSession().createQuery(//
				"FROM Section s ORDER BY s.sortNum ASC")// 按排序号进行排序
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<SectionVo> getSectionVos() {
		List<SectionVo> sectionVos = getSession()
				.createQuery(//
						"SELECT new com.wangwei.qiqiao.vo.SectionVo(s.id,s.name) FROM Section s")//
				.list();
		return sectionVos;
	}

	public int getBoardCount(Long id) {
		return Integer.parseInt(getSession().createQuery(//
				"SELECT COUNT(*)FROM Board b WHERE b.section.id=?")//
				.setParameter(0, id)//
				.uniqueResult()//
				.toString());
	}

	public SectionVo getSectionVoById(Long sid) {
		return (SectionVo)getSession().createQuery(//
				"SELECT new com.wangwei.qiqiao.vo.SectionVo(s.id,s.name) FROM Section s WHERE s.id=?")//
				.setParameter(0, sid)//
				.uniqueResult();
	}

}
