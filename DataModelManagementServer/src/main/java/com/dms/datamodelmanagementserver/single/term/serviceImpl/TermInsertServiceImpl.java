package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.single.term.dto.TermDTO;
import com.dms.datamodelmanagementserver.single.term.mapper.TermMapper;
import com.dms.datamodelmanagementserver.single.term.service.TermInsertService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermInsertServiceImpl implements TermInsertService {
	
    private final TermMapper termMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

	public TermInsertServiceImpl(TermMapper termMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
		this.termMapper = termMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}

	@Override
	public boolean singleTermInsertRest(List<TermDTO> termDTOList) {
		termDTOList.stream().forEach(it -> {
			String subjAreaName = it.getSubjAreaId();
			it.setSubjAreaId(standardAreaSelectOneService.selectOne(subjAreaName).getStdAreaId());
		});
		termMapper.singleTermInsert(termDTOList);
		boolean termInsertResult = !termDTOList.isEmpty();
		return termInsertResult;
	}
}


