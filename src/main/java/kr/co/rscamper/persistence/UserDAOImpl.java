package kr.co.rscamper.persistence;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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
}
