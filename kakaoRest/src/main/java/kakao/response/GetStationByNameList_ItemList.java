package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemList")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStationByNameList_ItemList {

	private String arsId;
	private String posX;
	private String posY;
	private String stId;
	private String stNm;
	private String tmX;
	private String tmY;
	public GetStationByNameList_ItemList(String arsId, String posX, String posY, String stId, String stNm, String tmX,
			String tmY) {
		super();
		this.arsId = arsId;
		this.posX = posX;
		this.posY = posY;
		this.stId = stId;
		this.stNm = stNm;
		this.tmX = tmX;
		this.tmY = tmY;
	}
	public GetStationByNameList_ItemList() {
		super();
	}
	public String getArsId() {
		return arsId;
	}
	public void setArsId(String arsId) {
		this.arsId = arsId;
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
	public String getTmX() {
		return tmX;
	}
	public void setTmX(String tmX) {
		this.tmX = tmX;
	}
	public String getTmY() {
		return tmY;
	}
	public void setTmY(String tmY) {
		this.tmY = tmY;
	}
	
}
