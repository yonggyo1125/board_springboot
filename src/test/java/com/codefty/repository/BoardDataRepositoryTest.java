package com.codefty.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.codefty.entity.BoardData;
import com.codefty.repository.BoardDataRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class BoardDataRepositoryTest {
	
	@Autowired
	BoardDataRepository boardDataRepository;
	
	@Test
	@DisplayName("게시글 저장 테스트")
	public void createBoardDataTest() {
		BoardData boardData = new BoardData();
		boardData.setSubject("게시글 제목");
		boardData.setContents("게시글 본문");
		boardData.setRegDt(LocalDateTime.now());
		boardData.setModDt(LocalDateTime.now());
		
		BoardData savedBoardData = boardDataRepository.save(boardData);
		System.out.println(savedBoardData);
	}
	
	public void createBoardDatas() {
		for(int i = 1; i <= 10; i++) {
			BoardData boardData = new BoardData();
			boardData.setSubject("게시글 제목" + i);
			boardData.setContents("게시글 본문" + i);
			boardData.setRegDt(LocalDateTime.now());
			boardData.setModDt(LocalDateTime.now());
			BoardData savedBoardData = boardDataRepository.save(boardData);
		}
	}
	
	@Test
	@DisplayName("게시글 제목 조회 테스트")
	public void findBySubjectTest() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findBySubject("게시글 제목1");
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	@Test
	@DisplayName("게시글 제목, 게시글 본문 or 테스트")
	public void findBySubjectOrContentsTest() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findBySubjectOrContents("게시글 제목1", "게시글 본문1");
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
}