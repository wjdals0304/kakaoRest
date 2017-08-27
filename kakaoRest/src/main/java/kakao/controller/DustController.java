package kakao.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

@Component
//@RestController
//@RequestMapping("kakao/controller/dust")

public class DustController {
	
	private String url = "http://openAPI.seoul.go.kr";
	private String url1 = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst";
	private String key = "&ServiceKey=An5Xhy3wYorHujPQFkVLhw%2BV86Pe4wRrnkylb2vtb2U2eetOLubZtDCiipTy98AnV2NwrHjeCzizO70PCSQrxw%3D%3D";
	private int result;
	
	//@RequestMapping(method = RequestMethod.GET)
	
	
	
	public String Dust1()throws JSONException, IOException {
		
		JSONObject jsonObject = new JSONObject();
		
		JSONObject js = null;
		
		Map<String, String> map = new HashMap<>();
		
		String du;
		
		StringBuilder urlBuilder = new StringBuilder(url);
		
		URL url;
		urlBuilder.append(":" + URLEncoder.encode("8088","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("6b53747152737977383942704b7856","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("json","UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("ForecastWarningMinuteParticleOfDustService", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8"));

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
        
        JSONObject re = (JSONObject) js.get("ForecastWarningMinuteParticleOfDustService");
        
        du = "서울시 현재 미세먼지는"+"\n";
        
        if(re.getJSONObject("RESULT").get("CODE").equals("INFO-000")) {
        	du += "발표시각 : "+re.getJSONArray("row").getJSONObject(0).getString("APPLC_DT")+" "+"\n";
        	du += "예/경보 : "+re.getJSONArray("row").getJSONObject(0).getString("FA_ON")+" "+"\n";	//예보.경보
        	du += "오염물질 종류 : "+re.getJSONArray("row").getJSONObject(0).getString("POLLUTANT")+" "+"\n";
        	du += "예보등급 : "+re.getJSONArray("row").getJSONObject(0).getString("CAISTEP")+" "+"\n";
        	du += "행동요령(예보) : "+re.getJSONArray("row").getJSONObject(0).getString("ALARM_CNDT")+" "+"\n";
        	du += "경보등급 : "+re.getJSONArray("row").getJSONObject(0).getString("ALERTSTEP")+" "+"\n";
        	du += "행동요령(경보) : "+re.getJSONArray("row").getJSONObject(0).getString("CNDT1")+" "+"\n";
        }
        else if(re.getJSONObject("RESULT").get("CODE").equals("INFO-200")) {
        	System.out.println("조회된 데이터가 없습니다.");
        	du = "조회된 데이터가 없습니다.";
        }
        else {
        	System.out.println("오류가 발생하였습니다.");
        	du = "오류가 발생하였습니다.";
        }
        
        //System.out.println(du);
        
        du += "\n지역 별 미세먼지는 OO구"+"\n"+ "혹은 OO이라고 입력해주세요.:D"+"\n";
        
        map.put("text", du);
        
		jsonObject.put("message", map);

		String json = jsonObject.toString();

		return json;
	}
	
	
	public String Dust2(String gu)throws JSONException, IOException {
		
		//String gu = "광진구";
		
		//String gu ="광진";
		
		JSONObject jsonObject = new JSONObject();
		
		JSONObject js = new JSONObject();
		
		Map<String, String> map = new HashMap<>();
		
		String guName[] = {"강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구", "동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"}; 
		
		for(int i = 0; i < guName.length; i++) {
			if(gu.equals(guName[i])||guName[i].contains(gu)) {
				result = i;
			}
		}
		
		String du;
		
		StringBuilder urlBuilder = new StringBuilder(url1);
		
		URL url;
		
        urlBuilder.append("?" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8")); //도시명
        urlBuilder.append("&" + URLEncoder.encode("searchCondition", "UTF-8") + "=" + URLEncoder.encode("DAILY", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); //한 페이지 결과 수
        
        url = new URL(urlBuilder.toString()+key);
       
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
        
        js = (JSONObject)new XMLSerializer().read(sb.toString());

        System.out.println(js);
        
        JSONObject re = (JSONObject) js.get("header");
        
        JSONObject r = (JSONObject) js.get("body");
        
        System.out.println(re);

        System.out.println(r);
        
        du = "현재 대기상태는"+"\n";
        
        int dust;
        
        if(re.get("resultCode").equals("00")) {
        	du += "발표 일시 : " + r.getJSONArray("items").getJSONObject(result).getString("dataTime")+"\n";
        	du += "측정 장소 : "+r.getJSONArray("items").getJSONObject(result).getString("cityName")+"\n";
        	du += "이산화황 : "+r.getJSONArray("items").getJSONObject(result).getDouble("so2Value")+"ppm\n";
        	du += "일산화탄소 : "+r.getJSONArray("items").getJSONObject(result).getDouble("coValue")+"ppm\n";
        	du += "오존 : "+r.getJSONArray("items").getJSONObject(result).getDouble("o3Value")+"ppm\n";
        	du += "이산화질소 : "+r.getJSONArray("items").getJSONObject(result).getDouble("no2Value")+"ppm\n";
        	du += "미세먼지 : "+r.getJSONArray("items").getJSONObject(result).getInt("pm10Value")+" ㎍/㎥";
        	//du += "발표 일시 : "+r.getJSONArray("items").getJSONObject(0).getInt("pm25Value")+"\n";
        	dust = r.getJSONArray("items").getJSONObject(result).getInt("pm10Value");
        	
        	if(dust <= 30) {
        		du += " -> 좋아요(좋아)\n";
        	}
        	else if(dust <= 80)
        		du +=" -> 보통이에요(뿌듯)\n";
        	else if(dust <= 150)
        		du +=" -> 나빠요(졸려)\n";
        	else if(dust >= 151)
        		du +=" -> 매우나빠요(열받아)\n";
        }
        System.out.println(du);

        map.put("text", du);
        
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
