package org.cobro.neonsign.utility;

import java.util.List;

import org.cobro.neonsign.model.BoardDAO;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.SubArticleVO;

public class StoryLinker extends Thread{
	private BoardDAO boardDAO;
	private SubArticleVO subArticleVO;
	public StoryLinker() {
		super();
	}
	public StoryLinker(BoardDAO boardDAO, SubArticleVO subArticleVO) {
		super();
		this.boardDAO = boardDAO;
		this.subArticleVO = subArticleVO;
	}
	public void run() {
		boolean flag = true;
		System.out.println("thread start.");
		while(flag){
			 try {
			        Thread.sleep(60000);
			    }catch(Exception e) {
			    
			}
			System.out.println("스토리링킨 서비스"+subArticleVO);
			int curruntGrade = boardDAO.selectSubArticleCurruntGrade(subArticleVO);
			if(curruntGrade!=0){
			}
			System.out.println("스토리링킨 서비스 현재 스토리 단계"+curruntGrade);
			subArticleVO.setSubAtricleGrade(curruntGrade);
			List<SubArticleVO> list = boardDAO.selectListHigherLikeSubArticle(subArticleVO);
			System.out.println(list.toString());
			System.out.println("스토리링킨 서비스 관련 글 몇개 출력?"+list.size());
			//댓글이 없는 경우 자동 완결 처리 한다.
			if(list.size()==0){
				//메인 아티클의 타이틀을 가져온다.
				MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
				//메인 아티클의 컴플리트 여부를 수정해준다.insertMainArticle
				boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
				//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
				String mainArticleTitle = "[완결]"+mainArticleVO.getMainArticleTitle();
				mainArticleVO.setMainArticleTitle(mainArticleTitle);
				boardDAO.appendToCompleteArticle(mainArticleVO);
				flag=false;
			}else if(list.size()==1){
				if(list.get(0).getIsEnd()==0){
					//최고 잇자 수 득표한 댓글이 계속 잇는 글일 경우 최종 수정일을 고쳐준다.
					boardDAO.updateDateForMainArticle(subArticleVO.getMainArticleNo());
					//우선 연결을 해준다.
					System.out.println("여기로 오지 ?");
					subArticleVO.setSubArticleNo(list.get(0).getSubArticleNo());
					boardDAO.updateIsConnect(subArticleVO);
				}else{
					//최고 잇자 수 득표한 댓글이 그만하자는 글일 경우
					//우선 연결을 해준다.
					subArticleVO.setSubArticleNo(list.get(0).getSubArticleNo());
					boardDAO.updateIsConnect(subArticleVO);
					//메인 아티클의 컴플리트 여부를 수정해준다.
					boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
					//메인 아티클의 타이틀을 가져온다.
					MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
					//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
					String mainArticleTitle = "[완결]"+mainArticleVO.getMainArticleTitle();
					mainArticleVO.setMainArticleTitle(mainArticleTitle);
					boardDAO.appendToCompleteArticle(mainArticleVO);
					flag=false;
				}
			//동점 댓글이 여러개일 경우
			}else{
				int j = 0;
				Long max = 0L;
				//동점 댓글들 중 가장 최근의 댓글들을 찾는다.
				for(int i=0;i<list.size();i++){
					if(max<Long.parseLong(list.get(i).getSubArticleDate())){
						max=Long.parseLong(list.get(i).getSubArticleDate());
						j=i;
					}
				}
				if(list.get(j).getIsEnd()==0){
					//최고 잇자 수 득표한 댓글이 계속 잇는 글일 경우 최종 수정일을 고쳐준다.
					boardDAO.updateDateForMainArticle(subArticleVO.getMainArticleNo());
					//우선 연결을 해준다.
					subArticleVO.setSubArticleNo(list.get(j).getSubArticleNo());
					boardDAO.updateIsConnect(subArticleVO);
				}else{
					//최고 잇자 수 득표한 댓글이 그만하자는 글일 경우
					//우선 연결을 해준다.
					subArticleVO.setSubArticleNo(list.get(j).getSubArticleNo());
					boardDAO.updateIsConnect(subArticleVO);
					//메인 아티클의 타이틀을 가져온다.
					MainArticleVO mainArticleVO = boardDAO.selectMainArticleTitleByMainArticleNo(subArticleVO.getMainArticleNo());
					//메인 아티클의 컴플리트 여부를 수정해준다.insertMainArticle
					boardDAO.updateBestToCompletArticle(subArticleVO.getMainArticleNo());
					//베스트 글이 완결 글로 이동할 때 타이틀에 [완결]표시를 달아준다.
					String mainArticleTitle = "[완결]"+mainArticleVO.getMainArticleTitle();
					mainArticleVO.setMainArticleTitle(mainArticleTitle);
					boardDAO.appendToCompleteArticle(mainArticleVO);
					flag=false;
				}
			}
			System.out.println("평가 1회");
		}
		System.out.println(" thread end.");
	}
}
