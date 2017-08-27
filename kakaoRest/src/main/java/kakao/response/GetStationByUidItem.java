package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceResult")
public class GetStationByUidItem {
	
	@XmlElement(name = "msgHeader")
	private MsgHeader msgHeader;
	
	@XmlElement(name = "msgBody")
	private MsgBody_getStationByUidItem msgBody;

	public GetStationByUidItem(MsgHeader msgHeader, MsgBody_getStationByUidItem msgBody) {
		super();
		this.msgHeader = msgHeader;
		this.msgBody = msgBody;
	}

	public GetStationByUidItem() {
		super();
	}

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public MsgBody_getStationByUidItem getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(MsgBody_getStationByUidItem msgBody) {
		this.msgBody = msgBody;
	}
	
	
}
