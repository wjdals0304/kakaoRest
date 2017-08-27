package kakao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.bookmark.domain.bus_Bookmark_VO;
import kakao.bookmark.domain.restaurant_Bookmark_VO;
import kakao.bookmark.domain.subway_Bookmark_VO;
import kakao.bookmark.service.DbService;
import naver.api.LocationNaverApi;
import naver.api.Restaurant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class BookmarkController {
	@Autowired 
	DbService db;
	
	@Autowired
	TrafficInfoController subway_F;
	@Autowired
	BusInfoController bus_F;
	
	private LocationNaverApi naverApi = new LocationNaverApi();
	private HashMap<String, String> globalmap = new HashMap<>();


	public String f_Bookmark() {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		map.put("text", "어떤 거 할래?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("즐겨찾기 보기");
		list.add("즐겨찾기 삭제");
	

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		String json = jsonObject.toString();

		return json;
	}
	public String kindofBookmark() {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		map.put("text", "어떤 정보를 원해?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("My지하철");
		list.add("My버스");
		list.add("My맛집");
	

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		String json = jsonObject.toString();

		return json;
	}
	
	
	
	
	public String show_Subway_Bookmark(String message_Content,String user_key) {
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String bookmark_station,bookmark_station_line;
		StringTokenizer st = new StringTokenizer(message_Content,",");

		bookmark_station=st.nextToken();
		bookmark_station_line=st.nextToken();
		
		List<subway_Bookmark_VO> subway = null;
		subway = db.callsubwaybookmarkBykey(user_key);
		
		String response = "";
			
		response +=subway_F.print_subway_name(bookmark_station, bookmark_station_line).toString();
		
		map.put("text", response);

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	//앞
	public String show_Restaurant_Bookmark(String message_Content,String user_key) {
		
		
		JSONObject jsonObject = new JSONObject();

		JSONObject jsonObjectKeyboard = new JSONObject();

		Map<String, Object> message = new LinkedHashMap<>();
		Map<String, String> url = new HashMap<>();
		HashMap<String, String> info = new HashMap<>();
		String locationName,title,foodkind,restIndex;
		StringTokenizer st = new StringTokenizer(message_Content,",");
		String response = "";
		int restindex;
		
		title=st.nextToken();
		locationName=st.nextToken();
		
		info.put("title", title);
		info.put("locationName", locationName);
		info.put("user_key", user_key);
		
		restaurant_Bookmark_VO rest = null;
		rest = db.userRestaurantbookmark(info);
		List<Restaurant> myRestaurant;
		String textMessage = "";
		
		restindex=rest.getRestIndex();
		foodkind=rest.getFoodkind();
			
		myRestaurant= naverApi.getNaverApi(locationName, foodkind);//DB에서 리스트 받아옴.

			if ("".equals(myRestaurant.get(restindex).getDescription())) {//rest.get(i).getRestIndex도 DB에서 가져옴

				textMessage +="(별)"+myRestaurant.get(restindex).getTitle() + "(별)\n\n" + "주소 : "
						+ myRestaurant.get(restindex).getRoadAddress() + "\n\n" + "전화번호 : "
						+ myRestaurant.get(restindex).getTelephone()+"\n\n\n" ;

			} else if ("".equals(myRestaurant.get(restindex).getTelephone())) {

				textMessage +="(별)"+ myRestaurant.get(restindex).getTitle() + "(별)\n\n" + "설명 :"
						+ myRestaurant.get(restindex).getDescription() + "\n\n" + "주소 : "
						+ myRestaurant.get(restindex).getRoadAddress() + "\n\n\n";

			} else if ("".equals(myRestaurant.get(restindex).getDescription())
					&& "".equals(myRestaurant.get(restindex).getTelephone())) {

				textMessage += "(별)"+myRestaurant.get(restindex).getTitle() + "(별)\n\n" + "주소 : "
						+ myRestaurant.get(restindex).getRoadAddress() + "\n\n\n";

			} else {
				textMessage +="(별)"+myRestaurant.get(restindex).getTitle() + "(별)\n\n" + "설명 :"
						+ myRestaurant.get(restindex).getDescription() + "\n\n" + "주소 : "
						+ myRestaurant.get(restindex).getRoadAddress() + "\n\n" + "전화번호 : "
						+ myRestaurant.get(restindex).getTelephone() + "\n\n\n";
			}
			url.put("label", "자세히 보기");
			url.put("url", "https://search.naver.com/search.naver?query=" + myRestaurant.get(restindex).getTitle());
			message.put("message_button", url);

		
		message.put("text", textMessage);

		jsonObject.put("message", message);

		jsonObjectKeyboard.put("type", "buttons");
		
		
		List<String> list = new ArrayList<>();
		list.add("처음으로");
		JSONArray array = JSONArray.fromObject(list);

		jsonObjectKeyboard.put("buttons", array);

		jsonObject.put("keyboard", jsonObjectKeyboard);

		String json = jsonObject.toString();

		return json;
		
	}
	
	
	public String show_Bus_Bookmark(String message_Content,String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String bus_num,station_num;
		StringTokenizer st = new StringTokenizer(message_Content,"번,");
		String response = "";

		List<bus_Bookmark_VO> bus = null;
		bus = db.callbusbookmarkBykey(user_key);
		
		bus_num=st.nextToken();
		station_num=st.nextToken();
	
			
		response +=bus_F.print_bus_info(bus_num, station_num).toString();
		map.put("text", response);

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	
	
	////////////////////////////////////////////////////////////////////뒤
	
	public String show_Restaurant_Location(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();

		List<restaurant_Bookmark_VO> rest = null;
		rest = db.callrestaurantbookmarkBykey(user_key);
		
		if(rest.size()==0)
		{
			map.put("text", "즐겨찾기 등록 부터해줘~(좋아)");
			jsonObject.put("message", map);

			jsonObjectSub.put("type", "buttons");

			List<String> list = new ArrayList<>();
			list.add("처음으로");

			JSONArray array = JSONArray.fromObject(list);

			jsonObjectSub.put("buttons", array);
			jsonObject.put("keyboard", jsonObjectSub);

			
			String json = jsonObject.toString();

			return json;
		}
		
		List<String> list = new ArrayList<>();

		for(int i=0;i<rest.size();i++){
			
			list.add(rest.get(i).getTitle()+","+rest.get(i).getLocationName());
			
		}
		
		map.put("text", "어느 맛집을 보고싶어?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String show_Bus_Location(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();

		List<bus_Bookmark_VO> bus = null;
		bus = db.callbusbookmarkBykey(user_key);
		
		if(bus.size()==0)
		{
			map.put("text", "즐겨찾기 등록 부터해줘~(좋아)");
			jsonObject.put("message", map);

			jsonObjectSub.put("type", "buttons");

			List<String> list = new ArrayList<>();
			list.add("처음으로");

			JSONArray array = JSONArray.fromObject(list);

			jsonObjectSub.put("buttons", array);
			jsonObject.put("keyboard", jsonObjectSub);

			
			String json = jsonObject.toString();

			return json;
		}
		
		List<String> list = new ArrayList<>();

		
		for(int i=0;i<bus.size();i++){
			
			list.add(bus.get(i).getBus_num()+"번,"+bus.get(i).getStation_num());
			
		}
		map.put("text", "어느 버스,정류장을 보고싶어?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String show_Subway_Location(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();

		List<subway_Bookmark_VO> subway = null;
		subway = db.callsubwaybookmarkBykey(user_key);
		
		if(subway.size()==0)
		{
			map.put("text", "즐겨찾기 등록 부터해줘~(좋아)");
			jsonObject.put("message", map);

			jsonObjectSub.put("type", "buttons");

			List<String> list = new ArrayList<>();
			list.add("처음으로");

			JSONArray array = JSONArray.fromObject(list);

			jsonObjectSub.put("buttons", array);
			jsonObject.put("keyboard", jsonObjectSub);

			
			String json = jsonObject.toString();

			return json;
		}
		
		List<String> list = new ArrayList<>();

		
		for(int i=0;i<subway.size();i++){
			
			list.add(subway.get(i).getBookmark_station()+","+subway.get(i).getBookmark_station_line());
			
		}
		map.put("text", "어느역을 선택 할래?");

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	//////////////////////////////////////////////////////////////////// 삭제 부분 시작
	public String delete_Bus_Bookmark(String message_Content,String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		HashMap<String, String> info_map = new HashMap<>();
		String response = "즐겨찾기에 삭제됬어~(좋아)";
		String bus_num,station_num;
		StringTokenizer st = new StringTokenizer(message_Content,"번,");
		
		
		bus_num=st.nextToken();
		station_num=st.nextToken();
		
		info_map.put("bus_num",bus_num);
		info_map.put("station_num",station_num);
		info_map.put("user_key", user_key);		
		
		
		
		db.delete_Busbookmark(info_map);
		
		
		map.put("text", response);

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String delete_Subway_Bookmark(String message_Content,String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		HashMap<String, String> info_map = new HashMap<>();
		String response = "즐겨찾기에 삭제됬어~(좋아)";
		String bookmark_station,bookmark_station_line;
		StringTokenizer st = new StringTokenizer(message_Content,",");
		
		
		bookmark_station=st.nextToken();
		bookmark_station_line=st.nextToken();
		
		info_map.put("bookmark_station",bookmark_station);
		info_map.put("bookmark_station_line",bookmark_station_line);
		info_map.put("user_key", user_key);
		
		db.delete_Subwaybookmark(info_map);
		
		
		map.put("text", response);

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String delete_Restaurant_Bookmark(String message_Content,String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		HashMap<String,String> info_map =new HashMap();
		String response = "즐겨찾기에 삭제됬어~(좋아)";
		String title,locationName;
		StringTokenizer st = new StringTokenizer(message_Content,",");
		
		
		title=st.nextToken();
		locationName=st.nextToken();
	
		info_map.put("title", title);
		info_map.put("locationName", locationName);
		info_map.put("user_key", user_key);
		
		db.delete_Restaurantbookmark(info_map);
		
		
		map.put("text", response);

		jsonObject.put("message", map);

		jsonObjectSub.put("type", "buttons");

		List<String> list = new ArrayList<>();
		list.add("처음으로");

		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String select_SubwayInfo(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String response = "어떤 역을 삭제할래?";
		
		List<subway_Bookmark_VO> list_db = null;
		list_db = db.callsubwaybookmarkBykey(user_key);
		

		List<String> list = new ArrayList<>();
		
		for(int i=0;i<list_db.size();i++){
		
			list.add(list_db.get(i).getBookmark_station()+","+list_db.get(i).getBookmark_station_line());
		
		}
		map.put("text", response);
		jsonObject.put("message", map);
		jsonObjectSub.put("type", "buttons");


		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String select_BusInfo(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String response = "어떤 정류장을 삭제할래?";
		
		List<bus_Bookmark_VO> list_db = null;
		list_db = db.callbusbookmarkBykey(user_key);
		

		List<String> list = new ArrayList<>();
		
		for(int i=0;i<list_db.size();i++){
		
			list.add(list_db.get(i).getBus_num()+"번,"+list_db.get(i).getStation_num());
		
		}
		map.put("text", response);
		jsonObject.put("message", map);
		jsonObjectSub.put("type", "buttons");


		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}
	
	public String select_RestaurantInfo(String user_key) {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObjectSub = new JSONObject();
		Map<String, String> map = new HashMap<>();
		String response = "어떤 맛집을 삭제할래?";
		
		List<restaurant_Bookmark_VO> list_db = null;
		List<Restaurant> myRestaurant = null;
		list_db = db.callrestaurantbookmarkBykey(user_key);
		

		List<String> list = new ArrayList<>();
		
		for(int i=0;i<list_db.size();i++){
		
			myRestaurant = naverApi.getNaverApi(list_db.get(i).getLocationName(), list_db.get(i).getFoodkind());
			list.add(myRestaurant.get(list_db.get(i).getRestIndex()).getTitle()+","+list_db.get(i).getLocationName());
			
		}
		map.put("text", response);
		jsonObject.put("message", map);
		jsonObjectSub.put("type", "buttons");


		JSONArray array = JSONArray.fromObject(list);

		jsonObjectSub.put("buttons", array);
		jsonObject.put("keyboard", jsonObjectSub);

		
		String json = jsonObject.toString();

		return json;
	}

}
