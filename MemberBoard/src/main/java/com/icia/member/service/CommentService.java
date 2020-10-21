package com.icia.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.CommentDAO;
import com.icia.member.dto.CommentDTO;

@Service
public class CommentService {

	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommentDAO commentDAO;
	
	public List<CommentDTO> commentWrite(CommentDTO comment) {
		String mid = (String)session.getAttribute("loginId");
		comment.setCwriter(mid);
		int writeResult = commentDAO.commentWrite(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		if(writeResult > 0) {
			commentList = commentDAO.commentList(comment.getCbnumber());
		}else {
			commentList = null;
		}
		return commentList;
	}

	public int commentDelete(int cnumber) {
		int result = commentDAO.commentDelete(cnumber);
		return result;
	}

	public List<CommentDTO> commentList(int cbnumber) {
		List<CommentDTO> list = commentDAO.commentList2(cbnumber);
		return list;
	}


}
