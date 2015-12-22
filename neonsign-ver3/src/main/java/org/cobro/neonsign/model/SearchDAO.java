package org.cobro.neonsign.model;

import java.util.HashMap;
import java.util.List;

import org.cobro.neonsign.vo.MainArticleVO;

public interface SearchDAO {

	public List<MainArticleVO> rsearchBykeyword(String keyword);

	public List<MainArticleVO> searchByTag(String keyword);

	public void hitArticle(String keyword);

	public List<MainArticleVO> articleSort(String sort);

	public List<MainArticleVO> searchBytitle(String keyword, int pageNo, String tag);

	public List<MainArticleVO> searchByContext(String keyword, int pageNo, String tag);

	public List<MainArticleVO> searchByNickName(String keyword, int pageNo, String tag);

	public void insertSearch(String keyword);

	public int updateSearch(String keyword) ;

	public List<HashMap<String, String>> selectReport();

	public List<MainArticleVO> searchByPerson(String keyword, int pageNo, String tag);
}
