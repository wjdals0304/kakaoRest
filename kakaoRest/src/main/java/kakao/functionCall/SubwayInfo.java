package kakao.functionCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.TrafficInfoController;
import kakao.domain.Message;

@Component
public class SubwayInfo implements functionCallConfig{
	
	@Autowired
	private TrafficInfoController trafficInfo;
	
	private int subway_button = 0;
	private String station_name;
	private String station_line_bookmark;

	
	public String getMessage(Message message) {
		
		if(subway_button == 0) {	// 1)지하철 버튼을 누른다
			subway_button = 1;
			return trafficInfo.press_subway_button();
		}
		else if(subway_button == 1) {	// 2)지하철역 이름을 입력한다
			subway_button = 2;
			station_name = message.getContent();
			if(station_name.charAt(station_name.length()-1) == '역') {
				station_name = station_name.substring(0, station_name.length()-1);
			}
			return trafficInfo.choose_subway_line(station_name);	// 입력한 지하철역의 호선을 버튼으로 전달
		}
		else if(subway_button == 2) {	//3) 지하철 호선을 선택한다
			subway_button = 3;
			String station_line = message.getContent();
			station_line_bookmark=station_line;

			return trafficInfo.write_subway_name(station_name, station_line);
		}
		else if(subway_button == 3) {	//4) 다른역 검색, 즐겨찾는 구간 추가, 처음으로 버튼 중 1개를 클릭한다
			if(message.getContent().equals("다른 역 검색")) {
				subway_button = 1;
				return trafficInfo.press_subway_button();
			}
			else if(message.getContent().equals("즐겨찾는 역 추가")) {
				subway_button = 0;
				return trafficInfo.write_subway_bookmark(station_name, station_line_bookmark,message.getUser_key());
				
			}
			else if(message.getContent().equals("처음으로")) {	// "처음으로" 버튼 클릭시
				
			}
		}
		else {
	
		
		}

		return null;
	}
	
	public void init() {
		subway_button = 0;
	}

}
