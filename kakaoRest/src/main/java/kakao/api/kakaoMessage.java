package kakao.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kakao.controller.BusInfoController;
import kakao.controller.RestaurantController;
import kakao.controller.TrafficInfoController;
import kakao.domain.Message;
import kakao.enumT.FoodKind;
import naver.api.Restaurant;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("message")
public class kakaoMessage {

	static String RESTAURANT_FLOW;
	Set<String> foodKind = FoodKind.containFoodKind();
	private Restaurant restaurantInfo = new Restaurant();
	
	static int subway_button = 0;
	static int bus_button = 0;
	
	static String check = null;
	static String station_id;
	String station_name;

	@Autowired
	private RestaurantController restaurant;

	@Autowired
	private TrafficInfoController trafficInfo;

	@Autowired
	private BusInfoController busInfo;

	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message)  {


		// 정민 start
		/*if ("맛집추천".equals(message.getContent())) {
			RESTAURANT_FLOW = "ONE";
			return restaurant.restaurantLocationMessage();
		}

		if (RESTAURANT_FLOW.equals("ONE")) {
			RESTAURANT_FLOW = "TWO";
			restaurantInfo.setLocationName(message.getContent());
			return restaurant.restaurantKindMessage();
		}

		if (RESTAURANT_FLOW.equals("TWO") && foodKind.contains(message.getContent())
				&& !restaurantInfo.getLocationName().equals(null)) {
			RESTAURANT_FLOW = "FALSE";
			restaurantInfo.setKind(message.getContent());
			return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), message.getContent());
		}

		if(RESTAURANT_FLOW.equals("TWO") && "기타".equals(message.getContent())
				&& !restaurantInfo.getLocationName().equals(null)){
			RESTAURANT_FLOW = "THREE";
			return restaurant.restaurantEtcMessage();
		}

		if(RESTAURANT_FLOW.equals("THREE")){
			RESTAURANT_FLOW = "FALSE";
			restaurantInfo.setKind(message.getContent());
			return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), message.getContent());
		}


		if (RESTAURANT_FLOW.equals("FALSE") && "이거 먹을래".equals(message.getContent())) {
			return restaurant.restaurantEatMessage();
		}

		if (RESTAURANT_FLOW.equals("FALSE") && "다른거 추천해줘".equals(message.getContent())) {
			return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), restaurantInfo.getKind());
		}*/
		// 정민 end


		// 지수 담당 start
		if(message.getContent().equals("지하철")) {	// 1) 지하철 버튼을 누른다
			subway_button = 1;
			return trafficInfo.press_subway_button();
		}


		if(subway_button == 1) {	// 2) 지하철역 이름을 입력한다
			subway_button = 2;
			station_name = message.getContent();
			if(station_name.charAt(station_name.length()-1) == '역') {
				station_name = station_name.substring(0, station_name.length()-1);
			}
			return trafficInfo.choose_subway_line(station_name);	// 입력한 지하철역의 호선을 버튼으로 전달
		}

		else if(subway_button == 2) {	// 3) 지하철 호선을 선택한다
			subway_button = 3;
			String station_line = message.getContent();
			return trafficInfo.write_subway_name(station_name, station_line);
		}

		else if(subway_button == 3) {	//4) 다른 역 검색, 즐겨찾는 구간 추가, 처음으로 버튼 중 1개를 클릭한다
			if(message.getContent().equals("다른 역 검색")) {
				subway_button = 1;
				return trafficInfo.press_subway_button();
			}
			else if(message.getContent().equals("즐겨찾는 역 추가")) {

			}
			else if(message.getContent().equals("처음으로")) {	// "처음으로" 버튼 클릭시
				return getKeyboard();
			}
		}




		if(message.getContent().equals("버스")) {		//1) 버스 버튼을 누른다
			bus_button = 1;
			return busInfo.press_bus_button();
		}

		if(bus_button == 1) {	//2) 정류소번호나 정류소이름을 입력한다

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

			}
			else if(msg.equals("처음으로")) {	// 처음으로 버튼 클릭 시
				return getKeyboard();
			}
		}
		else {

		}
		// 지수 담당 end


		return null;
	}

	String getKeyboard() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = new HashMap<>();

		List<String> list = new ArrayList<>();

		list.add("날씨");
		list.add("지하철");
		list.add("버스");
		list.add("맛집추천");

		JSONArray array = JSONArray.fromObject(list);
		map.put("type", "buttons");
		map.put("buttons", array);	

		jsonObject.put("keyboard", map);
		String json = jsonObject.toString();

		return json;
	}

}
