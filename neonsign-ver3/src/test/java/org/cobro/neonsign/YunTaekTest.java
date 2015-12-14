package org.cobro.neonsign;

import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.model.BoardDAO;
import org.cobro.neonsign.model.BoardService;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.model.ReportDAO;
import org.cobro.neonsign.model.UtilService;
import org.cobro.neonsign.vo.SubArticleVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
*    TDD : 테스트 주도 개발(test-driven development, TDD)은 
*            매우 짧은 개발 사이클을 반복하는 소프트웨어 개발 프로세스
*            
*    JUnit: 자바 단위 테스트를 위한 TDD 프레임워크
*    
*    아래 라이브러리가 maven의 pom.xml에 추가되어야 한다. 
      <!-- spring junit  -->
 <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-test</artifactId>
           <version>${org.springframework-version}</version>            
       </dependency>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class YunTaekTest {
	@Resource
	private MemberService memberService;
	@Resource
	private BoardService boardService;
	@Resource
	private BoardDAO boardDAO;
	@Resource
	private UtilService utilService;
	@Resource
	private ReportDAO reportDAO;

	@Test
	public void test(){
		SubArticleVO subArticleVO=new SubArticleVO();
		subArticleVO.setMainArticleNo(21); subArticleVO.setSubAtricleGrade(0);
		List<SubArticleVO> list=boardDAO.selectListSubArticle(subArticleVO);
	/*	MainArticleVO mvo=new MainArticleVO();
		mvo.setMainArticleNo(3);
		int number=reportDAO.mainArticleReport(mvo);
		System.out.println(number);*/
	}
}
