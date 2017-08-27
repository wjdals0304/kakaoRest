package kakao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "msgBody")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStationByNameList_msgBody {

	@XmlElement(name = "itemList")
	private List<GetStationByNameList_ItemList> itemList;

	public GetStationByNameList_msgBody(List<GetStationByNameList_ItemList> itemList) {
		super();
		this.itemList = itemList;
	}

	public GetStationByNameList_msgBody() {
		super();
	}

	public List<GetStationByNameList_ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<GetStationByNameList_ItemList> itemList) {
		this.itemList = itemList;
	}
	
}
