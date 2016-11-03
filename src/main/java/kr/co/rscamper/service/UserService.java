package kr.co.rscamper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;

import kr.co.rscamper.domain.UserPhotoVO;
import kr.co.rscamper.domain.UserVO;

public interface UserService {
	public UserVO selectMainByUidComment(String userUid) throws Exception;
	
	/** [App] 회원 가입 */
	public void insertUser(UserVO user) throws Exception;
<<<<<<< HEAD

	/** [App] 회원 탈퇴 */
	public void deleteUserByUid(String userUid);
	
	/** [App] UID로 회원 정보 가져오기 */
	public UserVO selectUserByUid(String userUid);

	/** [App] 회원 정보 수정 (프로필사진) */
	public void updateProfileImage(UserPhotoVO userPhoto);
	
	/** [App] 회원 정보 수정 (프로필 배경 사진) */
	public void updateBgImage(UserPhotoVO userPhoto);
	
	/** TODO: [App] 회원 정보 수정 (회원정보) */

	/** TODO: [App] 회원 리스트 가져오기 */
	public List<UserVO> selectUserList();
	
=======
	
	/** 회원정보 변경 */
	public void updateUser(UserVO user)throws Exception;
>>>>>>> master
}
