package org.cobro.neonsign.model;
import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.vo.ItjaMemberVO;
import org.cobro.neonsign.vo.MemberVO;
import org.springframework.stereotype.Service;



@Service
public class ItjaMemberBean {
	@Resource
	private BoardDAO boardDAO;
	/**
	 * 회원의 아이디를 활용해 해당 회원이 잇자를누른 글들이 출력된다.
	 * @author junyoung
	 * @return
	 */
	public List<ItjaMemberVO> getItjaListByMemberEmail(MemberVO memberVO){
		return boardDAO.getItjaListByMemberEmail(memberVO.getMemberEmail());
	}
	/**
	 * 복합 pk들을 활용해 itja 여부를 확인 한다.
	 * 1. 잇자 여부를 체크하고
	 * 2. 이미 잇자가 되어 있다면, 잇자와 멤버관계를 테이블에서 삭제한 뒤 해당 글의 잇자 수를 줄여준다
	 * 3. 잇자가 되어 있지 않다면 , 잇자와 멤버관계를 테이블에 삽입한 뒤 해달 글의 잇자 수를 늘려 준다.
	 * 	
	 * @author junyoung
	 * @return
	 */
	public int checkItja(ItjaMemberVO itjaMemberVO){
		int result = boardDAO.checkItja(itjaMemberVO);
		int isMain = itjaMemberVO.getSubArticleNo();
		System.out.println("제대로 체크하고 있냐"+result);
		if(result==1){
			boardDAO.deleteItja(itjaMemberVO);
			if(isMain==0){
				boardDAO.updateMainMinusItjaHit(itjaMemberVO);
				boardDAO.updateMainMinusTotalItjaHit(itjaMemberVO);
			}else{
				boardDAO.updateMainMinusTotalItjaHit(itjaMemberVO);
				boardDAO.updateSubMinusItjaHit(itjaMemberVO);
			}
		}else{
			//주제글 잇자
			if(isMain==0){
				boardDAO.insertMainItjaMember(itjaMemberVO);
				boardDAO.updateMainPlusItjaHit(itjaMemberVO);
				boardDAO.updateMainPlusTotalItjaHit(itjaMemberVO);
			}else{
				boardDAO.insertSubItjaMember(itjaMemberVO);
				boardDAO.updateSubPlusItjaHit(itjaMemberVO);
				boardDAO.updateMainPlusTotalItjaHit(itjaMemberVO);
			}
		}
		return result;
	}
	/**
	 * 주제글과 잇는글을 파악해서 카운트를 반환한다.
	 * @author junyoung
	 * @param itjaMemberVO
	 * @return
	 */
	public int itjaCount(ItjaMemberVO itjaMemberVO) {
		int isMain = itjaMemberVO.getSubArticleNo();
		int result =0;
		if(isMain==0){
			result = boardDAO.selectMainItjaCount(itjaMemberVO);
		}else{
			result = boardDAO.selectSubItjaCount(itjaMemberVO);
		}
		if(result<0){
			result=itjaCountDefault(itjaMemberVO);
		}
		return result;
	}
	private int itjaCountDefault(ItjaMemberVO itjaMemberVO) {
		boardDAO.itjaCountDefault(itjaMemberVO);
		return 0;
	}
	/**
	 * 주제글의 토탈잇자를 구한다.
	 * @author junyoung
	 * @param itjaMemberVO
	 * @return
	 */
	public int itjaTotalCount(ItjaMemberVO itjaMemberVO) {
		int result = boardDAO.selectItjaTotalCount(itjaMemberVO);
		if(result<0){
			result = itjaCountDefault(itjaMemberVO);
		}
		return result;
	}
	
}
