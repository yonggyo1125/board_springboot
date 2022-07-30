package com.codefty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefty.entity.BoardData;

public interface BoardDataRepository extends JpaRepository<BoardData, Long> {
	
	List<BoardData> findBySubject(String subject);
	
	List<BoardData> findBySubjectOrContents(String subject, String contents);
}