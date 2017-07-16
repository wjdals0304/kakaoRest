package kakao.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kakao.controller.Restaurant;
import kakao.domain.Message;
import kakao.enumT.RestaurantLocation;

@RestController
@RequestMapping("message")
public class kakaoMessage {

	private static String RESTAURANT_LOCATION_NAME;
	private static String RESTAURANT_TF;

	Set<String> locationSet = RestaurantLocation.containLocation();
	List<String> foodKind = new ArrayList<>();

	@Autowired
	private Restaurant restaurant;

	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message) {

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
			
			return restaurant.restaurantMessageThird(RESTAURANT_LOCATION_NAME,message.getContent());
			
		}
                   
		return null;
	}

}
