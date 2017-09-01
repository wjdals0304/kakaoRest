package kakao.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import kakao.domain.Message;
import kakao.functionCall.BookmarkInfo;
import kakao.functionCall.BusInfo;
import kakao.functionCall.DustInfo;
import kakao.functionCall.RestaurantInfo;
import kakao.functionCall.SubwayInfo;
import kakao.functionCall.WeatherInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("message")
public class kakaoMessage {
	
	static String check = null;

	@Autowired
	private BusInfo bus_info;
	
	@Autowired
	private SubwayInfo subway_info;
	
	@Autowired
	private RestaurantInfo restaurant_info;
	
	@Autowired
	private WeatherInfo weather_info;
	
	@Autowired
	private DustInfo dust_info;

	@Autowired
	private BookmarkInfo bookmark_info;
	
	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message)  {
		
		if (message.getContent().equals("지하철")) { 
			check = "subway"; 
			subway_info.init();

			}
		else if (message.getContent().equals("버스")) { 
			check = "bus"; 
			bus_info.init();
			}
		else if ( message.getContent().equals("맛집추천")) { 
			check = "restr"; 
			}
		else if (message.getContent().equals("날씨")) {
			check = "weather";				
		}
		else if (message.getContent().equals("미세먼지")||message.getContent().equals("미세")) {
			check = "dust";				
		}
		else if(message.getContent().equals("즐겨찾기관리")){
			check = "bookmark";
			bookmark_info.init();
		}
		else if (message.getContent().equals("처음으로")) { 
			check = null;
			bus_info.init();
			subway_info.init();
			return getKeyboard();
			}
		else { 
			
		}
		
		
		if(check.equals("subway")) { 
			return subway_info.getMessage(message); 
			}
		else if (check.equals("bus")) {
			return bus_info.getMessage(message); 
			}
		else if (check.equals("restr")) {
			return restaurant_info.getMessage(message); 
			}
		else if(check.equals("weather")) {
			return weather_info.getMessage(message); 
		}
		else if(check.equals("dust")) {
			return dust_info.getMessage(message); 
		}
		else if(check.equals("bookmark")){
			return bookmark_info.getMessage(message);
		}
		else {
			 return null;
			}
	}
	
	
	String getKeyboard() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<>();
		Map<String, Object> keyboard_map = new HashMap<>();
		List<String> list = new ArrayList<>();

		list.add("날씨");
		list.add("지하철");
		list.add("버스");
		list.add("맛집추천");
		list.add("즐겨찾기관리");
		
		JSONArray array = JSONArray.fromObject(list);
		keyboard_map.put("type", "buttons");
		keyboard_map.put("buttons", array);	

		map.put("text", "처음으로");
		
		jsonObject.put("message", map);
		jsonObject.put("keyboard", keyboard_map);
		String json = jsonObject.toString();

		return json;
	}
	
	
	

	
	
	
	 
}
