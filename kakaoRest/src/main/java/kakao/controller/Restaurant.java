package kakao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class Restaurant {

	public String restaurantMessageFirst() {

		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		map.put("text", "어느지역 맛집인지 말해줘~");

		jsonObject.put("message", map);

		String json = jsonObject.toString();

		return json;
	}

	public String restaurantMessageSecond() {
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

	public String restaurantMessageThird(String locationName, String foodKind) {
		JSONObject jsonObject = new JSONObject();

		JSONObject jsonObjectKeyboard = new JSONObject();

		Map<String, Object> message = new LinkedHashMap<>();
		message.put("text", locationName);

		Map<String, String> url = new HashMap<>();
		url.put("label", "자세히 보기");
		url.put("url", "http://www.naver.com");

		message.put("message_button", url);
		
		jsonObject.put("message", message);

		jsonObjectKeyboard.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("이거 먹을래 ");
		list.add("다른거 추천해줘");
		list.add("처음으로");
		JSONArray array = JSONArray.fromObject(list);

		jsonObjectKeyboard.put("buttons", array);

		jsonObject.put("keyboard", jsonObjectKeyboard);

		String json = jsonObject.toString();


		return json;

	}

}
