package com.icia.member.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.CommentDTO;
import com.icia.member.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment){
		List<CommentDTO> commentList = commentService.commentWrite(comment);
		return commentList;
	}
	
	@RequestMapping(value = "/commentdelete")
	public @ResponseBody int commentDelete(@RequestParam("cnumber") int cnumber ) {
		int result = commentService.commentDelete(cnumber);
		return result;
	}
	
	@RequestMapping(value = "/commentlist")
	public @ResponseBody List<CommentDTO> commentList(@RequestParam("cbnumber") int cbnumber) {
		List<CommentDTO> list = commentService.commentList(cbnumber);
		return list;
	}

}
