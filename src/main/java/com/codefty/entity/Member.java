package com.codefty.entity;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // 회원번호
	
	@Column(nullable=false, length=30)
	private String memId; // 아이디
	
	@Column(nullable=false, length=30)
	private String memNm; // 회원명
	
	@Column(nullable=false, length=60)
	private String memPw; // 비밀번호
	private String email; // 이메일 주소
	
	@Column(length=13)
	private String mobile; // 휴대전화번호
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_profile_id")
	private MemberProfile memberProfile;
	
	@OneToMany(mappedBy = "member", fetch=FetchType.LAZY)
	private List<BoardData> boardDatas = new ArrayList<>();
}
