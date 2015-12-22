package org.cobro.neonsign.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.MainArticleVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class SearchDAOImpl implements SearchDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<MainArticleVO> rsearchBykeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MainArticleVO> searchByTag(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hitArticle(String keyword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MainArticleVO> articleSort(String sort) {
		// TODO Auto-generated method stub
		return null;
	}
	//2015-12-19 대협추가
	@Override
	public List<MainArticleVO> searchBytitle(String keyword, int pageNo, String tag) {
		//System.out.println("제목 DAO:"+text);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("pageNo", String.valueOf(pageNo));
		if(!tag.equals("")){
			map.put("tagName", tag);
			return sqlSessionTemplate.selectList("search.searchBytitleAsTag", map);
		}else{
			return sqlSessionTemplate.selectList("search.searchBytitle",map);
		}
	}
	//2015-12-19 대협추가
	@Override
	public List<MainArticleVO> searchByContext(String keyword, int pageNo, String tag) {
		//System.out.println("내용 DAO:"+keyword);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("pageNo", String.valueOf(pageNo));
		if(!tag.equals("")){
			map.put("tagName", tag);
			return sqlSessionTemplate.selectList("search.searchByContextAsTag", map);
		}else{
			return sqlSessionTemplate.selectList("search.searchByContext", map);
		}
	}
	//2015-12-19 대협추가
	@Override
	public List<MainArticleVO> searchByNickName(String keyword, int pageNo, String tag) {
		//System.out.println("닉네임 DAO:"+keyword);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("pageNo", String.valueOf(pageNo));
		if(!tag.equals("")){
			map.put("tagName", tag);
			return sqlSessionTemplate.selectList("search.searchByNickNameAsTag", map);
		}else{
			return sqlSessionTemplate.selectList("search.searchByNickName", map);
		}
	}
	//2015-12-19 대협추가
	@Override
	public List<MainArticleVO> searchByPerson(String keyword, int pageNo, String tag) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("pageNo", String.valueOf(pageNo));
		if(!tag.equals("")){
			map.put("tagName", tag);
			return sqlSessionTemplate.selectList("search.searchByPersonAsTag", map);
		}else{
			return sqlSessionTemplate.selectList("search.searchByPerson",map);
		}
	}

	@Override
	public void insertSearch(String keyword){
		sqlSessionTemplate.insert("search.insertSearch",keyword);
		
	}

	@Override
	public int updateSearch(String keyword){
		return sqlSessionTemplate.update("search.updateSearch",keyword);
	}

	@Override
	public List<HashMap<String, String>> selectReport() {
		return sqlSessionTemplate.selectList("search.selectSearch");
	}


}
