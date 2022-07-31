package com.codefty.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter @Setter
public abstract class BaseTimeEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDt;
	
	@LastModifiedDate
	private LocalDateTime modDt;
}