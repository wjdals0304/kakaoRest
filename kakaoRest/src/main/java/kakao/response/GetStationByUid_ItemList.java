package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStationByUid_ItemList {
	private String adirection;
	private String arrmsg1;
	private String arrmsg2;
	private String arrmsgSec1;
	private String arrmsgSec2;
	private String arsId;
	private String busRouteId;
	private String busType1;
	private String busType2;
	private String firstTm;
	private String gpsX;
	private String gpsY;
	private String isArrive1;
	private String isArrive2;
	private String isFullFlag1;
	private String isFullFlag2;
	private String isLast1;
	private String isLast2;
	private String lastTm;
	private String nextBus;
	private String nxtStn;
	private String posX;
	private String posY;
	private String rerdieDiv1;
	private String rerdieDiv2;
	private String rerideNum1;
	private String rerideNum2;
	private String routeType;
	private String rtNm;
	private String sectNm;
	private String sectOrd1;
	private String sectOrd2;
	private String stId;
	private String stNm;
	private String staOrd;
	private String stationTp;
	private String term;
	private String traSpd1;
	private String traSpd2;
	private String traTime1;
	private String traTime2;
	private String vehId1;
	private String vehId2;
	
	public GetStationByUid_ItemList(String adirection, String arrmsg1, String arrmsg2, String arrmsgSec1,
			String arrmsgSec2, String arsId, String busRouteId, String busType1, String busType2, String firstTm,
			String gpsX, String gpsY, String isArrive1, String isArrive2, String isFullFlag1, String isFullFlag2,
			String isLast1, String isLast2, String lastTm, String nextBus, String nxtStn, String posX, String posY,
			String rerdieDiv1, String rerdieDiv2, String rerideNum1, String rerideNum2, String routeType, String rtNm,
			String sectNm, String sectOrd1, String sectOrd2, String stId, String stNm, String staOrd, String stationTp,
			String term, String traSpd1, String traSpd2, String traTime1, String traTime2, String vehId1,
			String vehId2) {
		super();
		this.adirection = adirection;
		this.arrmsg1 = arrmsg1;
		this.arrmsg2 = arrmsg2;
		this.arrmsgSec1 = arrmsgSec1;
		this.arrmsgSec2 = arrmsgSec2;
		this.arsId = arsId;
		this.busRouteId = busRouteId;
		this.busType1 = busType1;
		this.busType2 = busType2;
		this.firstTm = firstTm;
		this.gpsX = gpsX;
		this.gpsY = gpsY;
		this.isArrive1 = isArrive1;
		this.isArrive2 = isArrive2;
		this.isFullFlag1 = isFullFlag1;
		this.isFullFlag2 = isFullFlag2;
		this.isLast1 = isLast1;
		this.isLast2 = isLast2;
		this.lastTm = lastTm;
		this.nextBus = nextBus;
		this.nxtStn = nxtStn;
		this.posX = posX;
		this.posY = posY;
		this.rerdieDiv1 = rerdieDiv1;
		this.rerdieDiv2 = rerdieDiv2;
		this.rerideNum1 = rerideNum1;
		this.rerideNum2 = rerideNum2;
		this.routeType = routeType;
		this.rtNm = rtNm;
		this.sectNm = sectNm;
		this.sectOrd1 = sectOrd1;
		this.sectOrd2 = sectOrd2;
		this.stId = stId;
		this.stNm = stNm;
		this.staOrd = staOrd;
		this.stationTp = stationTp;
		this.term = term;
		this.traSpd1 = traSpd1;
		this.traSpd2 = traSpd2;
		this.traTime1 = traTime1;
		this.traTime2 = traTime2;
		this.vehId1 = vehId1;
		this.vehId2 = vehId2;
	}
	public GetStationByUid_ItemList() {
		super();
	}
	public String getAdirection() {
		return adirection;
	}
	public void setAdirection(String adirection) {
		this.adirection = adirection;
	}
	public String getArrmsg1() {
		return arrmsg1;
	}
	public void setArrmsg1(String arrmsg1) {
		this.arrmsg1 = arrmsg1;
	}
	public String getArrmsg2() {
		return arrmsg2;
	}
	public void setArrmsg2(String arrmsg2) {
		this.arrmsg2 = arrmsg2;
	}
	public String getArrmsgSec1() {
		return arrmsgSec1;
	}
	public void setArrmsgSec1(String arrmsgSec1) {
		this.arrmsgSec1 = arrmsgSec1;
	}
	public String getArrmsgSec2() {
		return arrmsgSec2;
	}
	public void setArrmsgSec2(String arrmsgSec2) {
		this.arrmsgSec2 = arrmsgSec2;
	}
	public String getArsId() {
		return arsId;
	}
	public void setArsId(String arsId) {
		this.arsId = arsId;
	}
	public String getBusRouteId() {
		return busRouteId;
	}
	public void setBusRouteId(String busRouteId) {
		this.busRouteId = busRouteId;
	}
	public String getBusType1() {
		return busType1;
	}
	public void setBusType1(String busType1) {
		this.busType1 = busType1;
	}
	public String getBusType2() {
		return busType2;
	}
	public void setBusType2(String busType2) {
		this.busType2 = busType2;
	}
	public String getFirstTm() {
		return firstTm;
	}
	public void setFirstTm(String firstTm) {
		this.firstTm = firstTm;
	}
	public String getGpsX() {
		return gpsX;
	}
	public void setGpsX(String gpsX) {
		this.gpsX = gpsX;
	}
	public String getGpsY() {
		return gpsY;
	}
	public void setGpsY(String gpsY) {
		this.gpsY = gpsY;
	}
	public String getIsArrive1() {
		return isArrive1;
	}
	public void setIsArrive1(String isArrive1) {
		this.isArrive1 = isArrive1;
	}
	public String getIsArrive2() {
		return isArrive2;
	}
	public void setIsArrive2(String isArrive2) {
		this.isArrive2 = isArrive2;
	}
	public String getIsFullFlag1() {
		return isFullFlag1;
	}
	public void setIsFullFlag1(String isFullFlag1) {
		this.isFullFlag1 = isFullFlag1;
	}
	public String getIsFullFlag2() {
		return isFullFlag2;
	}
	public void setIsFullFlag2(String isFullFlag2) {
		this.isFullFlag2 = isFullFlag2;
	}
	public String getIsLast1() {
		return isLast1;
	}
	public void setIsLast1(String isLast1) {
		this.isLast1 = isLast1;
	}
	public String getIsLast2() {
		return isLast2;
	}
	public void setIsLast2(String isLast2) {
		this.isLast2 = isLast2;
	}
	public String getLastTm() {
		return lastTm;
	}
	public void setLastTm(String lastTm) {
		this.lastTm = lastTm;
	}
	public String getNextBus() {
		return nextBus;
	}
	public void setNextBus(String nextBus) {
		this.nextBus = nextBus;
	}
	public String getNxtStn() {
		return nxtStn;
	}
	public void setNxtStn(String nxtStn) {
		this.nxtStn = nxtStn;
	}
	public String getPosX() {
		return posX;
	}
	public void setPosX(String posX) {
		this.posX = posX;
	}
	public String getPosY() {
		return posY;
	}
	public void setPosY(String posY) {
		this.posY = posY;
	}
	public String getRerdieDiv1() {
		return rerdieDiv1;
	}
	public void setRerdieDiv1(String rerdieDiv1) {
		this.rerdieDiv1 = rerdieDiv1;
	}
	public String getRerdieDiv2() {
		return rerdieDiv2;
	}
	public void setRerdieDiv2(String rerdieDiv2) {
		this.rerdieDiv2 = rerdieDiv2;
	}
	public String getRerideNum1() {
		return rerideNum1;
	}
	public void setRerideNum1(String rerideNum1) {
		this.rerideNum1 = rerideNum1;
	}
	public String getRerideNum2() {
		return rerideNum2;
	}
	public void setRerideNum2(String rerideNum2) {
		this.rerideNum2 = rerideNum2;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getRtNm() {
		return rtNm;
	}
	public void setRtNm(String rtNm) {
		this.rtNm = rtNm;
	}
	public String getSectNm() {
		return sectNm;
	}
	public void setSectNm(String sectNm) {
		this.sectNm = sectNm;
	}
	public String getSectOrd1() {
		return sectOrd1;
	}
	public void setSectOrd1(String sectOrd1) {
		this.sectOrd1 = sectOrd1;
	}
	public String getSectOrd2() {
		return sectOrd2;
	}
	public void setSectOrd2(String sectOrd2) {
		this.sectOrd2 = sectOrd2;
	}
	public String getStId() {
		return stId;
	}
	public void setStId(String stId) {
		this.stId = stId;
	}
	public String getStNm() {
		return stNm;
	}
	public void setStNm(String stNm) {
		this.stNm = stNm;
	}
	public String getStaOrd() {
		return staOrd;
	}
	public void setStaOrd(String staOrd) {
		this.staOrd = staOrd;
	}
	public String getStationTp() {
		return stationTp;
	}
	public void setStationTp(String stationTp) {
		this.stationTp = stationTp;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTraSpd1() {
		return traSpd1;
	}
	public void setTraSpd1(String traSpd1) {
		this.traSpd1 = traSpd1;
	}
	public String getTraSpd2() {
		return traSpd2;
	}
	public void setTraSpd2(String traSpd2) {
		this.traSpd2 = traSpd2;
	}
	public String getTraTime1() {
		return traTime1;
	}
	public void setTraTime1(String traTime1) {
		this.traTime1 = traTime1;
	}
	public String getTraTime2() {
		return traTime2;
	}
	public void setTraTime2(String traTime2) {
		this.traTime2 = traTime2;
	}
	public String getVehId1() {
		return vehId1;
	}
	public void setVehId1(String vehId1) {
		this.vehId1 = vehId1;
	}
	public String getVehId2() {
		return vehId2;
	}
	public void setVehId2(String vehId2) {
		this.vehId2 = vehId2;
	}
	
}
