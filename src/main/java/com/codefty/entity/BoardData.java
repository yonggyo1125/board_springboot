package com.codefty.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
public class BoardData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // 게시글 번호
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	@Column(nullable=false, length=50)
	private String subject; // 게시글 제목
	
	@Lob
	@Column(nullable=false)
	private String contents; // 게시글 내용
	
	@Column(columnDefinition="int default '0' not null")
	private int viewCount; // 게시글 조회수 
	
	@OneToMany(mappedBy="boardData", cascade=CascadeType.ALL,
				orphanRemoval=true, fetch=FetchType.LAZY)
	private List<FileInfo> fileInfos = new ArrayList<>();
	
	private LocalDateTime regDt; //등록시간
	private LocalDateTime modDt; // 수정시간
}