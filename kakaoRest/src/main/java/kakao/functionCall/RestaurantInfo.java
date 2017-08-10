package kakao.functionCall;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.RestaurantController;
import kakao.domain.Message;
import kakao.enumT.FoodKind;
import naver.api.Restaurant;

@Component
public class RestaurantInfo implements functionCallConfig{
	
	static String RESTAURANT_FLOW;
	
	Set<String> foodKind = FoodKind.containFoodKind();
	
	private Restaurant restaurantInfo = new Restaurant();
	
	@Autowired
	private RestaurantController restaurant;
	
	public String getMessage(Message message) {
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
		
		return null;
	}

}
