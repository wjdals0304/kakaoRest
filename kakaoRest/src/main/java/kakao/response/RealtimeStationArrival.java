package kakao.response;

import java.util.List;

public class RealtimeStationArrival {

	private ErrorMessage errorMessage;
	private List<RealtimeArrivalList> realtimeArrivalList;
	
	public RealtimeStationArrival(ErrorMessage errorMessage, List<RealtimeArrivalList> realtimeArrivalList) {
		super();
		this.errorMessage = errorMessage;
		this.realtimeArrivalList = realtimeArrivalList;
	}

	public RealtimeStationArrival() {
		super();
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<RealtimeArrivalList> getRealtimeArrivalList() {
		return realtimeArrivalList;
	}

	public void setRealtimeArrivalList(List<RealtimeArrivalList> realtimeArrivalList) {
		this.realtimeArrivalList = realtimeArrivalList;
	}
	
}
