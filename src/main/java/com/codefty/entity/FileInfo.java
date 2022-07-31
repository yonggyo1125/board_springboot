package com.codefty.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class FileInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_data_id")
	private BoardData boardData;
	
	@Column(nullable=false)
	private String fileName;
	
	@Column(nullable=false)
	private String mineType;
	
	@Column(columnDefinition="tinyint default '0'")
	private Integer isDone;
}