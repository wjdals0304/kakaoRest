package kakao.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceResult")
public class LowArrInfoByStIdResponse {
	
	@XmlElement(name = "msgHeader")
	private MsgHeader msgHeader;
	
	@XmlElement(name = "msgBody")
	private MsgBody msgBody;

	public LowArrInfoByStIdResponse(MsgHeader msgHeader, MsgBody msgBody) {
		super();
		this.msgHeader = msgHeader;
		this.msgBody = msgBody;
	}

	public LowArrInfoByStIdResponse() {
		super();
	}

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public MsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(MsgBody msgBody) {
		this.msgBody = msgBody;
	}
	
	
}
