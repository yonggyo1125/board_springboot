package com.codefty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codefty.entity.BoardData;

public interface BoardDataRepository extends JpaRepository<BoardData, Long> {
	
	List<BoardData> findBySubject(String subject);
	
	List<BoardData> findBySubjectOrContents(String subject, String contents);
	
	List<BoardData> findByViewCountLessThan(Integer viewCount);
	
	List<BoardData> findByViewCountLessThanOrderByViewCountDesc(Integer viewCount);
	
	@Query("select b from BoardData b where b.contents like %:contents% order by b.viewCount desc")
	List<BoardData> findByContents(@Param("contents") String contents);
	
	@Query(value="select * from board_data b where b.contents like %:contents% order by b.view_count desc", nativeQuery=true)
	List<BoardData> findByContentsByNative(@Param("contents") String contents);
}