package com.codefty.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class BoardData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // 게시글 번호
	
	@Column(nullable=false, length=50)
	private String subject; // 게시글 제목
	
	@Lob
	@Column(nullable=false)
	private String contents; // 게시글 내용
	
	@Column(columnDefinition="int default '0' not null")
	private int viewCount; // 게시글 조회수 
	
	private LocalDateTime regDt; //등록시간
	private LocalDateTime modDt; // 수정시간
}