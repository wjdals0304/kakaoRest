package kakao.bookmark.domain;

public class subway_Bookmark_VO {

	private String user_key;
	private String bookmark_station;
	private String bookmark_station_line;
	public String getBookmark_station_line() {
		return bookmark_station_line;
	}
	public void setBookmark_station_line(String bookmark_station_line) {
		this.bookmark_station_line = bookmark_station_line;
	}
	public String getUser_key() {
		return user_key;
	}
	public String getBookmark_station() {
		return bookmark_station;
	}
	public void setBookmark_station(String bookmark_station) {
		this.bookmark_station = bookmark_station;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
}
