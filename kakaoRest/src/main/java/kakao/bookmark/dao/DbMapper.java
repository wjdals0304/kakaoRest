package kakao.bookmark.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import kakao.bookmark.domain.*;;


@Repository
public interface DbMapper {

	public void insert_Subwaybookmark(subway_Bookmark_VO vo);
	public List<subway_Bookmark_VO> callsubwaybookmarkBykey(String user_key);
	public void insert_Restaurantbookmark(restaurant_Bookmark_VO vo);
	public List<restaurant_Bookmark_VO> callrestaurantbookmarkBykey(String user_key);
	public void insert_Busbookmark(bus_Bookmark_VO vo);
	public List<bus_Bookmark_VO> callbusbookmarkBykey(String user_key);
	public void delete_Subwaybookmark(HashMap<String,String> map);
	public void delete_Busbookmark(HashMap<String,String> map);
	public void delete_Restaurantbookmark(HashMap<String,String> map);
	public restaurant_Bookmark_VO userRestaurantbookmark(HashMap<String,String> map);


}
