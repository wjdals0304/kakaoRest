package kakao.response;

import java.util.List;

public class RealtimeArrivalList {
	
	private int beginRow;
	private int endRow;
	private int curPage;
	private int pageRow;
	private int totalCount;
	private int rowNum;
	private int selectedCount;
	private String subwayId;	//지하철호선ID
	private String subwayNm;
	private String updnLine;	//상하행선구분
	private String trainLineNm;	//도착지방면
	private String subwayHeading;	//내리는문방향
	private String statnFid;	//이전지하철역ID
	private String statnTid;	//다음지하철역ID
	private String statnId;		//지하철역ID
	private String statnNm;		//지하철역명
	private String trainCo;		//환승노선수
	private String ordkey;		//도착예정열차순번
	private String subwayList;	//연계호선ID
	private String statnList;		//연계지하철역ID
	private String btrainSttus;		//열차종류
	private String barvlDt;			//열차도착예정시간
	private String btrainNo;		//열차번호
	private String bstatnId;		//종착지하철역ID
	private String bstatnNm;		//종착지하철역명
	private String recptnDt;		//열차도착정보를 생성한 시각
	private String arvlMsg2;		//첫번째도착메세지
	private String arvlMsg3;		//두번째도착메세지
	private String arvlCd;		
	/*
	 * 도착코드
	(0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중)	
	 */
	
	public RealtimeArrivalList(int beginRow, int endRow, int curPage, int pageRow, int totalCount, int rowNum,
			int selectedCount, String subwayId, String subwayNm, String updnLine, String trainLineNm,
			String subwayHeading, String statnFid, String statnTid, String statnId, String statnNm, String trainCo,
			String ordkey, String subwayList, String statnList, String btrainSttus, String barvlDt,
			String btrainNo, String bstatnId, String bstatnNm, String recptnDt, String arvlMsg2, String arvlMsg3,
			String arvlCd) {
		super();
		this.beginRow = beginRow;
		this.endRow = endRow;
		this.curPage = curPage;
		this.pageRow = pageRow;
		this.totalCount = totalCount;
		this.rowNum = rowNum;
		this.selectedCount = selectedCount;
		this.subwayId = subwayId;
		this.subwayNm = subwayNm;
		this.updnLine = updnLine;
		this.trainLineNm = trainLineNm;
		this.subwayHeading = subwayHeading;
		this.statnFid = statnFid;
		this.statnTid = statnTid;
		this.statnId = statnId;
		this.statnNm = statnNm;
		this.trainCo = trainCo;
		this.ordkey = ordkey;
		this.subwayList = subwayList;
		this.statnList = statnList;
		this.btrainSttus = btrainSttus;
		this.barvlDt = barvlDt;
		this.btrainNo = btrainNo;
		this.bstatnId = bstatnId;
		this.bstatnNm = bstatnNm;
		this.recptnDt = recptnDt;
		this.arvlMsg2 = arvlMsg2;
		this.arvlMsg3 = arvlMsg3;
		this.arvlCd = arvlCd;
	}

	public RealtimeArrivalList() {
		super();
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getSelectedCount() {
		return selectedCount;
	}

	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
	}

	public String getSubwayId() {
		return subwayId;
	}

	public void setSubwayId(String subwayId) {
		this.subwayId = subwayId;
	}

	public String getSubwayNm() {
		return subwayNm;
	}

	public void setSubwayNm(String subwayNm) {
		this.subwayNm = subwayNm;
	}

	public String getUpdnLine() {
		return updnLine;
	}

	public void setUpdnLine(String updnLine) {
		this.updnLine = updnLine;
	}

	public String getTrainLineNm() {
		return trainLineNm;
	}

	public void setTrainLineNm(String trainLineNm) {
		this.trainLineNm = trainLineNm;
	}

	public String getSubwayHeading() {
		return subwayHeading;
	}

	public void setSubwayHeading(String subwayHeading) {
		this.subwayHeading = subwayHeading;
	}

	public String getStatnFid() {
		return statnFid;
	}

	public void setStatnFid(String statnFid) {
		this.statnFid = statnFid;
	}

	public String getStatnTid() {
		return statnTid;
	}

	public void setStatnTid(String statnTid) {
		this.statnTid = statnTid;
	}

	public String getStatnId() {
		return statnId;
	}

	public void setStatnId(String statnId) {
		this.statnId = statnId;
	}

	public String getStatnNm() {
		return statnNm;
	}

	public void setStatnNm(String statnNm) {
		this.statnNm = statnNm;
	}

	public String getTrainCo() {
		return trainCo;
	}

	public void setTrainCo(String trainCo) {
		this.trainCo = trainCo;
	}

	public String getOrdkey() {
		return ordkey;
	}

	public void setOrdkey(String ordkey) {
		this.ordkey = ordkey;
	}

	public String getSubwayList() {
		return subwayList;
	}

	public void setSubwayList(String subwayList) {
		this.subwayList = subwayList;
	}

	public String getStatnList() {
		return statnList;
	}

	public void setStatnList(String statnList) {
		this.statnList = statnList;
	}

	public String getBtrainSttus() {
		return btrainSttus;
	}

	public void setBtrainSttus(String btrainSttus) {
		this.btrainSttus = btrainSttus;
	}

	public String getBarvlDt() {
		return barvlDt;
	}

	public void setBarvlDt(String barvlDt) {
		this.barvlDt = barvlDt;
	}

	public String getBtrainNo() {
		return btrainNo;
	}

	public void setBtrainNo(String btrainNo) {
		this.btrainNo = btrainNo;
	}

	public String getBstatnId() {
		return bstatnId;
	}

	public void setBstatnId(String bstatnId) {
		this.bstatnId = bstatnId;
	}

	public String getBstatnNm() {
		return bstatnNm;
	}

	public void setBstatnNm(String bstatnNm) {
		this.bstatnNm = bstatnNm;
	}

	public String getRecptnDt() {
		return recptnDt;
	}

	public void setRecptnDt(String recptnDt) {
		this.recptnDt = recptnDt;
	}

	public String getArvlMsg2() {
		return arvlMsg2;
	}

	public void setArvlMsg2(String arvlMsg2) {
		this.arvlMsg2 = arvlMsg2;
	}

	public String getArvlMsg3() {
		return arvlMsg3;
	}

	public void setArvlMsg3(String arvlMsg3) {
		this.arvlMsg3 = arvlMsg3;
	}

	public String getArvlCd() {
		return arvlCd;
	}

	public void setArvlCd(String arvlCd) {
		this.arvlCd = arvlCd;
	}
	
}
