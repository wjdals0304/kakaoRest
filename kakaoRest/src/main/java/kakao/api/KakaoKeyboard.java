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

		list.add("날씨");
		list.add("지하철");
		list.add("버스");
		list.add("맛집추천");
<<<<<<< HEAD
		list.add("즐겨찾기 관리");
=======
>>>>>>> branch 'master' of https://github.com/wjdals0304/kakaoRest

		JSONArray array = JSONArray.fromObject(list);

		jsonObject.put("buttons", array);
		String json = jsonObject.toString();

		return json;
	}

}
