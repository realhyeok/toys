package com.makeboard.board.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.makeboard.board.service.BoardService;
import com.makeboard.board.vo.BoardFileVO;
import com.makeboard.board.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@ResponseBody
	@RequestMapping(value= "/getBoardDatas", method = RequestMethod.POST)
	public List<BoardVO> getBoardDatas() throws SQLException{
		List<BoardVO> boardList = new ArrayList<>();
//		Map<String, List<BoardVO>> retMap = new HashMap<>();
//		JSONObject jo = new JSONObject();
//		JSONArray ja = new JSONArray();
		
		boardList = boardService.getAllBoardList();
		
//		for (BoardVO b : boardList) {
//			System.out.println(b.getBdRegdate());
//		}
		return boardList;
	}
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(){
		String url = "board";
		
		return url;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public Map<String, String> boardInsert(BoardVO boardVO, MultipartFile file) throws SQLException, IllegalStateException, IOException{
		Map<String, String> retMap = new HashMap<>();
		
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + file.getOriginalFilename();
		String fileType =FilenameUtils.getExtension(file.getOriginalFilename()); 
		File saveFile = new File(filePath, fileName);
		
		file.transferTo(saveFile);
		
//		save the datas to boardFileVO
		BoardFileVO bfVO = new BoardFileVO();
		bfVO.setFileNm(fileName);
		bfVO.setUploadPath(filePath + "\\" + fileName);
		bfVO.setUuid(uuid.toString());
		bfVO.setWriter(boardVO.getBdWriter());
		bfVO.setBdNo(boardVO.getBdNo());
		bfVO.setFileType(fileType);
		
		boardService.addBoard(boardVO);
		
		retMap.put("success", "success");
		
		return retMap;
	}
	
	@RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
	public String boardInsertPage(){
		String url = "boardInsert";
		
		return url;
	}
	
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(ModelAndView mv, HttpServletRequest req) throws SQLException{
		BoardVO boardVO = new BoardVO();
		
		String bdNo = req.getParameter("bdNo");
		
		boardVO = boardService.getBoardBybdNo(bdNo);
		
		mv.addObject("boardVO", boardVO);
		mv.setViewName("/boardDetail");
		
		return mv;
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetailPage(){
		String url = "boardDetail";
		return url;
	}
	
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdatePage(ModelAndView mv, HttpServletRequest req) throws SQLException{
		BoardVO boardVO = new BoardVO();
		
		String bdNo = req.getParameter("bdNo");
		boardVO = boardService.getBoardBybdNo(bdNo);
	
		mv.addObject("boardVO", boardVO);
		mv.setViewName("boardUpdate"); 	
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public Map<String, String> boardUpdate(BoardVO boardVO) throws SQLException{
		
		Map<String, String> retMap = new HashMap<>();
		boardService.updateBoardBybdNo(boardVO);
		
		retMap.put("success", "success");
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public Map<String, String> boardDelete(@RequestParam("bdNo") String bdNo) throws SQLException{
		
		Map<String, String> retMap = new HashMap<>();
		boardService.deleteBoardBybdNo(bdNo);
		
		retMap.put("success", "success");
		return retMap;
	}
	
}
