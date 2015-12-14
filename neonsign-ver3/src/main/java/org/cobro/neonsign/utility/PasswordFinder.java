package org.cobro.neonsign.utility;

import java.util.Random;

import javax.annotation.Resource;

import org.cobro.neonsign.model.MemberDAO;
import org.cobro.neonsign.vo.FindPasswordVO;
import org.cobro.neonsign.vo.MemberVO;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class PasswordFinder {
	//왜 리소스가 안먹힐까 ? @Resource
	private MemberDAO memberDAO;
	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public String passFinder(FindPasswordVO findPasswordVO){
		String result = "";
		MemberVO memberVO = memberDAO.findMemberByEmail(findPasswordVO.getMemberEmail());
		String emailConfFile = "emailConfFile.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(emailConfFile);
		
		// @Service("emailSenderTestConfig") <-- same annotation you specified in EmailSenderTestConfig.java
		NeonsignEmailSenderAPI neonsignEmailSenderAPI = (NeonsignEmailSenderAPI) context.getBean("neonsignMailSender");
		neonsignEmailSenderAPI.setNeonsignMailSender((JavaMailSender) context.getBean("mailSender"));
		
		if(memberVO!=null){
			String subject = memberVO.getMemberNickName()+"님이 요청하신 비밀번호 정보입니다.";
			String randomSentence = randomSentenceMaker(20);
			String msgBody = "";
				   msgBody += "<html><body>아래의 링크를 클릭하시면 수정 된 임시 비밀번호를 확인 하실 수 있습니다.<br>";
				   msgBody += "<a href='http://localhost:8888/neonsign-ver2/requestTemporaryPassword.neon?memberEmail="+findPasswordVO.getMemberEmail()+"&randomSentence="+randomSentence+"'>";
				   msgBody += "임시 비밀번호 발급 받기</a></body></html>";
			//요청 이메일과 20자리의 난수를 DB에 기록해둔다.
		findPasswordVO.setRandomSentence(randomSentence);
		//이전에 비밀번호 확인 요청이 있었던 경우 테이블에서 삭제하고 갱신하여 다시 삽입해준다.
		if(memberDAO.confirmPasswordFindRequest(findPasswordVO)==null){
			memberDAO.deletePasswordFindRequest(findPasswordVO);
		}
		memberDAO.insertPasswordFindRequest(findPasswordVO);
		
			//요청 이메일로 메일을 보내준다.
		neonsignEmailSenderAPI.ifyReadyToSendEmail(findPasswordVO.getMemberEmail(), "admin@neonsign.co.kr", subject, msgBody);
			result = "success";
		}else{
			//유효한 메일이 아니므로 fail을 출력
			result = "fail";
		}
		return result;
	}
	
	public String randomSentenceMaker(int sentenceLength){
		StringBuffer randomSentece =new StringBuffer();
		Random random = new Random();
		for(int i=0;i<sentenceLength;i++){
		    if(random.nextBoolean()){
		    	randomSentece.append((char)((int)(random.nextInt(26))+97));
		    }else{
		    	randomSentece.append((random.nextInt(10))); 
		    }
		}
		return randomSentece.toString();
	}
}
