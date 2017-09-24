package kakao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "shortestRoute")
public class ShortestRoute {
	
	@XmlElement(name = "RESULT")
	private ErrorMessage errorMessage;
	
	@XmlElement(name = "row")
	private List<ShortestRoute_row> row;
	
	public ShortestRoute(ErrorMessage errorMessage, List<ShortestRoute_row> row) {
		super();
		this.errorMessage = errorMessage;
		this.row = row;
	}
	
	public ShortestRoute() {
		super();
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<ShortestRoute_row> getRow() {
		return row;
	}
	public void setRow(List<ShortestRoute_row> row) {
		this.row = row;
	}
	
	

}
