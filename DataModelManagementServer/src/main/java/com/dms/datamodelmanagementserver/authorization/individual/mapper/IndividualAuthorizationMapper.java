package com.dms.datamodelmanagementserver.authorization.individual.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationByGroupIdDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.UpdateUserPasswordDTO;

@Mapper
public interface IndividualAuthorizationMapper {
	public List<IndividualUserDTO> getAllIndividualAuthorizationList();
	
	public IndividualUserDTO findIndividualUserById(String usrId);
	
	public List<IndividualAuthorizationByStdjAreaIdDTO> findIndividualAuthorizationByStdjAreaId(String usrId);
	
	public boolean insertIndividual(IndividualUserDTO individualUserDTO);
	
	// 신규 권한 추가
	public boolean insertIndividualAuth(List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList);
	
	// admin 은 표준 분류 체계 등록과 동시에 권한 'W' 추가
	public void insertAdminAuth(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);
	
	public boolean insertIndividualAuthOneByOne(IndividualAuthorizationByStdjAreaIdDTO individualAuthorizationByStdjAreaIdDTO);
	
	// 특정 사용자가 속한 모든 그룹 조회
	public List<GroupDTO> findGroupByUserId(String userId);
	
	public List<IndividualAuthorizationByGroupIdDTO> findIndividualAuthorizationByGroupId(String groupId);

	public List<SubjAreaDetailDTO> getAllSubjAreaList();
	
	// 프로젝트별 개인 사용자 권한 조회
	public String findIndividualIdAndSubjAreaId(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);

	public List<IndividualAuthorizationByStdjAreaIdDTO> findSubjAreaIdByUsrId(String usrId);
	// 개인별 사용자 권한 수정
	public boolean updateIndividualAuthorization(IndividualAuthorizationByStdjAreaIdDTO individualAuthorizationByStdjAreaIdDTO);
	
	public List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO);
	
	// 아이디 중복 확인
	public boolean validateUserId(String userId);
	
	// 표준 분류 체계 삭제에 따라 해당 표준 분류 체계에 부여되어 있는 권한 모두 삭제
	public void deleteSubjAreaInUsrAuth(String subjAreaId);
	
	public boolean deleteIndividualAuthBySubjAreaId(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);
	
	public boolean updateUser(IndividualUserDTO individualUserDTO );
	
	public boolean validateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO);
	
	public boolean updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO);


}
