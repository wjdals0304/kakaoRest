package kakao.enumT;

import java.util.HashSet;
import java.util.Set;

public enum FoodKind {

	한식,양식,중식,기타; 
	
	public static Set<String> containFoodKind(){
		
		FoodKind[] foodKind = FoodKind.values();

		Set<String> foodSet = new HashSet<>();
		for (FoodKind foo : foodKind) {
			foodSet.add(foo.name());
		}

		return foodSet;
		
	}

}
