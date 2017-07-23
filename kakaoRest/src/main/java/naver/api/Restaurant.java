package naver.api;

public class Restaurant {
	

	private String title;
	private String link;
	private String description;
	private String telephone;
	private String roadAddress;
	private String mapx;
	private String mapy;
	private String kind; 
	private String locationName;
	
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	@Override
	public String toString() {
		return "Restaurant [title=" + title + ", link=" + link + ", description=" + description + ", telephone="
				+ telephone + ", roadAddress=" + roadAddress + ", mapx=" + mapx + ", mapy=" + mapy + "]";
	}

	
	
}
