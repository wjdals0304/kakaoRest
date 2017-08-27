package kakao.functionCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.BusInfoController;
import kakao.domain.Message;

@Component
public class BusInfo implements functionCallConfig{
	
	@Autowired
	private BusInfoController busInfo;
	
	
	static String station_id;
	int bus_button = 0;
	
	public String getMessage(Message message) {
		
		if(bus_button == 0) {		//1) 버스 버튼을 누른다
			bus_button = 1;
			return busInfo.press_bus_button();
		}
		else if(bus_button == 1) {	//2) 정류소번호나 정류소이름을 입력한다

			String tmp = message.getContent();
			String check = tmp.replaceAll("[0-9]", "");
			if(check.length() == 0 && tmp.length() == 5) {	//입력값이 숫자이면 (정류소번호) (전부 숫자로이루어진 5글자이면)
				station_id = tmp;
				bus_button = 2;
				return busInfo.getStationByUidItem(station_id);
			}
			else if(check.length() == 0 && tmp.length() != 5) {	//잘못된 입력값이면
				return null;
			}
			else {		//입력값이 정류소이름이면 
				bus_button = 3;
				return busInfo.write_station_name(tmp);
			}

		}
		else if(bus_button == 2) {
			bus_button = 4;
			String bus_num = message.getContent();
			return busInfo.choose_bus_num(bus_num, station_id);
		}
		else if(bus_button == 3) {
			bus_button = 2;
			int index = message.getContent().indexOf('[');
			station_id = message.getContent().substring(index + 1, index + 6);
			return busInfo.getStationByUidItem(station_id); 
		}
		else if(bus_button == 4) {
			String msg = message.getContent();
			if(msg.equals("다른 버스 검색")) {
				bus_button = 2;
				return busInfo.getStationByUidItem(station_id); 
			}
			else if(msg.equals("다른 정류소 검색")) {
				bus_button = 1;
				return busInfo.press_bus_button();
			}
			else if(msg.equals("즐겨찾는 버스 추가")) {
				bus_button = 1;
				return busInfo.bus_bookmark(message.getUser_key());
				
			}
			
		}
		else {

		}
		return null;
	}
	
	public void init() {
		bus_button = 0;
	}

}
