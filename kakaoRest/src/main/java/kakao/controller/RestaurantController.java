package kakao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kakao.bookmark.dao.DbMapper;
import kakao.bookmark.domain.restaurant_Bookmark_VO;
import kakao.bookmark.service.DbService;
import naver.api.LocationNaverApi;
import naver.api.Restaurant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class RestaurantController {

	private LocationNaverApi naverApi = new LocationNaverApi();
	private Restaurant user_restaurant;
	
	restaurant_Bookmark_VO vo=null;
	
	@Autowired
	private DbService db;

	
	public String restaurantLocationMessage() {

		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		String textMessage = "어느 지역 맛 집인지  말해줘~(방긋) \n역이름으로 입력하면 더욱더 정확한 정보를 줄 수 있습니다.\n\nex)강남역,건대입구역";

		map.put("text", textMessage);

		jsonObject.put("message", map);

		String json = jsonObject.toString();

		return json;
	}

	public String restaurantKindMessage() {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		map.put("text", "뭐 먹을래?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("한식");
		list.add("양식");
		list.add("중식");
		list.add("기타");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		String json = jsonObject.toString();

		return json;
	}

	public String restaurantApiMessage(String locationName, String foodKind) {

		JSONObject jsonObject = new JSONObject();

		JSONObject jsonObjectKeyboard = new JSONObject();

		Map<String, Object> message = new LinkedHashMap<>();

		List<Restaurant> restaurantlist = naverApi.getNaverApi(locationName, foodKind);//음식점 리스트 vo에 저장해서 list화 시킴. Restaurant는 vo객체

		Random random = new Random();

		int number = random.nextInt(restaurantlist.size());//음식적 크기만큼 랜덤으로 돌림.
		String textMessage = null;
		//각 식당마다 갖고 있는 정보가 달라서 if문으로 나눈듯
		if ("".equals(restaurantlist.get(number).getDescription())) {

			textMessage ="(별)"+restaurantlist.get(number).getTitle() + "(별)\n\n" + "주소 : "
					+ restaurantlist.get(number).getRoadAddress() + "\n\n" + "전화번호 : "
					+ restaurantlist.get(number).getTelephone();

		} else if ("".equals(restaurantlist.get(number).getTelephone())) {

			textMessage ="(별)"+ restaurantlist.get(number).getTitle() + "(별)\n\n" + "설명 :"
					+ restaurantlist.get(number).getDescription() + "\n\n" + "주소 : "
					+ restaurantlist.get(number).getRoadAddress() + "\n\n";

		} else if ("".equals(restaurantlist.get(number).getDescription())
				&& "".equals(restaurantlist.get(number).getTelephone())) {

			textMessage = "(별)"+restaurantlist.get(number).getTitle() + "(별)\n\n" + "주소 : "
					+ restaurantlist.get(number).getRoadAddress() + "\n\n";

		} else {
			textMessage ="(별)"+restaurantlist.get(number).getTitle() + "(별)\n\n" + "설명 :"
					+ restaurantlist.get(number).getDescription() + "\n\n" + "주소 : "
					+ restaurantlist.get(number).getRoadAddress() + "\n\n" + "전화번호 : "
					+ restaurantlist.get(number).getTelephone();
		}

		message.put("text", textMessage);

		Map<String, String> url = new HashMap<>();
		url.put("label", "자세히 보기");
		url.put("url", "https://search.naver.com/search.naver?query=" + restaurantlist.get(number).getTitle());
		user_restaurant = restaurantlist.get(number);
		message.put("message_button", url);

		jsonObject.put("message", message);

		vo=new restaurant_Bookmark_VO();
		vo.setLocationName(locationName);
		vo.setFoodkind(foodKind);
		vo.setRestIndex(number);
		vo.setTitle(user_restaurant.getTitle());
		jsonObjectKeyboard.put("type", "buttons");
		
		
		List<String> list = new ArrayList<>();
		list.add("즐겨찾기에 추가할래");
		list.add("다른 거 추천해줘");
		list.add("처음으로");
		JSONArray array = JSONArray.fromObject(list);

		jsonObjectKeyboard.put("buttons", array);

		jsonObject.put("keyboard", jsonObjectKeyboard);

		String json = jsonObject.toString();

		return json;
	}

//	public String restaurantEatMessage() {
//		JSONObject jsonObject = new JSONObject();
//
//		Map<String, String> map = new HashMap<>();
//
//		map.put("text", "맛있게 먹어~(좋아)");
//
//		jsonObject.put("message", map);
//
//		String json = jsonObject.toString();
//		return json;
//	}
	
	
	public String restaurantBookmarkMessage(String user_key) {
	
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectKeyboard = new JSONObject();
	
		Map<String, String> map = new HashMap<>();
		
		vo.setUser_key(user_key);
		
		try{
		db.insert_Restaurantbookmark(vo);
		}
		catch(DataAccessException e){
			map.put("text", "이미 즐겨찾기에 추가되어 있어~ 확인해봐 (좋아)");
			jsonObject.put("message", map);
			
			jsonObjectKeyboard.put("type", "buttons");
			List<String> list = new ArrayList<>();
			list.add("다른 거 추천해줘");
			list.add("처음으로");
			JSONArray array = JSONArray.fromObject(list);

			jsonObjectKeyboard.put("buttons", array);

			jsonObject.put("keyboard", jsonObjectKeyboard);

			String json = jsonObject.toString();
		
			return json;
			
		}
		map.put("text", "즐겨찾기에 추가됬어~(좋아)");
		jsonObject.put("message", map);
		
		jsonObjectKeyboard.put("type", "buttons");
		List<String> list = new ArrayList<>();
		list.add("다른 거 추천해줘");
		list.add("처음으로");
		JSONArray array = JSONArray.fromObject(list);

		jsonObjectKeyboard.put("buttons", array);

		jsonObject.put("keyboard", jsonObjectKeyboard);

		String json = jsonObject.toString();
	
		return json;
}

	public String restaurantEtcMessage() {
		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		map.put("text", "어떤거 먹을거야? \n ex)치킨,커피");

		jsonObject.put("message", map);

		String json = jsonObject.toString();
		return json;
	}

	public String restaurantAgainLocMessage() {

		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		map.put("text", "죄송합니다. 무슨 말씀인지 모르겠어요.. \n\n특정 역 이름이나 지역명을 입력해주면 더욱더 정확한 답변을 드릴 수 있습니다^^");

		jsonObject.put("message", map);

		String json = jsonObject.toString();
		return json;

	}

	public String restaurantAgainEtcMessage() {

		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		map.put("text", "죄송합니다. 무슨 말씀인지 모르겠어요.. \n\n특정 음식을 입력해주면 더욱더 정확한 답변을 드릴 수 있습니다^^ ");

		jsonObject.put("message", map);

		String json = jsonObject.toString();
		return json;

	}
}
