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
import kakao.functionCall.BusInfo;
import kakao.functionCall.RestaurantInfo;
import kakao.functionCall.SubwayInfo;
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


	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message)  {

		if (message.getContent().equals("지하철")) { 
			check = "subway"; 
			}
		else if (message.getContent().equals("버스")) { 
			check = "bus"; 
			}
		else if ( message.getContent().equals("맛집추천")) { 
			check = "restr"; 
			}
		else if (message.getContent().equals("처음으로")) { 
			check = null;
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
		else {
			return null; 
			}
	}
	
	
	String getKeyboard() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = new HashMap<>();

		List<String> list = new ArrayList<>();

		list.add("날씨");
		list.add("지하철");
		list.add("버스");
		list.add("맛집추천");

		JSONArray array = JSONArray.fromObject(list);
		map.put("type", "buttons");
		map.put("buttons", array);	

		jsonObject.put("keyboard", map);
		String json = jsonObject.toString();

		return json;
	}

}
