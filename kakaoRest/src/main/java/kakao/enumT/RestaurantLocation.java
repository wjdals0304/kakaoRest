package kakao.enumT;

import java.util.HashSet;
import java.util.Set;

public enum RestaurantLocation {
	강남, 홍대, 건대;

	
	public static Set<String> containLocation() {

		RestaurantLocation[] restaurantlocations = RestaurantLocation.values();

		Set<String> locationSet = new HashSet<>();
		for (RestaurantLocation locat : restaurantlocations) {
			locationSet.add(locat.name());
		}

		return locationSet;
	}

}
