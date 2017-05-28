package kakao.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kakao.domain.Message;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("message")
public class kakaoMessage {

	@RequestMapping(method = RequestMethod.POST)
	String returnMessage(@RequestBody Message message){

		System.out.println(message.toString());
		
		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<>();

		map.put("text", "감사합니다");

		jsonObject.put("message", map);

		String json = jsonObject.toString();
		
		return json;
	}

}
