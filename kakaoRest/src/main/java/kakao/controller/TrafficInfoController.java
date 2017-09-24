package kakao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import kakao.response.RealtimeStationArrival;
import kakao.response.ShortestRoute;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class TrafficInfoController {

	private static RestTemplate restTemplate = new RestTemplate();

	private String service_name = null;
	private String api_key = "44516b5859617330313138684771494c";
	private String decoded_api_key = null;

	private RealtimeStationArrival response;
	private ShortestRoute response2;


	public TrafficInfoController() {

	}


	public String getDecoded_serviceKey() {		//서비스키 decode한 값을 리턴
		
		String decoded_service_key = null;
		try {
			decoded_service_key = URLDecoder.decode(api_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decoded_service_key;
	}
	
	public boolean is_subway_name(String subway_name) {
		//Map<Integer, String> station_name = get_station_name();
		return true;
	}
	
	public void set_response(String station_name) {
		service_name = "realtimeStationArrival";
		String url = "http://swopenAPI.seoul.go.kr/api/subway/{api_key}/json/{service_name}/0/20/{station_name}";
		String response_message;	

		this.response = restTemplate.getForObject(url, RealtimeStationArrival.class,
				getDecoded_serviceKey(), service_name, station_name);
		
		/*if(response.) {
			response = null;
		}*/
		
	}
	
	public String change_station_line_toNumber(String station_line) {
		
		switch(station_line) {
		case "경의 중앙선":
			station_line = "1063";
			break;
		case "공항철도":
			station_line = "1065";
			break;
		case "신분당선":
			station_line = "1077";
			break;
		case "분당선":
			station_line = "1075";
			break;
		case "경춘선":
			station_line = "1067";
			break;
		case "수인선":
			station_line = "1071";
			break;
		default:
			station_line = "100" + station_line.charAt(0);
			break;
		}
		
		return station_line;
	}
	
	public String change_station_line_toButton(String station_line) {
		switch(station_line) {
		case "1063":
			station_line = "경의 중앙선";
			break;
		case "1065":
			station_line = "공항철도";
			break;
		case "1077":
			station_line = "신분당선";
			break;
		case "1075":
			station_line = "분당선";
			break;
		case "1067":
			station_line = "경춘선";
			break;
		case "1071":
			station_line = "수인선";
			break;
		default:
			station_line = station_line.charAt(3) + "호선";
			break;
		}
		return station_line;
	}
	
	public String get_subway_info(String station_name, String station_line) {
		service_name = "realtimeStationArrival";
		String url = "http://swopenAPI.seoul.go.kr/api/subway/{api_key}/json/{service_name}/0/20/{station_name}";
		String response_message = "@" + station_name + "역 " + station_line +  " 도착 정보" + "\n" + "\n";
		int total_data_count;

		station_line = change_station_line_toNumber(station_line);

		this.response = restTemplate.getForObject(url, RealtimeStationArrival.class,
				getDecoded_serviceKey(), service_name, station_name);
		
		total_data_count = response.getErrorMessage().getTotal();

		
		if(response.getErrorMessage().getCode().equals("INFO-200")) {
			response_message = "잘못된 역 이름입니다.";
			return response_message;
		}
		
		else {
			for(int i=0 ; i<total_data_count ; i++) {
				if(response.getRealtimeArrivalList().get(i).getSubwayId().equals(station_line)) {
					response_message += response.getRealtimeArrivalList().get(i).getTrainLineNm() + "\n";
					response_message += "도착 정보 : " + response.getRealtimeArrivalList().get(i).getArvlMsg2() + "\n";
					response_message += "\n";
				}
				else {
					continue;
				}
			}
			
			return response_message;
		}

	}
	
	public String press_subway_button() {		// step1 : 지하철역 버튼을 눌렀을 때
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", "1. 지하철 도착 정보를 알고싶으면 검색할 지하철역의 이름을  알려줘!(꺄아) \n예)강남, 용산, 홍대입구 \n\n"
				+ "2. 지하철 역 간 최단경로 및 시간을 알고싶으면 출발역과 도착역을 함께 알려줘!(좋아) (공백 필수) \n예)삼각지 홍대입구");
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String choose_subway_line(String station_name) {	// step2 : 지하철이름을 입력했을 때 해당 역에 대한 호선을 버튼으로 전달.
															// 만약 호선이 1개이면 바로 메시지로 전달
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> subway_line_list = new ArrayList<String>();
		
		set_response(station_name);
		
		if(response == null) {
			map.put("text", "잘못된 역 이릅입니다. 역 이름을 확인해주세요!");
			jsonObject.put("message", map);
			
			List<String> list = new ArrayList<>();
			list.add("다른 역 검색");
			list.add("처음으로");
			
			JSONArray array = JSONArray.fromObject(list);
			
			keyboard_map.put("type", "buttons");
			keyboard_map.put("buttons", array);	
			jsonObject.put("keyboard", keyboard_map);
			
			String json = jsonObject.toString();
			return json;
		}
		
		map.put("text", "검색 할 호선을 선택해줘!");
		jsonObject.put("message", map);
		
		int total_data_count = response.getErrorMessage().getTotal();
		
		for(int i=0 ; i<total_data_count ; i++) {
			String temp = response.getRealtimeArrivalList().get(i).getSubwayId();
			temp = change_station_line_toButton(temp);
			
			if(subway_line_list.contains(temp) == false) {
			subway_line_list.add(temp);
			}

		}
		
		if(subway_line_list.size() == 1) { 
			return write_subway_name(station_name, subway_line_list.get(0)); 
		}
		
		JSONArray array = JSONArray.fromObject(subway_line_list);
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);
		
		String json = jsonObject.toString();
		return json;
		
	}

	public String write_subway_name(String station_name, String station_line) {	// step3 : 지하철역 버튼을 누른 후 지하철역을 입력했을 때
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		
		String response_message = get_subway_info(station_name, station_line);
		
		map.put("text", response_message);
		jsonObject.put("message", map);
		
		List<String> list = new ArrayList<>();
		list.add("다른 역 검색");
		list.add("즐겨찾는 역 추가");
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);
		
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);
		
		String json = jsonObject.toString();
		return json;
	}
	
	public String set_response_shortestRoute(String departure, String arrive) {
		
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String url = "http://swopenapi.seoul.go.kr/api/subway/{api_key}/xml/shortestRoute/0/5/{departure}/{arrive}";
		String url2 = "http://swopenAPI.seoul.go.kr/api/subway/{api_key}/json/{service_name}/0/20/{station_name}";
		this.response2 = restTemplate.getForObject(url, ShortestRoute.class, getDecoded_serviceKey(), 
				departure, arrive); 
		
		RealtimeStationArrival departure_info = restTemplate.getForObject(url2, RealtimeStationArrival.class, 
				getDecoded_serviceKey(), "realtimeStationArrival", departure);
		
		String station_list_before = StringUtils.deleteWhitespace(response2.getRow().get(0).getShtStatnNm().get(0));
		
		String departure_id = response2.getRow().get(0).getStatnFid();
		
		String[] station_id_list = StringUtils.split(response2.getRow().get(0).getShtStatnId().get(0), ',');
		String[] station_list = StringUtils.split(station_list_before, ','); // "삼각지", "효창공원", "공덕" ...
		
		String first = station_id_list[0].substring(0, 4);	// 1006
		String first2 = change_station_line_toButton(first);	//출발역 호선
		
		int change_line = 0;	//호선이 바뀌는 인덱스
		String change_subway = "";
		
		String message = "[" + station_list[0] + "(" + first2 + ") - ";
		
		for(int i=1 ; i<station_id_list.length ; i++) {	
			if((station_id_list[i].substring(0, 4)).equals(first) == false) {
				change_line = i;
				change_subway = station_id_list[i].substring(0, 4);
			}
		}
		
			for(int i=1 ; i<station_list.length ; i++) {
				if(i == change_line) {
					message += station_list[i] + "(환승:" + change_station_line_toButton(change_subway) + ")";
				}
				else {
					message += station_list[i];
					if(i != station_list.length - 1) { message += " - "; }
				}
			}
		
		
		message += "]" + "\n";
		message += response2.getRow().get(0).getShtTransferMsg();
		//message += "\n" + station_id_list;
		
		map.put("text", message);
		jsonObject.put("message", map);
		
		String json = jsonObject.toString();
		return json;
	}
}
