package com.dms.standarddataserver.standardArea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.standarddataserver.standardArea.dto.DomainGroupDTO;

@Mapper
public interface DomainGroupAutoMapper {
    /**
     * [수정 이력]
     * 날짜: 2024-03-07
     * 수정자: 최유민
     * 수정 내용: 
     * - 메서드명에 Auto 단어 추가
     * - 파라미터 변경
    **/
	void insertAutoDomainGroup(DomainGroupDTO autoDomainGroupDto);
//    void insertDomainGroup(@Param("domainGroupId") String domainId,@Param("standardAreaId") String standardAreaId);
	
	
	void deleteSubjAreaInDomainGroup(String subjAreaId);
}
