package kakao.api;

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

@RestController
@RequestMapping("message")
public class kakaoMessage {

	static String RESTAURANT_FLOW;
	static int subway_button = 0;
	static int bus_button = 0;
	
	Set<String> foodKind = FoodKind.containFoodKind();
	String station_name;
	
	private Restaurant restaurantInfo = new Restaurant();

	@Autowired
	private RestaurantController restaurant;

	@Autowired
	private TrafficInfoController trafficInfo;

	@Autowired
	private BusInfoController busInfo;

	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message) {

		// 정민 start
		if ("맛집추천".equals(message.getContent())) {
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
		}
		// 정민 end

		// 지수 담당 start
		if (message.getContent().equals("지하철")) { // 1)지하철 버튼을 누른다
			subway_button = 1;
			return trafficInfo.press_subway_button();
		}

		if (subway_button == 1) { // 2)지하철역 이름을 입력한다
			subway_button = 2;
			station_name = message.getContent();
			return trafficInfo.choose_subway_line(station_name); // 입력한 지하철역의
																	// 호선을 버튼으로
																	// 전달
		}

		if (subway_button == 2) { // 3) 지하철 호선을 선택한다
			subway_button = 3;
			String station_line = message.getContent();
			return trafficInfo.write_subway_name(station_name, station_line);
		}

		if (subway_button == 3) { // 4) 다른역 검색, 즐겨찾는 구간 추가, 처음으로 버튼 중 1개를 클릭한다
			if (message.getContent().equals("다른 역 검색")) {
				subway_button = 1;
				return trafficInfo.press_subway_button();
			} else if (message.getContent().equals("즐겨찾는 역 추가")) {

			} else { // "처음으로" 버튼 클릭시

			}
		}

		if (message.getContent().equals("버스")) {
			bus_button = 1;
			return busInfo.press_bus_button();
		}

		if (bus_button == 1) {
			bus_button = 2;
			String station_id = message.getContent();
			return busInfo.write_bus_num(station_id);
		}
		// 지수 담당 end

		return null;
	}

}
