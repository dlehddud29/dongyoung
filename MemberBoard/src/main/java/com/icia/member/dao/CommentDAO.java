package com.icia.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.member.dto.CommentDTO;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public int commentWrite(CommentDTO comment) {
		return sql.insert("Comment.commentWrite", comment);
	}
	
	public List<CommentDTO> commentList(int cbnumber) {
		return sql.selectList("Comment.commentList", cbnumber);
	}

	public List<CommentDTO> commentView(int bnumber) {
		return sql.selectList("Comment.commentView", bnumber);
	}

	public int commentDelete(int cnumber) {
		return sql.delete("Comment.commentDelete", cnumber);
	}

	public List<CommentDTO> commentList2(int cbnumber) {
		return sql.selectList("Comment.commentList2", cbnumber);
	}



}
