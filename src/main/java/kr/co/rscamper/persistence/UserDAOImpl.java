package kr.co.rscamper.persistence;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.rscamper.domain.UserPhotoVO;
import kr.co.rscamper.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "kr.co.rscamper.UserMapper";
	
	@Override
	public UserVO selectMainByUidComment(String userUid) {
		System.out.println("userUid : " + userUid);
		return sqlSessionTemplate.selectOne(namespace + ".selectMainByUidComment", userUid);
	}

	@Override
	public void insertUser(UserVO user) {
		sqlSessionTemplate.insert(namespace + ".insertUser", user);
	}

	@Override
	public void deleteUserByUid(String userUid) {
		sqlSessionTemplate.delete(namespace + ".deleteUserByUid", userUid);
	}

	@Override
	public UserVO selectUserByUid(String userUid) {
		return sqlSessionTemplate.selectOne(namespace + ".selectUserByUid", userUid);
	}

	@Override
	public UserPhotoVO selectUserPhotoPath(UserPhotoVO userPhoto) {
		return sqlSessionTemplate.selectOne(namespace + ".selectUserPhotoPath", userPhoto);
	}

	@Override
	public void deleteUserPhotoByNo(int photoNo) {
		sqlSessionTemplate.delete(namespace + ".deleteUserPhotoByNo", photoNo);
	}

	@Override
	public void insertUserPhoto(UserPhotoVO userPhoto) {
		sqlSessionTemplate.insert(namespace + ".insertUserPhoto", userPhoto);
	}

	@Override
	public void updateUserPhotoUrl(UserPhotoVO userPhoto) {
		sqlSessionTemplate.update(namespace + ".updateUserPhotoUrl", userPhoto);
	}
}
