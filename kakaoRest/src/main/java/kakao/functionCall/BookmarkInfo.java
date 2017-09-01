package kakao.functionCall;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kakao.bookmark.service.DbService;
import kakao.controller.BookmarkController;
import kakao.domain.Message;

@Component
public class BookmarkInfo implements functionCallConfig {

	@Autowired
	BookmarkController bookmarkInfo;
	int flow = 0;
	String getMessage;
	
	@Override
	public String getMessage(Message message) {
		// TODO Auto-generated method stub
		getMessage = message.getContent();
		if(flow ==0&&"즐겨찾기관리".equals(getMessage))
		{

			flow = 1;
			return bookmarkInfo.f_Bookmark();
			
		}
		else if(flow == 1 && "즐겨찾기 보기".equals(getMessage))
		{
			flow=2;
			return bookmarkInfo.kindofBookmark();
			
		}
		else if(flow == 1 && "즐겨찾기 삭제".equals(getMessage))
		{
			flow=3;
			return bookmarkInfo.kindofBookmark();
		}
		else if(flow == 2){//보기
			
			if("My지하철".equals(getMessage)){
				flow=7;
				return bookmarkInfo.show_Subway_Location(message.getUser_key());
			}
			else if("My버스".equals(getMessage)){
				flow=8;
				return bookmarkInfo.show_Bus_Location(message.getUser_key());
			}
		
			else if("My맛집".equals(getMessage)){
				flow=9;
				return bookmarkInfo.show_Restaurant_Location(message.getUser_key());
			}
			
		}
		else if(flow==7){
			flow=0;
			return bookmarkInfo.show_Subway_Bookmark(getMessage,message.getUser_key());
			
		}
		else if(flow==8){
			flow=0;
			return bookmarkInfo.show_Bus_Bookmark(getMessage, message.getUser_key());
		}
		else if(flow==9){
			flow=0;
			return bookmarkInfo.show_Restaurant_Bookmark(getMessage, message.getUser_key());
			
		}
		else if(flow == 3){//삭제
			
			if("My지하철".equals(getMessage)){
				flow=4;
				return bookmarkInfo.select_SubwayInfo(message.getUser_key());
			}
			else if("My버스".equals(getMessage)){
				flow=5;
				return bookmarkInfo.select_BusInfo(message.getUser_key());
			}
		
			else if("My맛집".equals(getMessage)){
				flow=6;
				return bookmarkInfo.select_RestaurantInfo(message.getUser_key());
			}
			
		}
		else if(flow == 4){
			flow=0;
			return bookmarkInfo.delete_Subway_Bookmark(getMessage, message.getUser_key());
		}
		
		else if(flow == 5){
			flow=0;
			return bookmarkInfo.delete_Bus_Bookmark(getMessage, message.getUser_key());
		}
		
		else if(flow == 6){
			flow=0;
			return bookmarkInfo.delete_Restaurant_Bookmark(getMessage, message.getUser_key());
		}//삭제 끝
		else{
			flow=0;
			return bookmarkInfo.back_to_start();
		}
		

		return null;
	}

	public void init() {
		flow = 0;
	}
}
