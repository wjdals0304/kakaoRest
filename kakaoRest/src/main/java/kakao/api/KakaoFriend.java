package kakao.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kakao.domain.Friend;

@RestController
@RequestMapping("friend")
public class KakaoFriend {
		
	@RequestMapping(method = RequestMethod.POST)
	public void addFriend(@RequestBody Friend friend){
		System.out.println(friend.toString());
		
	}
	
	@RequestMapping(value="{user_key}",method = RequestMethod.DELETE)
	public void deleteFriend(@PathVariable String user_key){
		
		System.out.println(user_key);
	}
	
	
}
