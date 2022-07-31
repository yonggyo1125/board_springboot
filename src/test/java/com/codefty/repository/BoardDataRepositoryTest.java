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

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.codefty.entity.QBoardData;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class BoardDataRepositoryTest {
	
	@PersistenceContext
	EntityManager em;
	
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
			boardData.setViewCount(100 + i);
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
	
	@Test
	@DisplayName("조회수 LessThan 테스트")
	public void findByViewCountsLessThanTest() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findByViewCountLessThan(105);
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	@Test
	@DisplayName("가격 내림차순 조회 테스트")
	public void findByViewCountLessThanOrderByViewCountDesc() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findByViewCountLessThanOrderByViewCountDesc(110);
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	@Test
	@DisplayName("@Query를 이용한 게시글 조회 테스트")
	public void findByContentsTest() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findByContents("게시글 본문");
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	@Test
	@DisplayName("nativeQuery 속성을 이용한 게시글 조회 테스트")
	public void findByContentsByNative() {
		this.createBoardDatas();
		List<BoardData> boardDatas = boardDataRepository.findByContentsByNative("게시글 본문");
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	@Test
	@DisplayName("Querydsl 조회 테스트1")
	public void queryDslTest() {
		this.createBoardDatas();
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QBoardData qBoardData = QBoardData.boardData;
		JPAQuery<BoardData> query = queryFactory.selectFrom(qBoardData)
					.where(qBoardData.subject.eq("게시글 제목2"))
					.where(qBoardData.contents.like("%" + "본문" + "%"))
					.orderBy(qBoardData.viewCount.desc());
		
		List<BoardData> boardDatas = query.fetch();
		for (BoardData boardData : boardDatas) {
			System.out.println(boardData);
		}
	}
	
	public void createBoardDatas2() {
		for(int i = 1; i <= 5; i++) {
			BoardData boardData = new BoardData();
			boardData.setSubject("게시글 제목" + i);
			boardData.setContents("게시글 본문" + i);
			boardData.setViewCount(100 + i);
			boardData.setRegDt(LocalDateTime.now());
			boardData.setModDt(LocalDateTime.now());
			boardDataRepository.save(boardData);
		}
		
		for(int i = 6; i <= 10; i++) {
			BoardData boardData = new BoardData();
			boardData.setSubject("게시글 제목" + i);
			boardData.setContents("게시글 본문" + i);
			boardData.setViewCount(100 + i);
			boardData.setRegDt(LocalDateTime.now());
			boardData.setModDt(LocalDateTime.now());
			boardDataRepository.save(boardData);
		}
	}
	
	@Test
	@DisplayName("게시글 Querydsl 조회 테스트 2")
	public void querydslTest2() {
		
		this.createBoardDatas2();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QBoardData boardData = QBoardData.boardData;
		String contents = "게시글 본문";
		int viewCount = 105;
		
		booleanBuilder.and(boardData.contents.like("%" + contents + "%"));
		booleanBuilder.and(boardData.viewCount.gt(viewCount));
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<BoardData> boardDataPagingResult = boardDataRepository.findAll(booleanBuilder, pageable);
		System.out.println("total elements : " + boardDataPagingResult.getTotalElements());
		
		List<BoardData> boardDatas = boardDataPagingResult.getContent();
		for(BoardData _boardData : boardDatas) {
			System.out.println(_boardData);
		}
	}
}