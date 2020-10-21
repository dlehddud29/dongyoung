package com.icia.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.BoardDTO;
import com.icia.member.dto.MemberDTO;
import com.icia.member.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	
	
	@RequestMapping(value="/boardlist")
	public ModelAndView boardList(@RequestParam(value="page", required=false,defaultValue="1")int page) {
		mav = boardService.boardList(page);
		return mav;
	}
	
	@RequestMapping(value="/boardwrite")
	public String boardWrite() {
		return "boardv/BoardWrite";
	}
	
	@RequestMapping(value = "/boardwritefile")
	public ModelAndView boardWritefile(BoardDTO board) throws IllegalStateException, IOException {
		mav = boardService.boardWritefile(board);
		return mav;
	}
	
	@RequestMapping(value = "/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber, @RequestParam(value="page", required=false,defaultValue="1")int page) {
		mav = boardService.boardView(bnumber, page);
		return mav;
	}
	
	@RequestMapping(value = "/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber){
		mav = boardService.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value = "/boardupdateprocess")
	public ModelAndView boardUpdateProcess(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = boardService.boardUpdateProcess(board);
		return mav;
	}
	
	@RequestMapping(value = "/boardsearch")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword) {
		mav = boardService.boardSearch(searchtype, keyword);
		return mav;
	}
	
	@RequestMapping(value = "/boarddeletewriter")
	public ModelAndView boardDeleteWriter(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardDeleteWriter(bnumber);
		return mav;
	}
	
	
	
}
