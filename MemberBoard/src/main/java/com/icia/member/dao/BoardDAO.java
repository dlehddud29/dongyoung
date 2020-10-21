package com.icia.member.dao;


import java.util.*;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.member.dto.BoardDTO;
import com.icia.member.dto.PageDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public List<BoardDTO> boardList() {
		return sql.selectList("Board.boardList");
	}

	public int boardWritefile(BoardDTO board) {
		return sql.insert("Board.boardWritefile", board);
	}

	public BoardDTO boardView(int bnumber) {
		return sql.selectOne("Board.boardView", bnumber);
	}

	public int listCount() {
		return sql.selectOne("Board.boardListCount");
	}

	public List<BoardDTO> boardListPaging(PageDTO paging) {
		return  sql.selectList("Board.boardListPaging", paging);
	}

	public BoardDTO boardUpdate(int bnumber) {
		return sql.selectOne("Board.boardUpdate", bnumber);
	}

	public int boardUpdateProcess(BoardDTO board) {
		return sql.update("Board.boardUpdateProcess", board);
	}

	public List<BoardDTO> boardSearch(String searchtype, String keyword) {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("searchtype", searchtype);
		searchMap.put("keyword", keyword);
		return sql.selectList("Board.boardSearch", searchMap);
	}

	public void hits(int bnumber) {
		sql.update("Board.Hits", bnumber);
		
	}

	public int boardDeleteWriter(int bnumber) {
		sql.delete("Board.boardDeleteWriter", bnumber);
		return 0;
	}

}
