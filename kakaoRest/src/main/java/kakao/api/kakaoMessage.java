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
import kakao.controller.Restaurant;
import kakao.controller.TrafficInfoController;
import kakao.domain.Message;
import kakao.enumT.RestaurantLocation;

@RestController
@RequestMapping("message")
public class kakaoMessage {

	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_TF;
	static int button = 0;

	Set<String> locationSet = RestaurantLocation.containLocation();
	List<String> foodKind = new ArrayList<>();
	
	@Autowired
	private Restaurant restaurant;
	
	@Autowired
	private TrafficInfoController trafficInfo;
	
	@Autowired
	private BusInfoController busInfo;

	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message) {

		//정민 start
		if ("맛집추천".equals(message.getContent())) {
			RESTAURANT_LOCATION_NAME=null;
			RESTAURANT_TF="TRUE";
			return restaurant.restaurantMessageFirst();
		}

		if (RESTAURANT_TF.equals("TRUE") && locationSet.contains(message.getContent())) {
			RESTAURANT_LOCATION_NAME = message.getContent();

			foodKind.add("한식");
			foodKind.add("양식");
			foodKind.add("중식");
			foodKind.add("기타");

			String json = restaurant.restaurantMessageSecond();
			return json;
		}

		if (RESTAURANT_TF.equals("TRUE") && foodKind.contains(message.getContent())
				&& !RESTAURANT_LOCATION_NAME.equals(null)) {
			RESTAURANT_TF="FALSE";
			return restaurant.restaurantMessageThird(RESTAURANT_LOCATION_NAME,message.getContent());
			
		}
		//정민 end
		
		// 지수 담당 start
		if(message.getContent().equals("지하철")) {
			button = 1;
			return trafficInfo.press_subway_button();
		}
		
		if(button == 1) {
			button = 0;
			String subway_name = message.getContent();
			return trafficInfo.write_subway_name(subway_name);
		}
		
		if(message.getContent().equals("버스")) {
			button = 2;
			return busInfo.press_bus_button();
		}
		
		if(button == 2) {
			button = 0;
			String station_id = message.getContent();
			return busInfo.write_bus_num(station_id);
		}
		// 지수 담당 end
                   
		return null;
	}

}
