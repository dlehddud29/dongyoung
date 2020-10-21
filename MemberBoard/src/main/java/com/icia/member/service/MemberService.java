package com.icia.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private HttpSession session;
	
	//ModelAndView 클래스 : 데이터와 화면을 모두 저장하는 클래스
	private ModelAndView mav;
	
	public ModelAndView memberJoin(MemberDTO member) {
		int joinResult = memberDAO.memberJoin(member);
		mav = new ModelAndView();
		
		if(joinResult > 0) {
			//회원가입 성공시 login.jsp출력
			mav.setViewName("login");
		}else {
			//회원가입 실패시 loginfail.jsp출력
			mav.setViewName("loginfail");
		}
		
		return mav;
	}

	public ModelAndView memberlogin(MemberDTO member) {
		mav = new ModelAndView();
		
		String loginId = memberDAO.memberlogin(member);
		if(loginId != null) {
			//세션값 지정해줄때는 httpsession을 import해줘야함
			session.setAttribute("loginId", loginId);
			mav.setViewName("main");
		}else {
			mav.setViewName("loginFail");
		}
		
		return mav;
	}

	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberDTO> memberList = memberDAO.memberList();
		//mav가 리스트로 가져가야함
		mav.addObject("memberList", memberList);
		mav.setViewName("MemberList");
		return mav;
	}


	public ModelAndView memberview(String mid) {
		mav = new ModelAndView();
		MemberDTO memberDTO = memberDAO.memberview(mid);
		mav.addObject("List", memberDTO);
		mav.setViewName("MemberView");
		return mav;
	}

	public ModelAndView memberdelete(String mid) {
		mav = new ModelAndView();
		int result = memberDAO.memberdelete(mid);
		if (result > 0) {
			mav.setViewName("redirect:/memberlist");
		}else {
			mav.setViewName("MemberDeleteFail");	
		}
		return mav;
	}

	public ModelAndView memberUpdate(String mid) {
		mav = new ModelAndView();
		MemberDTO memberDTO = memberDAO.memberUpdate(mid);
		mav.addObject("List", memberDTO);
		mav.setViewName("MemberUpdate");
		return mav;
	}

	public ModelAndView memberUpdateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int result = memberDAO.memberUpdateProcess(member);
		if(result > 0) {
			mav.setViewName("main");
		}else {
			mav.setViewName("MemberUpdateFail");
		}
		return mav;
	}

	public String idOverlap(String mid) {
		mav = new ModelAndView();
		String checkResult = memberDAO.idOverlap(mid);
		String resultMsg = null;
		//null값이 되어야 아이디가 없다는  거니깐 == null을 사용
		if(checkResult == null) {
			resultMsg = "OK";
		}else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

	public String idOnkeyup(String mid) {
		mav = new ModelAndView();
		String checkResult = memberDAO.idOnkeyup(mid);
		String resultMsg = null;
		//null값이 되어야 아이디가 없다는  거니깐 == null을 사용
		if(checkResult == null) {
			resultMsg = "OK";
		}else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

	public MemberDTO memberViewAjax(String mid) {
		MemberDTO memberView = memberDAO.memberview(mid);
		return memberView;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = memberDAO.kakaoLogin(kakaoId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("main");
		
		return mav;
	}

	public ModelAndView naverLogin(String profile) throws ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = memberDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("main");
		return mav;
	}

	public ModelAndView memberInfo(String bwriter) {
		mav = new ModelAndView();
		List<MemberDTO> list = memberDAO.memberInfo(bwriter); 
		mav.addObject("list", list);
		mav.setViewName("boardv/BoardInfo");
		return mav;
	}

}
