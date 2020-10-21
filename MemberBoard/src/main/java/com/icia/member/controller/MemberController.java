package com.icia.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.member.api.KakaoJoinApi;
import com.icia.member.api.KakaoLoginApi;
import com.icia.member.api.NaverJoinApi;
import com.icia.member.api.NaverLoginApi;
import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoJoinApi kakaoJoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	private ModelAndView mav;
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/memberloginform")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/memberjoinform")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/joinform")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) {
		System.out.println(member.toString());
		mav = memberService.memberJoin(member);
		
		return mav;
	}
	
	//로그인
	@RequestMapping(value="/loginform")
	public ModelAndView loginform(@ModelAttribute MemberDTO member) {
	//public ModelAndView loginform(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw) {
		mav = memberService.memberlogin(member);
		return mav;	
	}
	
	@RequestMapping(value="memberlist")
	public ModelAndView memberList() {
		mav = memberService.memberList();
		return mav;
	}
	
	@RequestMapping(value="memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		mav = memberService.memberview(mid);
		return mav;
	}
	
	@RequestMapping(value="memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = memberService.memberdelete(mid);
		return mav;
	}
	
	@RequestMapping(value="memberupdate")
	public ModelAndView memberUpdate(@RequestParam("mid") String mid) {
		//String mid2 = (String)session.getAttribute("loginId");
		mav = memberService.memberUpdate(mid);
		return mav;
	}
	
	@RequestMapping(value="memberupdateprocess")
	public ModelAndView memberUpdate(@ModelAttribute MemberDTO member) {
		mav = memberService.memberUpdateProcess(member);
		return mav;
	}
	
	//버튼으로 아이디 중복확인
	//ResponseBody : 텍스트로 리턴을 해주기 위한 어노테이션(리턴을 주소가 아닌 텍스트로줄 수 있는 어노테이션이다.)
	@RequestMapping(value="/idoverlap")
	public @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값 : "+mid);
		String resultMsg = memberService.idOverlap(mid);
		return resultMsg;//주소가 아닌 어떤 변수든 올 수 있음
	}
	//innerHTML로 아이디 중복확인
	@RequestMapping(value="/idonkeyup")
	public @ResponseBody String idOnkeyup(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값 : "+mid);
		String resultMsg = memberService.idOnkeyup(mid);
		return resultMsg;//주소가 아닌 어떤 변수든 올 수 있음
	}
	
	//ajax이용한 상세조회
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("전달 받은 값 : "+ mid);
		MemberDTO memberView = memberService.memberViewAjax(mid);
		return memberView;
	}
	
	//로그아웃
	@RequestMapping(value="/memberlogout")
	public String memberLogout() {
		session.invalidate();
		return "login";
	}
	
	//카카오 서버 로그인
	@RequestMapping(value="/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("KakaoLogin");
		return mav;
	}
	//카카오 서버 인증 통과 후 처리
	@RequestMapping(value="/kakaojoinok")
	public ModelAndView kakaoJoinOK(@RequestParam("code") String code, HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile" + profile);
		//profile에 담긴 id값을 가지고 join.jsp로 이동
		String kakaoId = profile.get("id").asText();//카카오에 등록된 아이디를 꺼내는 작업
		mav = new ModelAndView();
		mav.addObject("kakaoId", kakaoId);
		mav.setViewName("join");
		return mav;
	}
	
	@RequestMapping(value="/kakaologin")
	public ModelAndView kakaoLogin(HttpSession session) {
		String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("KakaoLogin");
		return mav;
	}
	
	@RequestMapping(value="/kakaologinok")
	public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
		JsonNode token = kakaoLoginApi.getAccessToken(code);
		JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
		
		mav = memberService.kakaoLogin(profile);
		
		return mav;
	}
	
	@RequestMapping(value="/naverjoin")
	public ModelAndView naverJoin(HttpSession session) {
		String naverUrl = naverJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("NaverLogin");
		return mav;
	}
	
	//profile을 가져와서 프로필을 naverUser를 json형식으로 까와서 response에 담긴 id값을 받아오는거
	@RequestMapping(value="/naverjoinok")
	public ModelAndView naverJoinOK(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		
		JSONObject naverUser =  (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		
		String naverId = (String) userInfo.get("id");
		mav.addObject("naverId", naverId);
		mav.setViewName("join");
		return mav;
	}
	
	@RequestMapping(value="/naverlogin")
	public ModelAndView naverLogin(HttpSession session) {
		String naverUrl = naverLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("NaverLogin");
		return mav;
	}
	
	@RequestMapping(value="/naverloginok")
	public ModelAndView naverLoginOK(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws IOException, ParseException {
	
		OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		mav = memberService.naverLogin(profile);
		return mav;
	}
	
	@RequestMapping(value = "/boardinfo")
	public ModelAndView memberInfo(@RequestParam("bwriter") String bwriter) {
		mav = memberService.memberInfo(bwriter);
		return mav;
	}

	
	
}
