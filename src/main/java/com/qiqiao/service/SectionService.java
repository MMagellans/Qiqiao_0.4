package com.qiqiao.service;

import java.util.List;

import com.qiqiao.base.BaseDao;
import com.qiqiao.model.Section;
import com.qiqiao.vo.SectionVo;

public interface SectionService extends BaseDao<Section> {
	
	/**
	 * 获取所有分区信息的SectionVo列表
	 * @return
	 */
	List<SectionVo> getSectionVos();

	int getBoardCount(Long id);

	SectionVo getSectionVoById(Long sid);

}
