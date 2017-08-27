package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceResult")
public class GetStationByNameList{
	
	@XmlElement(name = "msgHeader")
	private MsgHeader msgHeader;
	
	@XmlElement(name = "msgBody")
	private GetStationByNameList_msgBody msgBody;

	public GetStationByNameList(MsgHeader msgHeader, GetStationByNameList_msgBody msgBody) {
		super();
		this.msgHeader = msgHeader;
		this.msgBody = msgBody;
	}

	public GetStationByNameList() {
		super();
	}

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public GetStationByNameList_msgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(GetStationByNameList_msgBody msgBody) {
		this.msgBody = msgBody;
	}
	
	
}
