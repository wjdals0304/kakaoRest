package kakao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import kakao.bookmark.domain.subway_Bookmark_VO;
import kakao.bookmark.service.DbService;
import kakao.response.RealtimeStationArrival;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class TrafficInfoController {

	@Autowired
	private DbService db;
	
	private static RestTemplate restTemplate = new RestTemplate();

	private String service_name = null;
	private String api_key = "44516b5859617330313138684771494c";
	private String decoded_api_key = null;
	private String station_name_b,station_line_b;
	private RealtimeStationArrival response;


	public TrafficInfoController() {

	}


	public void set_response(String station_name) {
		service_name = "realtimeStationArrival";
		String url = "http://swopenAPI.seoul.go.kr/api/subway/{api_key}/json/{service_name}/0/20/{station_name}";
		String response_message;
		
		try {
			decoded_api_key = URLDecoder.decode(api_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	

		this.response = restTemplate.getForObject(url, RealtimeStationArrival.class,
				decoded_api_key, service_name, station_name);
		
		/*if(response.getErrorMessage().getCode().equals("INFO-200")) {
			return null;
		}
		else {
			return response;
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
		
		try {
			decoded_api_key = URLDecoder.decode(api_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	

		this.response = restTemplate.getForObject(url, RealtimeStationArrival.class,
				decoded_api_key, service_name, station_name);
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
		
		map.put("text", "검색할 지하철역의 이름을  알려줘! 예)강남, 용산, 홍대입구");
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
		
		map.put("text", "검색 할 호선을 선택해줘!");
		jsonObject.put("message", map);
		
		set_response(station_name);
		int total_data_count = response.getErrorMessage().getTotal();
		
		for(int i=0 ; i<total_data_count ; i++) {
			String temp = response.getRealtimeArrivalList().get(i).getSubwayId();
			temp = change_station_line_toButton(temp);
			
			if(subway_line_list.contains(temp) == false) {
			subway_line_list.add(temp);
			}

		}
		
		if(subway_line_list.size() == 1) { 
			station_name_b=station_name;
			station_line_b=subway_line_list.get(0);
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
		
		station_name_b=station_name;
		station_line_b=station_line;
		
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
	
	public String write_subway_bookmark(String user_key) {	//즐겨찾기에 추가 하기.

		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		
		subway_Bookmark_VO bVo = new subway_Bookmark_VO();
		String response_message;
		bVo.setBookmark_station(station_name_b);
		bVo.setBookmark_station_line(station_line_b);
		bVo.setUser_key(user_key);

		try
		{
		db.insert_Subwaybookmark(bVo);
		}
		catch (DataAccessException e){
			map.put("text", "이미 즐겨찾기에 추가되어 있어~ 확인해봐 (좋아)");
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
		map.put("text", "즐겨찾기에 추가됬어~(좋아)");
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
	
	public String print_subway_name(String station_name, String station_line) {
		
		String response_message = get_subway_info(station_name, station_line);
		
		return response_message;
	}
	
	
}
