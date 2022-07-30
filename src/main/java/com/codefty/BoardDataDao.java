package com.codefty;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefty.entity.BoardData;

@RestController
public class BoardDataDao {
	
	@Autowired
	EntityManagerFactory emf;  // [엔티티 매니저 팩토리] - 생성 
	
	@GetMapping("/board_test")
	public BoardData test() {
		
		BoardData board = new BoardData();
		
		// [엔티티 매니저] - 생성
		EntityManager em = emf.createEntityManager();
		
		// [트랜잭션] 획득
		EntityTransaction tx = em.getTransaction();
		
		BoardData findBoardData = null;
		try {
			tx.begin();
			
			board.setSubject("게시글 제목");
			board.setContents("게시글 본문");
			board.setRegDt(LocalDateTime.now());
			
			// 등록
			em.persist(board);
			
			// 수정
			board.setSubject("(수정)게시글 제목");
			
			// 한 건 조회
			findBoardData = em.find(BoardData.class, board.getId());
			
			// 목록 조회
			List<BoardData> boardDatas = em.createQuery("select b from BoardData b", BoardData.class).getResultList();
			System.out.println(boardDatas);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
		
		return findBoardData;
	}
}
