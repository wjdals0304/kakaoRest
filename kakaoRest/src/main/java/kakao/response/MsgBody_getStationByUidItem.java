package kakao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "msgBody")
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgBody_getStationByUidItem {

	@XmlElement(name = "itemList")
	private List<GetStationByUid_ItemList> itemList;

	public MsgBody_getStationByUidItem(List<GetStationByUid_ItemList> itemList) {
		super();
		this.itemList = itemList;
	}

	public MsgBody_getStationByUidItem() {
		super();
	}

	public List<GetStationByUid_ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<GetStationByUid_ItemList> itemList) {
		this.itemList = itemList;
	}
	
}
