package kakao.domain;

public class Friend {
	private String user_key;

	
	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	@Override
	public String toString() {
		return "Friend [user_key=" + user_key + "]";
	}
	
	
}
