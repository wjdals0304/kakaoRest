package kakao.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("chat_room")
public class KakaoChatRoom {

	@RequestMapping(value="{user_key}",method = RequestMethod.DELETE)
	public void deleteRoom(@PathVariable String user_key){
		
		System.out.println(user_key);
	}
	
	
}
