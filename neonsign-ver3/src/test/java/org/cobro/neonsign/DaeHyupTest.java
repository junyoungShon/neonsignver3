package org.cobro.neonsign;

import java.util.List;

import javax.annotation.Resource;

import org.cobro.neonsign.model.BoardDAO;
import org.cobro.neonsign.model.BoardService;
import org.cobro.neonsign.model.MemberService;
import org.cobro.neonsign.vo.MainArticleVO;
import org.cobro.neonsign.vo.TagVO;
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
public class DaeHyupTest {
	@Resource
	private MemberService memberService;
	@Resource
	private BoardService boardService;
	@Resource
	private BoardDAO boardDAO;
	

	/*@Test
	public void test(int pageNo){
		// List<MainArticleVO> mainArticleVOList, List<TagVO> tagVOList)
		List<TagVO> tagVOList = null;	
		tagVOList = boardService.selectTagList();
		System.out.println(tagVOList);
		List<MainArticleVO> mainArticleVOList = boardService.selectListNotCompleteMainArticleOrderByDate(pageNo);
		System.out.println(mainArticleVOList);
	}*/
}
