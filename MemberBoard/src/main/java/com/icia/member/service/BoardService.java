package com.icia.member.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.PageDTO;
import com.icia.member.dao.BoardDAO;
import com.icia.member.dao.CommentDAO;
import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.BoardDTO;
import com.icia.member.dto.CommentDTO;

@Service
public class BoardService {

	private ModelAndView mav;
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	
	private static final int PAGE_LIMIT = 3; //한페이지에 몇개의 목록을 보여줄거냐
	private static final int BLOCK_LIMIT = 5;//페이지갯수를 몇개보여줄것이냐
	
	public ModelAndView boardList(int page) {
		mav = new ModelAndView();
		int listCount = boardDAO.listCount();
		int startRow = (page-1)*PAGE_LIMIT+1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = boardDAO.boardListPaging(paging);
		
		//BLOCK_LIMIT사용
		//ceil = 올림(소수점이있으면 정수로 올림하는것)
		int maxPage = (int)(Math.ceil((double)listCount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT)))-1)*BLOCK_LIMIT+1;
		
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardv/BoardList");
		return mav;
	}


	public ModelAndView boardWritefile(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();//dto에 담긴 파일을 가져와서 이름을 가져와 담음
		bfilename = System.currentTimeMillis() +  "_" + bfilename;
		String savePath = "D:\\source\\spring\\Member201006\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) { //Empty는 비어있냐고 물어보는것 !는 비어있지않으면 저장한다.
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int result = boardDAO.boardWritefile(board);
		if(result > 0)
			mav.setViewName("redirect:/boardlist");
		else
			mav.setViewName("boardv/BoardWriteFileFail");
		return mav;
	}


	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		boardDAO.hits(bnumber);
		BoardDTO boardDTO = boardDAO.boardView(bnumber);
		List <CommentDTO> commentlist  = commentDAO.commentView(bnumber);
		mav.addObject("commentlist", commentlist);
		mav.addObject("boardDTO", boardDTO);
		mav.addObject("page", page);
		mav.setViewName("boardv/BoardView");
		return mav;
	}


	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO boardDTO = boardDAO.boardUpdate(bnumber);
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("boardv/BoardUpdate");
		return mav;
	}


	public ModelAndView boardUpdateProcess(BoardDTO board) throws IllegalStateException, IOException{
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();//dto에 담긴 파일을 가져와서 이름을 가져와 담음
		
		String savePath = "D:\\source\\spring\\Member201006\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int result = boardDAO.boardUpdateProcess(board);
		int bnumber = board.getBnumber();
		//int page = page.getPage();
		if(result > 0) {
			mav.setViewName("redirect:/boardview?bnumber="+bnumber);
		}else {
			System.out.println("수정안됨");
		}
		return mav;
	}

	public ModelAndView boardSearch(String searchtype, String keyword) {
		mav = new ModelAndView();
		List<BoardDTO> searchList = boardDAO.boardSearch(searchtype, keyword);
		mav.addObject("boardList", searchList);
		mav.setViewName("boardv/BoardList");
		return mav;
	}


	public ModelAndView boardDeleteWriter(int bnumber) {
		mav = new ModelAndView();
		int result = boardDAO.boardDeleteWriter(bnumber);
		mav.setViewName("redirect:/boardlist");
		return mav;
	}



}
