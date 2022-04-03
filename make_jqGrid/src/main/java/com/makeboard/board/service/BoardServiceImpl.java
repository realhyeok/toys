package com.makeboard.board.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makeboard.board.dao.BoardDao;
import com.makeboard.board.vo.BoardFileVO;
import com.makeboard.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<BoardVO> getAllBoardList() throws SQLException {
		return boardDao.selectAllBoardList();
	}

	@Override
	public void addBoard(BoardVO boardVO) throws SQLException {
		boardDao.insertBoard(boardVO);
	}

	@Override
	public BoardVO getBoardBybdNo(String bdNo) throws SQLException {
		return boardDao.selectBoardBybdNo(bdNo);
	}

	@Override
	public void updateBoardBybdNo(BoardVO boardVO) throws SQLException {
		boardDao.updateBoardBybdNo(boardVO);
	}

	@Override
	public void deleteBoardBybdNo(String bdNo) throws SQLException {
		boardDao.deleteBoardBybdNo(bdNo);
	}

	@Override
	public void insertBoardFileVO(BoardFileVO bfVO) throws SQLException {
		boardDao.insertBoardFileVO(bfVO);
	}

}
