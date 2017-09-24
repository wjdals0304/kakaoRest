package kakao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShortestRoute_row {
	int rowNum;
	int selectedCount;
	int totalCount;
	String statnFid;
	String statnTid;
	String statnFnm;
	String statnTnm;
	List<String> shtStatnId;
	List<String> shtStatnNm;
	String shtTransferMsg;
	String shtTravelMsg;
	int shtStatnCnt;
	int shtTravelTm;
	int shtTransferCnt;
	List<String> minStatnId;
	List<String> minStatnNm;
	String minTransferMsg;
	String minTravelMsg;
	int minStatnCnt;
	int minTravelTm;
	int minTransferCnt;
	String shtStatnXy;
	String minStatnXy;
	public ShortestRoute_row(int rowNum, int selectedCount, int totalCount, String statnFid, String statnTid,
			String statnFnm, String statnTnm, List<String> shtStatnId, List<String> shtStatnNm, String shtTransferMsg,
			String shtTravelMsg, int shtStatnCnt, int shtTravelTm, int shtTransferCnt, List<String> minStatnId,
			List<String> minStatnNm, String minTransferMsg, String minTravelMsg, int minStatnCnt, int minTravelTm,
			int minTransferCnt, String shtStatnXy, String minStatnXy) {
		super();
		this.rowNum = rowNum;
		this.selectedCount = selectedCount;
		this.totalCount = totalCount;
		this.statnFid = statnFid;
		this.statnTid = statnTid;
		this.statnFnm = statnFnm;
		this.statnTnm = statnTnm;
		this.shtStatnId = shtStatnId;
		this.shtStatnNm = shtStatnNm;
		this.shtTransferMsg = shtTransferMsg;
		this.shtTravelMsg = shtTravelMsg;
		this.shtStatnCnt = shtStatnCnt;
		this.shtTravelTm = shtTravelTm;
		this.shtTransferCnt = shtTransferCnt;
		this.minStatnId = minStatnId;
		this.minStatnNm = minStatnNm;
		this.minTransferMsg = minTransferMsg;
		this.minTravelMsg = minTravelMsg;
		this.minStatnCnt = minStatnCnt;
		this.minTravelTm = minTravelTm;
		this.minTransferCnt = minTransferCnt;
		this.shtStatnXy = shtStatnXy;
		this.minStatnXy = minStatnXy;
	}
	public ShortestRoute_row() {
		super();
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public String getStatnFnm() {
		return statnFnm;
	}
	public void setStatnFnm(String statnFnm) {
		this.statnFnm = statnFnm;
	}
	public String getStatnTnm() {
		return statnTnm;
	}
	public void setStatnTnm(String statnTnm) {
		this.statnTnm = statnTnm;
	}
	public List<String> getShtStatnId() {
		return shtStatnId;
	}
	public void setShtStatnId(List<String> shtStatnId) {
		this.shtStatnId = shtStatnId;
	}
	public List<String> getShtStatnNm() {
		return shtStatnNm;
	}
	public void setShtStatnNm(List<String> shtStatnNm) {
		this.shtStatnNm = shtStatnNm;
	}
	public String getShtTransferMsg() {
		return shtTransferMsg;
	}
	public void setShtTransferMsg(String shtTransferMsg) {
		this.shtTransferMsg = shtTransferMsg;
	}
	public String getShtTravelMsg() {
		return shtTravelMsg;
	}
	public void setShtTravelMsg(String shtTravelMsg) {
		this.shtTravelMsg = shtTravelMsg;
	}
	public int getShtStatnCnt() {
		return shtStatnCnt;
	}
	public void setShtStatnCnt(int shtStatnCnt) {
		this.shtStatnCnt = shtStatnCnt;
	}
	public int getShtTravelTm() {
		return shtTravelTm;
	}
	public void setShtTravelTm(int shtTravelTm) {
		this.shtTravelTm = shtTravelTm;
	}
	public int getShtTransferCnt() {
		return shtTransferCnt;
	}
	public void setShtTransferCnt(int shtTransferCnt) {
		this.shtTransferCnt = shtTransferCnt;
	}
	public List<String> getMinStatnId() {
		return minStatnId;
	}
	public void setMinStatnId(List<String> minStatnId) {
		this.minStatnId = minStatnId;
	}
	public List<String> getMinStatnNm() {
		return minStatnNm;
	}
	public void setMinStatnNm(List<String> minStatnNm) {
		this.minStatnNm = minStatnNm;
	}
	public String getMinTransferMsg() {
		return minTransferMsg;
	}
	public void setMinTransferMsg(String minTransferMsg) {
		this.minTransferMsg = minTransferMsg;
	}
	public String getMinTravelMsg() {
		return minTravelMsg;
	}
	public void setMinTravelMsg(String minTravelMsg) {
		this.minTravelMsg = minTravelMsg;
	}
	public int getMinStatnCnt() {
		return minStatnCnt;
	}
	public void setMinStatnCnt(int minStatnCnt) {
		this.minStatnCnt = minStatnCnt;
	}
	public int getMinTravelTm() {
		return minTravelTm;
	}
	public void setMinTravelTm(int minTravelTm) {
		this.minTravelTm = minTravelTm;
	}
	public int getMinTransferCnt() {
		return minTransferCnt;
	}
	public void setMinTransferCnt(int minTransferCnt) {
		this.minTransferCnt = minTransferCnt;
	}
	public String getShtStatnXy() {
		return shtStatnXy;
	}
	public void setShtStatnXy(String shtStatnXy) {
		this.shtStatnXy = shtStatnXy;
	}
	public String getMinStatnXy() {
		return minStatnXy;
	}
	public void setMinStatnXy(String minStatnXy) {
		this.minStatnXy = minStatnXy;
	}

}
