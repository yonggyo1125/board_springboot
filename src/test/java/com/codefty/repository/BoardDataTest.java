package com.codefty.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.codefty.entity.BoardData;
import com.codefty.entity.Member;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
class BoardDataTest {
	
	@Autowired
	BoardDataRepository boardDataRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@DisplayName("다대일, 일대다 매핑 테스트")
	public void manyToOneAndOneToMany() {
		
		/** 회원 추가 S */
		Member member = new Member();
		member.setMemId("user1");
		member.setMemNm("사용자1");
		member.setMemPw("123456");
		member.setEmail("user1@test.com");
		member.setMobile("01000000000");
		member.setRegDt(LocalDateTime.now());
		Member savedMember = memberRepository.save(member);
		System.out.println(savedMember);

		
		/** 회원 추가 E */
		
		/** 게시글 추가 S */
		for (int i = 1; i <= 5; i++) {
			BoardData boardData = new BoardData();
			boardData.setSubject("게시글 제목" + i);
			boardData.setContents("게시글 본문" + i);
			boardData.setViewCount(100 + i);
			boardData.setMember(savedMember);
			boardData.setRegDt(LocalDateTime.now());
			boardData.setModDt(LocalDateTime.now());
			
			boardDataRepository.save(boardData);
		}
		/** 게시글 추가 E */
				
		/** 게시글 조회 S */
		BoardData boardData = boardDataRepository.findById(Long.valueOf(2)).get();
		System.out.println(boardData);
		System.out.println(member.getMemId());
		/** 게시글 조회 E */
		
		
		/** 일대다 매핑 테스트 S */
	
		Member mem = memberRepository.findById(savedMember.getId()).get();
		System.out.println(mem.getId() + ", " + mem.getMemId());
		System.out.println(mem.getBoardDatas());
			
		/** 일대다 매핑 테스트 E */
	}
}
