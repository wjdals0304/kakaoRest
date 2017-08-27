package kakao.bookmark.service;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kakao.bookmark.dao.DbMapper;
import kakao.bookmark.domain.bus_Bookmark_VO;
import kakao.bookmark.domain.restaurant_Bookmark_VO;
import kakao.bookmark.domain.subway_Bookmark_VO;

@Service
public class DbService{

	
	
	@Autowired
	DbMapper dbMapper;
	
	public void insert_Restaurantbookmark(restaurant_Bookmark_VO vo){
		dbMapper.insert_Restaurantbookmark(vo);
	}
	public void insert_Subwaybookmark(subway_Bookmark_VO vo){
		dbMapper.insert_Subwaybookmark(vo);
	}
	public List<subway_Bookmark_VO> callsubwaybookmarkBykey(String user_key){
		return dbMapper.callsubwaybookmarkBykey(user_key);
	}
	public List<restaurant_Bookmark_VO> callrestaurantbookmarkBykey(String user_key){
		
		return dbMapper.callrestaurantbookmarkBykey(user_key);
	}
	public void insert_Busbookmark(bus_Bookmark_VO vo){
		dbMapper.insert_Busbookmark(vo);
	}
	public List<bus_Bookmark_VO> callbusbookmarkBykey(String user_key){
		return dbMapper.callbusbookmarkBykey(user_key);
	}
	public void delete_Subwaybookmark(HashMap<String,String> map){
		dbMapper.delete_Subwaybookmark(map);
		
	}
	public void delete_Busbookmark(HashMap<String,String> map){
		dbMapper.delete_Busbookmark(map);
	}
	public void delete_Restaurantbookmark(HashMap<String,String> map){
		dbMapper.delete_Restaurantbookmark(map);
	}

	public restaurant_Bookmark_VO userRestaurantbookmark(HashMap<String,String> map){
		return dbMapper.userRestaurantbookmark(map);
	}


//	@Autowired
//	private SqlSessionTemplate sqlSession;
//	public void insert_Subwaybookmark(subway_Bookmark_VO bVo) {
//		// TODO Auto-generated method stub
//			
//			
//			sqlSession.insert("bookmark.insert_Subwaybookmark",bVo);
//	}
//	
//	public void insert_RestaurantBookmark(restaurant_Bookmark_VO bVo) {
//		// TODO Auto-generated method stub
//			sqlSession.insert("bookmark.insertBookmark",bVo);
//	}
//	
//	public List<subway_Bookmark_VO> callbookmarkBykey(String user_key) {
//		// TODO Auto-generated method stub
//		
//		return sqlSession.selectList("bookmark.callbookmarkBykey",user_key);
//	}
//	
//	
	
}
