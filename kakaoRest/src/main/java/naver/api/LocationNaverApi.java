package naver.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;


public class LocationNaverApi {

	public List<Restaurant> getNaverApi(String location,String foodKind) {
		String clientId = "jIoNKFnsROGeEz8etaZA";
		String clientSecret = "UNQ1i8y0N3";

		
		List<Restaurant> list = new ArrayList<Restaurant>();

		try {
			String text = URLEncoder.encode(location+" "+foodKind, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&sort=comment&start=1&display=20";


			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			String jsonResponse = response.toString();

			Gson gson = new Gson();

			LocationJson locationJson = gson.fromJson(jsonResponse, LocationJson.class);
			String regex = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";


			for (Restaurant rest : locationJson.getItems()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setTitle(rest.getTitle().replaceAll(regex, "") );
				restaurant.setLink(rest.getLink());
				restaurant.setDescription(rest.getDescription().replaceAll(regex, ""));
				restaurant.setTelephone(rest.getTelephone());
				restaurant.setRoadAddress(rest.getRoadAddress());
				restaurant.setMapx(rest.getMapx());
				restaurant.setMapy(rest.getMapy());
				
				list.add(restaurant);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}


}
