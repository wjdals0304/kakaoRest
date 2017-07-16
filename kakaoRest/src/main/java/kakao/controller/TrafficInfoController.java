package kakao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kakao.response.RealtimeStationArrival;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class TrafficInfoController {

	private static RestTemplate restTemplate = new RestTemplate();

	private String service_name = null;
	private String api_key = "44516b5859617330313138684771494c";
	private String decoded_api_key = null;

	private RealtimeStationArrival response;


	public TrafficInfoController() {

	}


	public String get_subway_info(String station_name) {
		service_name = "realtimeStationArrival";
		String url = "http://swopenAPI.seoul.go.kr/api/subway/{api_key}/json/{service_name}/0/5/{station_name}";
		String response_message;
		int total_data_count = 0;
		
		try {
			decoded_api_key = URLDecoder.decode(api_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	

		response = restTemplate.getForObject(url, RealtimeStationArrival.class,
				decoded_api_key, service_name, station_name);

		
		if(response.getErrorMessage().getCode().equals("INFO-200")) {
			response_message = "잘못된 역 이름입니다.";
			return response_message;
		}
		else {
			total_data_count = response.getErrorMessage().getTotal();
			response_message = station_name + "역 도착정보 \n";
			
			/*for(int i=0 ; i<total_data_count ; i++) {
				response_message += response.getRealtimeArrivalList().get(i).getSubwayId().charAt(3) + "호선 ";
				response_message += response.getRealtimeArrivalList().get(i).getTrainLineNm() + "\n";
				response_message += "도착 정보 : " + response.getRealtimeArrivalList().get(i).getArvlMsg2() + "\n";
				response_message += "\n";
			
			}*/
					/*response.getRealtimeArrivalList().get(0).getTrainLineNm() + "\n" +
					"도착 정보 : " + response.getRealtimeArrivalList().get(0).getArvlMsg2() + "\n" +
					response.getRealtimeArrivalList().get(0).getArvlMsg3();*/

			response_message += response.getRealtimeArrivalList().get(0).getSubwayId().charAt(3) + "호선 ";
			response_message += response.getRealtimeArrivalList().get(0).getTrainLineNm() + "\n";
			response_message += "도착 정보 : " + response.getRealtimeArrivalList().get(0).getArvlMsg2() + "\n";
			response_message += "\n";
			
			return response_message;
		}

	}
	
	public String press_subway_button() {		// step1 : 지하철역 버튼을 눌렀을 때
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		
		map.put("text", "검색할 지하철역의 이름을  알려주세요! 예)강남, 용산, 홍대입구");
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		
		return json;
	}

	public String write_subway_name(String subway_name) {	// step2 : 지하철역 버튼을 누른 후 지하철역을 입력했을 때
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		
		String response_message = get_subway_info(subway_name);
		
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
}
