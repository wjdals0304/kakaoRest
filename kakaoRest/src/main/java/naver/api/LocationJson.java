package naver.api;

import java.util.List;

public class LocationJson {

	private String lastBuildDate;
	private String start;
	private String display;
	private List<Restaurant> items;

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<Restaurant> getItems() {
		return items;
	}

	public void setItems(List<Restaurant> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "LocationJson [lastBuildDate=" + lastBuildDate + ", start=" + start + ", display=" + display + ", items="
				+ items + "]";
	}

}