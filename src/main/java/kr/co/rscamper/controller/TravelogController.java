package kr.co.rscamper.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.rscamper.domain.CommentVO;
import kr.co.rscamper.domain.PageMaker;
import kr.co.rscamper.domain.PageVO;
import kr.co.rscamper.domain.TravelogVO;
import kr.co.rscamper.domain.UserVO;
import kr.co.rscamper.service.TravelogService;
import kr.co.rscamper.service.UserService;

@Controller
@RequestMapping("/travelog/*")
public class TravelogController {
	
	private static final Logger logger = LoggerFactory.getLogger(TravelogController.class);
	
	@Autowired
	private TravelogService travelogservice;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String travelog() {
		logger.info("/travelog > home");
		
		return "redirect:http://localhost:80/rscamper-web/views/travelog/list.jsp";
	}
	
	// 게시물 목록 확인
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody  Map<String, Object> read(PageVO page) throws Exception{
		logger.info("/travelog > List");
		
		List<TravelogVO> list = new ArrayList<>();
		list = travelogservice.listTravelog(page);
		
		System.out.println(list);
		
		int totalCount = travelogservice.totalCount();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPage(page);
		pageMaker.setTotalCount(totalCount);
		Map<String, Object> map = new HashMap<>();
		map.put("page", list);
		map.put("pageMaker", pageMaker);
		return map;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET(TravelogVO travelog, Model model) throws Exception {
		logger.info("register get...............");
		return "redirect:/travelog/";
	}
	
	// 게시물 실제 등록
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPost(TravelogVO travelog, RedirectAttributes rttr) throws Exception {
		travelogservice.regist(travelog);
		rttr.addAttribute("msg", "SUCCESS");
		return "redirect:/travelog/home";
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
	public String redirectDetail(@PathVariable("boardNo") int bNo) throws Exception {
		logger.info("/travelog > redirectDetail");
		
		return "redirect:http://localhost:80/rscamper-web/views/travelog/detail.jsp?bNo=" + bNo;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public @ResponseBody TravelogVO detail(@RequestParam("bNo") int bNo) throws Exception {
		logger.info("/travelog > detail");
		
		TravelogVO vo = new TravelogVO();
		vo = travelogservice.selectByNo(bNo);
		
		return travelogservice.selectByNo(bNo);
	}
	
	
	@RequestMapping(value = "/remove/{boardNo}", method = RequestMethod.GET)
	public String remove(@PathVariable("boardNo") int boardNo, RedirectAttributes rttr) throws Exception{
		logger.info("/travelog > delete");
		
		travelogservice.delete(boardNo);
//		rttr.addFlashAttribute("msg", "success");
		return "redirect:http://localhost:80/rscamper-web/views/travelog/list.jsp";
	}
	
	
	
	
	@RequestMapping(value = "/commentRegister", method = RequestMethod.POST)
	public @ResponseBody List<CommentVO> insert(@RequestParam String userUid, String commentContent, int boardNo, 
												HttpServletResponse res) throws Exception {
		logger.info("/travelogComment > insert");
		
		CommentVO cVo = new CommentVO();
		cVo.setUserUid(userUid);
		cVo.setCommentContent(commentContent);
		cVo.setBoardNo(boardNo);
		travelogservice.addCommnet(cVo);
		
		List<CommentVO> list = new ArrayList<>();
		list = travelogservice.listComment(cVo.getBoardNo());
		
		for (CommentVO val : list) {
			UserVO uVo = userService.selectMainByUidComment(val.getUserUid());
			val.setDisplayName(uVo.getDisplayName());
			val.setProviderPhotoUrl(uVo.getPhotoUrl());
		}
		System.out.println(list.size());
		return list;
	}
	
	@RequestMapping(value = "/commentList/{boardNo}", method = RequestMethod.PUT)
	public @ResponseBody List<CommentVO> listComment(@PathVariable("boardNo") int boardNo, HttpServletResponse res) throws Exception {
		logger.info("/travelogComment > listComment");
		
		List<CommentVO> list = new ArrayList<>();
		list = travelogservice.listComment(boardNo);
		
		for (CommentVO val : list) {
			UserVO uVo = userService.selectMainByUidComment(val.getUserUid());
			val.setDisplayName(uVo.getDisplayName());
			val.setProviderPhotoUrl(uVo.getPhotoUrl());
		}
		System.out.println(list.size());
		return list;
	}
	

	  @RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	  public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody CommentVO vo) {

	    ResponseEntity<String> entity = null;
	    try {
	      vo.setCommentNo(rno);
	      travelogservice.modifyReply(vo);

	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

	    ResponseEntity<String> entity = null;
	    try {
	    	travelogservice.removeComment(rno);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{boardNo}/{page}", method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> listPage(
	      @PathVariable("boardNo") Integer boardNo,
	      @PathVariable("page") Integer page) {

	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
	      PageVO pageVo = new PageVO();
	      pageVo.setPage(page);
	      
	      PageMaker pageMaker = new PageMaker();
	      pageMaker.setPage(pageVo);

	      Map<String, Object> map = new HashMap<String, Object>();
	      List<CommentVO> list = travelogservice.listComment(boardNo, pageVo);

	      map.put("list", list);

	      int replyCount = travelogservice.count(boardNo);
	      pageMaker.setTotalCount(replyCount);

	      map.put("pageMaker", pageMaker);

	      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	
}
