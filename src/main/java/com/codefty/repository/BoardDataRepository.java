package com.codefty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefty.entity.BoardData;

public interface BoardDataRepository extends JpaRepository<BoardData, Long> {
	
}
