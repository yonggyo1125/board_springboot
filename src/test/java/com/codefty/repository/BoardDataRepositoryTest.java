package com.codefty.repository;

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
}