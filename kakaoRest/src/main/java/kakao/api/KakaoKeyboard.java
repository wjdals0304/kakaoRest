package kakao.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("keyboard")
public class KakaoKeyboard {

	@RequestMapping(method = RequestMethod.GET)
	String getKeyboard() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "buttons");
		
		List<String> list = new ArrayList<>();
		list.add("a1");
		list.add("b2");
		JSONArray array = JSONArray.fromObject(list);
		
		jsonObject.put("buttons", array);
		String json = jsonObject.toString();
		return json;
 	
	}

}
