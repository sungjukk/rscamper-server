package kr.co.rscamper.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.rscamper.domain.MainVO;
import kr.co.rscamper.domain.UserVO;
import kr.co.rscamper.persistence.MainDAO;

@Service
public class MainServiceImpl implements MainService {
	
	@Inject
	private MainDAO dao;
	
	
	@Override
	public String getTime() throws Exception {
		return dao.getTime();
	}

	@Override
	public void insertMainComment(MainVO vo) throws Exception {
		dao.insertMainComment(vo);
	}

	@Override
	public List<MainVO> selectMainComment() throws Exception {
		return dao.selectMainComment();
	}

}