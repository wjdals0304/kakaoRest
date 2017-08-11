package kakao.functionCall;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.RestaurantController;
import kakao.domain.Message;
import kakao.enumT.FoodKind;
import naver.api.LocationNaverApi;
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
			
		} else if (RESTAURANT_FLOW.equals("ONE")) {
			
		   restaurantInfo.setLocationName(message.getContent());
			
			LocationNaverApi naverApi = new LocationNaverApi();
			List<Restaurant> resList = naverApi.getNaverApi(restaurantInfo.getLocationName(),"");
			
			if(resList.size() ==0){
				return restaurant.restaurantAgainLocMessage();
			}{
				RESTAURANT_FLOW = "TWO";
				return restaurant.restaurantKindMessage();
			}
  			
			
		} else if (RESTAURANT_FLOW.equals("TWO") && foodKind.contains(message.getContent())
				&& !restaurantInfo.getLocationName().equals(null)) {
			
        		  restaurantInfo.setKind(message.getContent());

				  RESTAURANT_FLOW = "FALSE";
			      return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), message.getContent());
			
		} else if(RESTAURANT_FLOW.equals("TWO") && "기타".equals(message.getContent())
				&& !restaurantInfo.getLocationName().equals(null)){
			
            RESTAURANT_FLOW = "THREE";
			return restaurant.restaurantEtcMessage();
			
			
		} else if(RESTAURANT_FLOW.equals("THREE")){
			
			restaurantInfo.setKind(message.getContent());
			
			LocationNaverApi naverApi = new LocationNaverApi();
			List<Restaurant> resList = naverApi.getNaverApi(restaurantInfo.getLocationName(),restaurantInfo.getKind());
			
			if(resList.size() ==0){
				return restaurant.restaurantAgainEtcMessage();
			}else{
				RESTAURANT_FLOW = "FALSE";
				return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), message.getContent());
			}
		} else if (RESTAURANT_FLOW.equals("FALSE") && "이거 먹을래".equals(message.getContent())) {
		
			return restaurant.restaurantEatMessage();
		
		} else if (RESTAURANT_FLOW.equals("FALSE") && "다른거 추천해줘".equals(message.getContent())) {
			
			return restaurant.restaurantApiMessage(restaurantInfo.getLocationName(), restaurantInfo.getKind());
		}

		return null;
	
	}

}
