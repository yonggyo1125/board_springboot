package com.codefty.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.codefty.entity.BoardData;
import com.codefty.entity.FileInfo;
import com.codefty.entity.Member;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
class BoardDataTest2 {
	
	@Autowired
	BoardDataRepository boardDataRepository;
	
	@Autowired
	FileInfoRepository fileInfoRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@PersistenceContext
	EntityManager em;
		
	@Test
	@DisplayName("영속성 전이 테스트")
	public void cascadeTest() {						
		BoardData boardData = new BoardData();
		boardData.setSubject("게시글 제목");
		boardData.setContents("게시글 본문");
		boardData.setViewCount(100);
		boardData.setRegDt(LocalDateTime.now());
		boardData.setModDt(LocalDateTime.now());
		
		for (int i = 1; i <= 3; i++) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName("파일명" + i);
			fileInfo.setMineType("image/png");
			fileInfo.setDone(true);
			fileInfo.setRegDt(LocalDateTime.now());
			
			boardData.getFileInfos().add(fileInfo);
		}
		
		boardDataRepository.saveAndFlush(boardData);
		em.clear();
		
		BoardData savedBoardData = boardDataRepository.findById(boardData.getId())
														.orElseThrow(EntityNotFoundException::new);
		
		assertEquals(3, savedBoardData.getFileInfos().size());
	}
	
	public BoardData createBoardData() {
			BoardData boardData = new BoardData();
			boardData.setSubject("게시글 제목");
			boardData.setContents("게시글 본문");
			boardData.setViewCount(100);
			boardData.setRegDt(LocalDateTime.now());
			boardData.setModDt(LocalDateTime.now());
			
			
			for (int i = 1; i <= 3; i++) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFileName("파일명" + i);
				fileInfo.setMineType("image/png");
				fileInfo.setDone(true);
				fileInfo.setRegDt(LocalDateTime.now());
				
				boardData.getFileInfos().add(fileInfo);
			}
			
			Member member = new Member();
			member.setMemId("user1");
			member.setMemNm("사용자1");
			member.setMemPw("12345");
			memberRepository.save(member);
			
			boardData.setMember(member);
			boardDataRepository.save(boardData);
			
			return boardData;
	}
	
	@Test
	@DisplayName("고아객체 제거 테스트")
	public void orphanRemovalTest() {
		BoardData boardData = this.createBoardData();
		boardData.getFileInfos().remove(0);
		em.flush();
	}
}