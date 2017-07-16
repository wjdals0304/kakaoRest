package kakao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import kakao.response.LowArrInfoByStIdResponse;
import kakao.response.RealtimeStationArrival;
import net.sf.json.JSONObject;

@Component
public class BusInfoController {
	
	private static RestTemplate restTemplate = new RestTemplate();

	private String service_key = "51KqeRxYvbCqUAHGUSvJ6WhRLh2t8QhUyi0WslGKfblPxQ%2BEZQBWztFlmhIMxNf63tUDxK7F%2B2X%2B6tBEa7E9Lw%3D%3D";
	private String decoded_service_key= null;
	
	private String operation_name = "";
	private String request_url = "http://ws.bus.go.kr/api/rest/arrive/{operation_name}?serviceKey={service_key}&stId={station_id}";
	//http://ws.bus.go.kr/api/rest/arrive/getLowArrInfoByStId?stId=23-287 get요청후, header에 serviceKey 설정
	
	private LowArrInfoByStIdResponse response;
	private String response_message;
	
	
	public BusInfoController() {
		super();
	}
	
	public String press_bus_button() {
		
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", "검색할 정류소를 입력해주세요!");
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String write_bus_num(String station_id) {
		String response_message = getLowArrInfoByStId(station_id);
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", response_message);
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}
	
	public String getLowArrInfoByStId(String station_id) {
		operation_name = "getLowArrInfoByStId";
		String temp_station_id = "113000196";
		try {
			decoded_service_key = URLDecoder.decode(service_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		
		response = restTemplate.getForObject(request_url, LowArrInfoByStIdResponse.class,
				operation_name, decoded_service_key, station_id);
		
		/*String res = restTemplate.getForObject(request_url, String.class,
				operation_name, decoded_service_key, temp_station_id);*/
		
		response_message = response.getMsgBody().getItemList().get(0).getStNm() + '\n' +
				response.getMsgBody().getItemList().get(0).getRtNm();
		return response_message;

	}
}
