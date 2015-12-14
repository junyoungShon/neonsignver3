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
	@Override
	public List<MainArticleVO> searchBytitle(String text) {
		System.out.println("제목 DAO:"+text);
		
		return sqlSessionTemplate.selectList("search.searchBytitle",text);
	}

	@Override
	public List<MainArticleVO> searchByContext(String keyword) {
		System.out.println("내용 DAO:"+keyword);
		return sqlSessionTemplate.selectList("search.searchByContext", keyword);
	}

	@Override
	public List<MainArticleVO> searchByNickName(String keyword) {
		System.out.println("닉네임 DAO:"+keyword);
		return sqlSessionTemplate.selectList("search.searchByNickName", keyword);
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
