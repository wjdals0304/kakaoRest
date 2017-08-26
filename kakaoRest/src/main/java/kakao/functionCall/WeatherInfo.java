package kakao.functionCall;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.controller.WeatherController;
import kakao.domain.Message;
import net.sf.json.JSONException;

@Component
public class WeatherInfo implements functionCallConfig {
	
	@Autowired
	private WeatherController we;
	
	int wewe = 0; 
	
	public String getMessage(Message message) {

		if( wewe == 0 && ("날씨".equals(message.getContent()))) {			//처음. 날씨버튼 클릭 이후 text형태 설명.
			wewe = 1;
			return we.Weather1();
		}

		else if( wewe ==  1 && "서울".equals(message.getContent())) {
			wewe = 2;
			try {
				return we.Weather2(message.getContent());	//두번 째.
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if( wewe ==  2) {
			wewe = 0;
			try {
				return we.Weather2(message.getContent());	//두번 째.
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			wewe = 0;
			return we.Weather1();
		}

		return null;
	}

}
