package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "msgHeader")
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgHeader {
	private int headerCd;
	private String headerMsg;
	private int itemCount;
	
	public MsgHeader(int headerCd, String headerMsg, int itemCount) {
		super();
		this.headerCd = headerCd;
		this.headerMsg = headerMsg;
		this.itemCount = itemCount;
	}
	public MsgHeader() {
		super();
	}
	public int getHeaderCd() {
		return headerCd;
	}
	public void setHeaderCd(int headerCd) {
		this.headerCd = headerCd;
	}
	public String getHeaderMsg() {
		return headerMsg;
	}
	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

}
