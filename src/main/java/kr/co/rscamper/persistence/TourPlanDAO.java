package kr.co.rscamper.persistence;

import java.util.List;

import kr.co.rscamper.domain.TourPlanCoverVO;
import kr.co.rscamper.domain.TourPlanParamVO;
import kr.co.rscamper.domain.TourPlanScheduleVO;
import kr.co.rscamper.domain.TourPlanSpotParamVO;
import kr.co.rscamper.domain.TourPlanVO;
import kr.co.rscamper.domain.TourPlanSpotVO;

public interface TourPlanDAO {

	/** 여행일정 메인 조회 */
	public List<TourPlanVO> selectTourPlanList(TourPlanParamVO tourPlanParam);

	/** 여행일정 총 개수 조회 */
	public int selectTourPlanTotalPages(TourPlanParamVO tourPlanParam);

	/** 여행일정 신규 등록 */
	public int insertTourPlan(TourPlanVO tourPlan);

	/** 여행장소 총 개수 조회 */
	public int selectSpotTotalPages(TourPlanSpotParamVO tourPlanSpotParam);

	/** 여행장소 리스트 조회 */
	public List<TourPlanSpotVO> selectTourSpotList(TourPlanSpotParamVO tourPlanSpotParam);

	/** 여행일정 한개 조회*/
	public TourPlanVO selectTourPlan(int recordNo);

	/** 커버 이미지 파일 조회 */
	public TourPlanCoverVO selectTourPlanCoverByNo(int recordNo);

	/** 커버 이미지 파일 정보 삭제 */
	public void deleteTourPlanCoverByNo(int coverNo);

	/** 커버 이미지 정보 입력 */
	public int insertTourPlanCover(TourPlanCoverVO tourPlanCover);

	/** 여행 일정 업데이트 */
	public void updateTourPlanImgByNo(TourPlanVO tourPlan);

	/** 여행일정 제목 업데이트 */
	public void updateTourPlanTitle(TourPlanVO tourPlan);

	/** 여행일정 업데이트 */
	public void updateTourPlan(TourPlanVO tourPlan);

	/** 여행일정 - 스케쥴 입력 */
	public void insertTourPlanSchedule(TourPlanScheduleVO tourPlanSchedule);

	/** 여행일정 - 스케쥴 삭제 */
	public void deleteTourPlanScheduleByRecordNo(int recordNo);

	/** 여행일정 - 스케쥴 조회 */
	public List<TourPlanScheduleVO> selectTourPlanScheduleListByRecordNo(int recordNo);

	
}