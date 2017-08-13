package kakao.functionCall;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.DustController;
import kakao.domain.Message;
import net.sf.json.JSONException;

@Component
public class DustInfo implements functionCallConfig {
	
	@Autowired
	private DustController du;
	
	int count = 2; 
	
	public String getMessage(Message message) {
		if( count == 2 && ("미세먼지".equals(message.getContent())||"미세".equals(message.getContent()))) {			//처음. 날씨버튼 클릭 이후 text형태 설명.
			count = 3;
			try {
				return du.Dust1();
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if( count ==  3) {
			count = 2;
			try {
				return du.Dust2(message.getContent());
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {}

		return null;
	}

}
