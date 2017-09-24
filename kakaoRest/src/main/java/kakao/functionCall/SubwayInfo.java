package kakao.functionCall;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.TrafficInfoController;
import kakao.domain.Message;

@Component
public class SubwayInfo implements functionCallConfig{
	
	@Autowired
	private TrafficInfoController trafficInfo;
	
	int subway_button = 0;
	String station_name;
	
	public String getMessage(Message message) {
		
		if(subway_button == 0) {	// 1)지하철 버튼을 누른다
			subway_button = 1;
			return trafficInfo.press_subway_button();
		}
		else if(subway_button == 1) {	// 2)지하철역 이름을 입력한다
			subway_button = 2;
			station_name = message.getContent();
			
			if(station_name.indexOf(" ") != -1) {	// 지하철 최단경로를 검색할 때
				int index = station_name.indexOf(" ");
				String start = station_name.substring(0, index);
				String dest = station_name.substring(index + 1, station_name.length());
				
				if(start.charAt((start.length() - 1)) == '역') {	// 출발역 이름에 '역'이 포함 되어 있으면 제거
					start = start.substring(0, (start.length() -1));
				}
				if(dest.charAt((dest.length() - 1)) == '역') {	//도착역 이름에 '역'이 포한 되어 있으면 제거
					dest = dest.substring(0, (dest.length() -1));
				}
				
				return trafficInfo.set_response_shortestRoute(start, dest);
			}
			
			if(station_name.charAt(station_name.length()-1) == '역') {	//지하철 도착정보를 검색할 때 (이름에 역을 함꼐 입력했을 경우)
				station_name = station_name.substring(0, station_name.length()-1);
			}
			return trafficInfo.choose_subway_line(station_name);	// 입력한 지하철역의 호선을 버튼으로 전달
			
		}
		else if(subway_button == 2) {	// 3) 지하철 호선을 선택한다
			subway_button = 3;
			String station_line = message.getContent();
			return trafficInfo.write_subway_name(station_name, station_line);
		}
		else if(subway_button == 3) {	// 4) 다른 역 검색, 즐겨찾는 구간 추가, 처음으로 버튼 중 1개를 클릭한다
			if(message.getContent().equals("다른 역 검색")) {
				subway_button = 1;
				return trafficInfo.press_subway_button();
			}
			else if(message.getContent().equals("즐겨찾는 역 추가")) {

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
