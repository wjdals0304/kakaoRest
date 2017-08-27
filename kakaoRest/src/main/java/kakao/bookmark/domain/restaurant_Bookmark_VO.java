package kakao.bookmark.domain;


public class restaurant_Bookmark_VO {
	
	private String user_key;
	
	///////////////////////////

	private String foodkind; 
	private String locationName;
	private int restIndex;
	private String title;
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUser_key() {
		return user_key;
	}


	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}


	public String getFoodkind() {
		return foodkind;
	}


	public void setFoodkind(String foodkind) {
		this.foodkind = foodkind;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getRestIndex() {
		return restIndex;
	}


	public void setRestIndex(int restIndex) {
		this.restIndex = restIndex;
	}



}
