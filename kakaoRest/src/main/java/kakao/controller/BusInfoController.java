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

import kakao.bookmark.domain.bus_Bookmark_VO;
import kakao.bookmark.service.DbService;
import kakao.response.GetStationByNameList;
import kakao.response.GetStationByUidItem;
import kakao.response.LowArrInfoByStIdResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class BusInfoController {

	@Autowired
	DbService db;
	
	private String ubus_num="";
	private String ustation_num="";
	
	private static RestTemplate restTemplate;
	
	private String service_key = "51KqeRxYvbCqUAHGUSvJ6WhRLh2t8QhUyi0WslGKfblPxQ%2BEZQBWztFlmhIMxNf63tUDxK7F%2B2X%2B6tBEa7E9Lw%3D%3D";
	private String decoded_service_key= null;

	private String operation_name = "";
	private String request_url = "http://ws.bus.go.kr/api/rest/arrive/{operation_name}?serviceKey={service_key}";
	private String request_url2 = "http://ws.bus.go.kr/api/rest/stationinfo/{operation_name}?serviceKey={service_key}";
	private LowArrInfoByStIdResponse response;
	private GetStationByNameList getStationByNameList;
	private GetStationByUidItem getStationByUidItem; 

	public BusInfoController() {
		super();
		restTemplate = new RestTemplate();
	}

	public static RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public static void setRestTemplate(RestTemplate restTemplate) {
		BusInfoController.restTemplate = restTemplate;
	}

	public LowArrInfoByStIdResponse getResponse() {
		return response;
	}

	public void setResponse(LowArrInfoByStIdResponse response) {
		this.response = response;
	}

	public GetStationByNameList getGetStationByNameList() {
		return getStationByNameList;
	}

	public void setGetStationByNameList(GetStationByNameList getStationByNameList) {
		this.getStationByNameList = getStationByNameList;
	}

	public GetStationByUidItem getGetStationByUidItem() {
		return getStationByUidItem;
	}

	public void setGetStationByUidItem(GetStationByUidItem getStationByUidItem) {
		this.getStationByUidItem = getStationByUidItem;
	}



	public String getDecoded_serviceKey() {		//서비스키 decode한 값을 리턴
		try {
			decoded_service_key = URLDecoder.decode(service_key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decoded_service_key;
	}

	public String press_bus_button() {	// 1. 버스 버튼을 눌렀을 때

		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();

		map.put("text", "검색할 정류소의 이름이나 정류소번호를 입력해줘! \n ex) 삼각지역 또는 03007");
		jsonObject.put("message", map);
		String json = jsonObject.toString();

		return json;
	}

	public String write_station_num(String station_id) {	// 2. 정류소번호나 정류소이름을 입력했을 때
		String check = station_id.replaceAll("[0-9]", "");
		if(check.length() == 0 && station_id.length() == 5) {	//입력값이 숫자이면 (정류소번호) (전부 숫자로이루어진 5글자이면)
			return getStationByUidItem(station_id);
		}
		else {		//입력값이 정류소이름이면 */
			return write_station_name(station_id);
		}

	}

	public String write_station_name(String station_name) {		// 3-1. 정류소 이름을 입력했을 때 관련 문자를 포함한 정류소 목록을 버튼으로 나열
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> buttons = new ArrayList<String>();

		operation_name = "getStationByName";
		String url = request_url2 + "&stSrch={station_name}";
		this.getStationByNameList = restTemplate.getForObject(url, GetStationByNameList.class,
				operation_name, getDecoded_serviceKey(), station_name);

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

	public String getStationByUidItem(String station_num) {		//3-2. 정류소번호를 입력했을 때 해당 정류소의 버스 번호 목록을 버튼으로 나열
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> buttons = new ArrayList<String>();

		String url = request_url2 + "&arsId={station_num}";
		operation_name = "getStationByUid";
		this.getStationByUidItem = restTemplate.getForObject(url, GetStationByUidItem.class,
				operation_name, getDecoded_serviceKey(), station_num);

		for(int i=0 ; i < getStationByUidItem.getMsgBody().getItemList().size() ; i++) {
			/*response_message += getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm() + "번 버스 도착 정보 <"
					+ getStationByUidItem.getMsgBody().getItemList().get(i).getNxtStn() + "방면> \n";
			response_message += "첫 번째 버스 : " + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg1();
			response_message += "첫 번째 버스 : " + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg2();*/
			String bus_num = getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm();
			buttons.add(bus_num);
		}

		map.put("text", "검색할 버스 번호를 선택해줘!");
		jsonObject.put("message", map);

		JSONArray array = JSONArray.fromObject(buttons);
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);

		String json = jsonObject.toString();
		return json;
	}

	public String choose_bus_num(String bus_num, String station_num) {	//4. 3-2에서 버스번호 목록 중 특정 버스번호를 선택했을 때

		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> buttons = new ArrayList<String>();
		String response_message = "";
		ubus_num=bus_num;
		ustation_num=station_num;

		/*request_url2 += "&arsId={station_num}";
		operation_name = "getStationByUid";
		getStationByUidItem = restTemplate.getForObject(request_url2, GetStationByUidItem.class,
				operation_name, getDecoded_serviceKey(), station_num);*/

		for(int i=0 ; i < getStationByUidItem.getMsgBody().getItemList().size() ; i++) {
			if(getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm().equals(bus_num)) {
				response_message += getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm() + "번 버스 도착 정보 ["
						+ getStationByUidItem.getMsgBody().getItemList().get(i).getNxtStn() + "방면] \n";
				response_message += "첫 번째 버스 \n" + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg1();
				if(getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm1() != null) {
					response_message += " - " + getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm1();
				}
				response_message += "\n";

				response_message += "두 번째 버스 \n" + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg2();
				if(getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm2() != null) {
					response_message += " - " + getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm2() + "\n";
				}

			}
		}
		
		buttons.add("다른 버스 검색");
		buttons.add("다른 정류소 검색");
		buttons.add("즐겨찾는 버스 추가");
		buttons.add("처음으로");
		
		JSONArray array = JSONArray.fromObject(buttons);
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);
		
		map.put("text", response_message);
		jsonObject.put("message", map);
		String json = jsonObject.toString();
		return json;
	}

	public String getLowArrInfoByStId(String station_id) {
		String response_message = "";
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
	
	public String bus_bookmark(String user_key) {	//즐겨찾기에 추가 하기.

		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		
		bus_Bookmark_VO vo = new bus_Bookmark_VO();
		String response_message;
		vo.setUser_key(user_key);
		vo.setBus_num(ubus_num);
		vo.setStation_num(ustation_num);

		try{
			db.insert_Busbookmark(vo);
		}
		catch(DataAccessException e){
			
			map.put("text", "이미 즐겨찾기에 추가되어 있어~ 확인해봐 (좋아)");
			jsonObject.put("message", map);
			
			List<String> list = new ArrayList<>();
			list.add("다른 버스 검색");
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
		list.add("다른 버스 검색");
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);
		
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	
		jsonObject.put("keyboard", keyboard_map);
		
		String json = jsonObject.toString();
		return json;
	}
	
	public String print_bus_info(String bus_num, String station_num) {
		
	
		String response_message = "";

		String url = request_url2 + "&arsId={station_num}";
		operation_name = "getStationByUid";
		this.getStationByUidItem = restTemplate.getForObject(url, GetStationByUidItem.class,
				operation_name, getDecoded_serviceKey(), station_num);

		
		for(int i=0 ; i < getStationByUidItem.getMsgBody().getItemList().size() ; i++) {
			if(getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm().equals(bus_num)) {
				
				
				response_message += getStationByUidItem.getMsgBody().getItemList().get(i).getRtNm() + "번 버스 도착 정보 ["
						+ getStationByUidItem.getMsgBody().getItemList().get(i).getNxtStn() + "방면] \n";
				response_message += "첫 번째 버스 \n" + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg1();
				if(getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm1() != null) {
					response_message += " - " + getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm1();
				}
				response_message += "\n";

				response_message += "두 번째 버스 \n" + getStationByUidItem.getMsgBody().getItemList().get(i).getArrmsg2();
				if(getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm2() != null) {
					response_message += " - " + getStationByUidItem.getMsgBody().getItemList().get(i).getStationNm2() + "\n";
				}

				
			}
		}
		
		
		return response_message;
	}
	
}
