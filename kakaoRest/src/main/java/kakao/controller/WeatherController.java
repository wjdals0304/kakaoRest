package kakao.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kakao.functionCall.WeatherInfo;
import kakao.functionCall.functionCallConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


@Component
//@RestController
//@RequestMapping("kakao/controller/weather")
public class WeatherController {
	
	private String url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib";
	private String serviceKey = "?ServiceKey=An5Xhy3wYorHujPQFkVLhw%2BV86Pe4wRrnkylb2vtb2U2eetOLubZtDCiipTy98AnV2NwrHjeCzizO70PCSQrxw%3D%3D";
	private int lat;	//좌표

//	@RequestMapping(method = RequestMethod.GET)
	
	public String Weather1() {

		JSONObject jsonObject = new JSONObject();
		
		Map<String, String> map = new HashMap<>();
		
		map.put("text", "날씨를 보시려면 '서울'이라고 입력해주세요!\n"+"서울시 미세먼지 정보는" + "\n" + "'미세먼지' 혹은 '미세'라고 입력해주세요"+"(쑥스)");	

		jsonObject.put("message", map);

		String json = jsonObject.toString();

		return json;
	}

	
	public String Weather2(String gu)throws JSONException, IOException{
		JSONObject jsonObject = new JSONObject();
		
		JSONObject js = null;
		
		Map<String, String> map = new HashMap<>();
		
		//String gu = "광진구";
		
		//String gu ="서울";
		
		String guName[] = {"서울","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구", "동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"}; 
		
		for(int i = 0; i < guName.length; i++) {
			if(gu.equals(guName[i])||guName[i].contains(gu)) {
				lat = i;
			}
		}
		
		String result;
		
		StringBuilder urlBuilder = new StringBuilder(url+serviceKey);
		
		int nx[] = {60, 61, 62, 61, 58, 59, 62, 58, 59, 61, 61, 61, 59, 59, 59, 61, 61, 61, 62, 58, 58, 60, 59, 60, 60, 62};	//x좌표
		int ny[] = {127, 126, 126, 128, 126, 125, 126, 125, 124, 129, 129, 127, 125, 127, 127, 125, 127, 127, 126, 126, 126, 126, 127, 127, 127, 128}; //y좌표
		
		String nx1 = Integer.toString(nx[lat]);
		String ny1 = Integer.toString(ny[lat]);
		Calendar cal = Calendar.getInstance();
		
		System.out.println(nx1+" "+ny1);
		
		String date;
        
		String m = "";
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
	
		if(month <= 9){
			m = "0"+Integer.toString(month);
		}
		else
			m = Integer.toString(month);
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int time = cal.get(Calendar.HOUR_OF_DAY);
		
		int min = cal.get(Calendar.MINUTE);
		
		String base;
		String sample;
		
		URL url;
		
		//정각발표 -> 40분 제공
		if(min < 40) {
			if(time == 0) {
				day = day - 1;
				base = "2300";
				sample = base;
			}
			else if(time >=1 && time <= 9 ) {
				base = "0"+Integer.toString(time-1) + "00";
				sample = Integer.toString(time-1);
			}
			else {
			base = Integer.toString(time-1) + "00";
			sample = Integer.toString(time-1);
			}
		}
		
		else if(time >=1 && time <= 9 ) {
			base = "0"+Integer.toString(time-1) + "00";
			sample = Integer.toString(time-1);
		}
		
		
		else {
			base = Integer.toString(time) +"00";
			sample = Integer.toString(time);
		}
		date = Integer.toString(year)+""+m+""+Integer.toString(day);
		
		//try {
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("SERVICE_KEY", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); //‘00년 00월 00일발표
	        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(base, "UTF-8")); 
	        //?시 발표  정시 단위. 매시간 40분 이후 호출
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx1, "UTF-8")); //예보지점의 X 좌표값
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny1, "UTF-8")); //예보지점의 Y 좌표값
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //한 페이지 결과 수
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //xml(기본값), json
	      
	        url = new URL(urlBuilder.toString());
	        
	        System.out.println(url);
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        BufferedReader rd;
	        
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } 
	        
	        else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        
	        String line;
	        
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        rd.close();
	        
	        conn.disconnect();
	        
	        js = JSONObject.fromObject(sb.toString());
	        
	        System.out.println(sb.toString());
	        
	        JSONObject re = (JSONObject) js.get("response");
	        
	        result = gu+" 현재 날씨는"+"\n"+"발표시각 : "+sample+"시 기준\n";
	        
	        int lgt; //낙뢰 코드 값
	        int pty; //강수형태 코드 값.
	        int rn1; //1시간 강수량.
	        int sky; //하늘 상태 코드 값.
	        
	        if(re.getJSONObject("header").get("resultCode").equals("0000")) {
	        
	        	lgt = re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(0).getInt("obsrValue");
	        
	        	if(lgt == 0 );
	        	else
	        		result += "낙뢰 : 있음.\n";
	        	
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(1).getString("category");
	        	
	        	pty = re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(1).getInt("obsrValue");
	        	
	        	if( pty == 0 );
	        	else if( pty == 1) {
	        		result += "강수형태 : 비가 와요."+"(비)"+"\n";
	        	}
	        	else if( pty == 2) {
	        		result += "강수형태 : 비랑 눈이 섞여 와요."+"(비)(눈)"+"\n";
	        	}
	        	else
	        		result += "강수형태 : 눈이와요."+"(눈)"+"\n";
	        	
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(2).getString("category");
	        	result += "습도 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(2).getInt("obsrValue")+"%"+"\n";
	        	
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(3).getString("category");
	        	
	        	rn1 = re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(3).getInt("obsrValue");
	        	if( rn1 != 0)
	        		result += "1시간강수량 : "+rn1+"mm"+"\n";
	        	
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(4).getString("category");
	        	
	        	sky = re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(4).getInt("obsrValue");
	        	
	        	if( sky == 1)
	        		result += "하늘상태 : 맑아요."+"(해)"+"\n";
	        	else if( sky == 2)
	        		result += "하늘상태 : 구름 조금."+"(구름)"+"\n";
	        	else if( sky == 2)
	        		result += "하늘상태 : 구름 많이."+"(구름)(구름)"+"\n";
	        	else
	        		result += "하늘상태 : 흐려요."+"\n";
	        		
	        	
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(5).getString("category");
	        	result += "기온 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(5).getInt("obsrValue")+"°C"+"\n";
	        	
	        	//result += "UUU동서바람성분 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(6).getInt("obsrValue")+"%";
	        	//result += "VEC풍향 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(7).getInt("obsrValue")+"%";
	        	//result += "VVV남북바람성분 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(8).getInt("obsrValue")+"%";
	        	//result += re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(9).getString("category");
	        	//result += "풍속 : "+re.getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(9).getInt("obsrValue")+"%\n";
	        }
	        
	        //System.out.println(result);
	        
	        if (lat == 0 ) {
	        	result += "\n지역 별 날씨는 OO구 혹은 OO이라고 입력해주세요\n";
	        	map.put("text", result);
		        jsonObject.put("message", map);
		        String json = jsonObject.toString();
		        return json;
	        	}
	        
	        else {
	        	map.put("text", result);
		        jsonObject.put("message", map);
	        	
	    		Map<String, Object> key = new HashMap<>();	//버튼형식으로
	    	    
	    	    List<String> list = new ArrayList<>();

	    		list.add("날씨");
	    		list.add("미세먼지");
	    		list.add("처음으로");

	    		JSONArray array = JSONArray.fromObject(list);
	    		key.put("type", "buttons");
	    		key.put("buttons", array);	
	    		jsonObject.put("keyboard", key);
	    		String json = jsonObject.toString();
	    		
	    		return json;
	        }
	
	}

}
