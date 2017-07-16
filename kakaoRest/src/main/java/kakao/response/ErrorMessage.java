package kakao.response;

public class ErrorMessage {
	int status;
	String code;
	String message;
	String link;
	String developerMessage;
	int total;
	
	public ErrorMessage(int status, String code, String message, String link, String developerMessage, int total) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.link = link;
		this.developerMessage = developerMessage;
		this.total = total;
	}
	public ErrorMessage() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
