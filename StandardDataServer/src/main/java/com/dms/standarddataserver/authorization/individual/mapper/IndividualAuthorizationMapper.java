package com.dms.standarddataserver.authorization.individual.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationByGroupIdDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SubjAreaDetailDTO;

@Mapper
public interface IndividualAuthorizationMapper {
	List<IndividualUserDTO> getAllIndividualAuthorizationList();
	
	IndividualUserDTO findIndividualUserById(String usrId);
	
	List<IndividualAuthorizationByStdjAreaIdDTO> findIndividualAuthorizationByStdjAreaId(String usrId);
	
	boolean insertIndividual(IndividualUserDTO individualUserDTO);
	
	// 신규 권한 추가
	boolean insertIndividualAuth(List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList);
	
	// admin 은 표준 분류 체계 등록과 동시에 권한 'W' 추가
	void insertAdminAuth(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);
	
	boolean insertIndividualAuthOneByOne(IndividualAuthorizationByStdjAreaIdDTO individualAuthorizationByStdjAreaIdDTO);
	
	List<IndividualAuthorizationByGroupIdDTO> findIndividualAuthorizationByGroupId(String groupId);

	List<SubjAreaDetailDTO> getAllSubjAreaList();
	
	// 표준분류체계별 개인 사용자 권한 조회
	String findIndividualIdAndSubjAreaId(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);

	// 개인별 사용자 권한 수정
	boolean updateIndividualAuthorization(IndividualAuthorizationByStdjAreaIdDTO individualAuthorizationByStdjAreaIdDTO);
	
	List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO);
	
	// 아이디 중복 확인
	boolean validateUserId(String userId);
	
	// 표준 분류 체계 삭제에 따라 해당 표준 분류 체계에 부여되어 있는 권한 모두 삭제
	void deleteSubjAreaInUsrAuth(String subjAreaId);
	
	boolean updateUser(IndividualUserDTO individualUserDTO );
	
//	boolean deleteUser(String userId);

}
