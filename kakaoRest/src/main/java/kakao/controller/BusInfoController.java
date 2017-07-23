package kakao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import kakao.response.GetStationByNameList;
import kakao.response.GetStationByUidItem;
import kakao.response.LowArrInfoByStIdResponse;
import kakao.response.RealtimeStationArrival;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class BusInfoController {
	
	private static RestTemplate restTemplate = new RestTemplate();

	private String service_key = "51KqeRxYvbCqUAHGUSvJ6WhRLh2t8QhUyi0WslGKfblPxQ%2BEZQBWztFlmhIMxNf63tUDxK7F%2B2X%2B6tBEa7E9Lw%3D%3D";
	private String decoded_service_key= null;
	
	private String operation_name = "";
	private String request_url = "http://ws.bus.go.kr/api/rest/arrive/{operation_name}?serviceKey={service_key}";
	private String request_url2 = "http://ws.bus.go.kr/api/rest/stationinfo/{operation_name}?serviceKey={service_key}";
	private LowArrInfoByStIdResponse response;
	private GetStationByNameList getStationByNameList;
	private GetStationByUidItem getStationByUidItem; 
	
	private String response_message;
	
	
	public BusInfoController() {
		super();
	}
	
	public String getDecoded_serviceKey() {
		try {
			decoded_service_key = URLDecoder.decode(service_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decoded_service_key;
	}
	
	public String press_bus_button() {
		
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", "검색할 정류소의 이름이나 정류소번호를 입력해줘! \n ex) 삼각지역 또는 03007");
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String write_bus_num(String station_id) {
		
		String check;
		boolean check_result = false;
		check = station_id.replaceAll("[0-9]", "");
		if(check.length() == 0 && station_id.length() == 5) {
			check_result = true;
		}
		else {
			check_result = false;
		}
		
		if(check_result == true) {
			//입력값이 숫자이면 (정류소번호) (전부 숫자로이루어진 5글자이면)
			response_message = getStationByUidItem(station_id);
		}
		else {	//입력값이 정류소이름이면 */
			return write_bus_name(station_id);
		}
		
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", response_message);
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String write_bus_name(String station_name) {
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> buttons = new ArrayList<String>();
		
		operation_name = "getStationByName";
		request_url2 += "&stSrch={station_name}";
		getStationByNameList = restTemplate.getForObject(request_url2, GetStationByNameList.class,
				operation_name, getDecoded_serviceKey(), "삼각지역");
		
		if(getStationByNameList.getMsgBody().getItemList().size() == 1) {	//이름이 겹치는 정류소가 따로 없다면
			String station_num = getStationByNameList.getMsgBody().getItemList().get(0).getArsId();
			return getStationByUidItem(station_num);
		}
		
		for(int i=0 ; i < getStationByNameList.getMsgBody().getItemList().size() ; i++) {
			String temp = getStationByNameList.getMsgBody().getItemList().get(i).getStNm();
			temp += "[" + getStationByNameList.getMsgBody().getItemList().get(i).getArsId() + "]";
			
			buttons.add(temp);
		}
		
		map.put("text", "버튼의 목록 중 정확한 정류소를 선택해줘!");
		jsonObject.put("message", map);
		
		JSONArray array = JSONArray.fromObject(buttons);
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);
		
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String getStationByUidItem(String station_num) {
		request_url2 += "&arsId={station_num}";
		operation_name = "getStationByUid";
		getStationByUidItem = restTemplate.getForObject(request_url2, GetStationByUidItem.class,
				operation_name, getDecoded_serviceKey(), station_num);
		
		for(int i=0 ; i < getStationByUidItem.getMsgBody().getItemList().size() ; i++) {
			response_message += getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm() + "번\n";
		}
		
		return response_message;
	}
	
	public String getLowArrInfoByStId(String station_id) {
		operation_name = "getLowArrInfoByStId";
		request_url += "&stId={station_id}";
		String temp_station_id = "113000196";
		try {
			decoded_service_key = URLDecoder.decode(service_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		
		response = restTemplate.getForObject(request_url, LowArrInfoByStIdResponse.class,
				operation_name, decoded_service_key, temp_station_id);
		
		response_message += station_id + "@정류소 버스 도착 정보 \n";
		response_message += response.getMsgBody().getItemList().get(0).getStNm() + '\n' +
				response.getMsgBody().getItemList().get(0).getRtNm() + "번 버스 \n" +
				response.getMsgBody().getItemList().get(0).getArrmsg1() + "\n" + 
				response.getMsgBody().getItemList().get(0).getArrmsg2();
		
		return response_message;

	}
}
