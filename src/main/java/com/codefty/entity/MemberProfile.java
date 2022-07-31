package com.codefty.entity;

import javax.persistence.*;

import lombok.Setter;
import lombok.Getter;

import com.codefty.constants.MemberLevel;

@Entity
@Setter @Getter
public class MemberProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberLevel levelType;
	
	private String introduction;
}
