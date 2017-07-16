package kakao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MsgBody {

	@XmlElement(name = "itemList")
	private List<ItemList> itemList;

	public MsgBody(List<ItemList> itemList) {
		super();
		this.itemList = itemList;
	}

	public MsgBody() {
		super();
	}

	public List<ItemList> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemList> itemList) {
		this.itemList = itemList;
	}
	
}
