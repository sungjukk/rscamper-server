package kr.co.rscamper.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.rscamper.domain.TourPlanCoverVO;
import kr.co.rscamper.domain.TourPlanParamVO;
import kr.co.rscamper.domain.TourPlanScheduleVO;
import kr.co.rscamper.domain.TourPlanSpotParamVO;
import kr.co.rscamper.domain.TourPlanVO;
import kr.co.rscamper.service.TourPlanService;

@Controller
@RequestMapping("/tourPlan")
public class TourPlanController {

	private static final Logger logger = LoggerFactory.getLogger(TourPlanController.class);
	
	@Inject
	private TourPlanService tourPlanService;
	
	@Inject
	private ServletContext servletContext;

	@RequestMapping(value = "/select/tourPlanList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTourPlanList(TourPlanParamVO tourPlanParam) throws Exception {
		return tourPlanService.selectTourPlanList(tourPlanParam);
	}
	
	@RequestMapping(value = "/insert/tourPlan", method = RequestMethod.POST)
	public @ResponseBody int insertTourPlan(TourPlanVO tourPlan) throws Exception {
		return tourPlanService.insertTourPlan(tourPlan);
	}
	
	@RequestMapping(value = "/update/tourPlan", method = RequestMethod.POST)
	public @ResponseBody void updateTourPlan(TourPlanVO tourPlan) throws Exception {
		tourPlanService.updateTourPlan(tourPlan);
	}
	
	@RequestMapping(value = "/select/tourPlanScheduleByRecordNo", method = RequestMethod.GET)
	public @ResponseBody List<TourPlanScheduleVO> selectTourPlanScheduleListByRecordNo(int recordNo) throws Exception {
		return tourPlanService.selectTourPlanScheduleListByRecordNo(recordNo);
	}
	
	@RequestMapping(value = "/insert/tourPlanSchedule", method = RequestMethod.POST)
	public @ResponseBody void insertTourPlanSchedule(TourPlanScheduleVO tourPlanSchedule) throws Exception {
		tourPlanService.insertTourPlanSchedule(tourPlanSchedule);
	}
	
	@RequestMapping(value = "/delete/tourPlanScheduleByRecordNo", method = RequestMethod.GET)
	public @ResponseBody void deleteTourPlanScheduleByRecordNo(int recordNo) throws Exception {
		tourPlanService.deleteTourPlanScheduleByRecordNo(recordNo);
	}
	
	@RequestMapping(value = "/select/spotList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> selectSpotList(TourPlanSpotParamVO tourPlanSpotParam) throws Exception {
		return tourPlanService.selectSpotList(tourPlanSpotParam);
	}
	
	@RequestMapping(value = "/select/oneTourPlan", method = RequestMethod.GET)
	public @ResponseBody TourPlanVO selectTourPlan(int recordNo) throws Exception {
		return tourPlanService.selectTourPlan(recordNo);
	}
	
	@RequestMapping(value = "/update/coverImage", method = RequestMethod.POST)
	public @ResponseBody void updateCoverImage(TourPlanCoverVO tourPlanCover) throws Exception {
		tourPlanService.updateCoverImage(tourPlanCover);
	}
	
	@RequestMapping(value = "/update/tourPlanTitle", method = RequestMethod.POST)
	public @ResponseBody void updateTourPlanTitle(TourPlanVO tourPlan) throws Exception {
		tourPlanService.updateTourPlanTitle(tourPlan);
	}
	
	
	@RequestMapping(value = "/upload/coverImage", method = RequestMethod.POST)
	public @ResponseBody TourPlanCoverVO coverImageUpload(MultipartHttpServletRequest mRequest) throws Exception {

		TourPlanCoverVO tourPlanCover = new TourPlanCoverVO();
		
		String path = "";
		Long size = (long) 0;
		String saveFileName = "";
		String oriFileName = "";
		
		// c:\\\\\
		String uploadDir = servletContext.getRealPath("/upload/images/tourPlan/cover");

		File f = new File(uploadDir);
		if (!f.exists())
			f.mkdirs();

		Iterator<String> iter = mRequest.getFileNames();
		while (iter.hasNext()) {
			String formFileName = iter.next();
			// 폼에서 파일을 선택하지 않아도 객체 생성됨
			MultipartFile mFile = mRequest.getFile(formFileName);

			// 원본 파일명
			oriFileName = mFile.getOriginalFilename();
			
			// 확장자는 무조건 jpg
			if (oriFileName != null && !oriFileName.equals("")) {

				// 확장자 처리
				String ext = ".jpg";
				
				// 뒤쪽에 있는 . 의 위치
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					// 파일명에서 확장자명(.포함)을 추출
					ext = oriFileName.substring(index);
				}
				
				// 파일 사이즈
				size = mFile.getSize();
				// TODO: 10메가 넘으면 실패 반환

				// 고유한 파일명 만들기
				saveFileName = "cover-" + UUID.randomUUID().toString() + ext;
				
				
				// 임시저장된 파일을 원하는 경로에 저장
				mFile.transferTo(new File(uploadDir + "/" + saveFileName));
				path = uploadDir + "/" + saveFileName;
			}
		}
		
		String filePath = "http://14.32.66.104:8081" + servletContext.getContextPath() + "/images?path=" + path.substring(path.lastIndexOf("upload")).replaceAll("\\\\","/");
		
		tourPlanCover.setOriName(oriFileName);
		tourPlanCover.setFileName(saveFileName);
		tourPlanCover.setRealPath(path);
		tourPlanCover.setFilePath(filePath);
		tourPlanCover.setFileSize(size);
	
		return tourPlanCover;
	}
	
	
	
	

}