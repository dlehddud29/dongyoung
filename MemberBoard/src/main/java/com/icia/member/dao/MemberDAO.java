package com.icia.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.member.dto.BoardDTO;
import com.icia.member.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public int memberJoin(MemberDTO member) {
		if(member.getKakaoId() != null)
			return sql.insert("Member.kakaoMemberJoin", member);
		else if(member.getNaverId() != null)
			return sql.insert("Member.naverMemberJoin", member);
		else
			return sql.insert("Member.memberJoin", member);
	}

	public String memberlogin(MemberDTO member) {
	
		return sql.selectOne("Member.memberlogin", member);
	}

	public List<MemberDTO> memberList() {
		return sql.selectList("Member.memberList");
	}

	public MemberDTO memberview(String mid) {
		return sql.selectOne("Member.memberview", mid);
	}

	public int memberdelete(String mid) {
		return sql.delete("Member.memberdelete", mid);
	}

	public MemberDTO memberUpdate(String mid) {
		return sql.selectOne("Member.memberUpdate", mid);
	}

	public int memberUpdateProcess(MemberDTO member) {
		return sql.update("Member.memberUpdateProcess", member);
	}

	public String idOverlap(String mid) {
		return sql.selectOne("Member.idOverlap", mid);
	}

	public String idOnkeyup(String mid) {
		return sql.selectOne("Member.idOverlap", mid);
	}

	public String kakaoLogin(String kakaoId) {
		return sql.selectOne("Member.kakaoLogin", kakaoId);
	}

	public String naverLogin(String naverId) {
		return sql.selectOne("Member.naverLogin", naverId);
	}

	public List<MemberDTO> memberInfo(String bwriter) {
		return sql.selectList("Member.memberInfo", bwriter);
	}



	

}
