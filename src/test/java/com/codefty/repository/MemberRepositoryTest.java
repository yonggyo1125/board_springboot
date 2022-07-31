package com.codefty.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.codefty.entity.Member;
import com.codefty.entity.MemberProfile;
import com.codefty.constants.MemberLevel;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberProfileRepository memberProfileRepository;
	
	@Test
	@DisplayName("회원 추가 테스트")
	public void createMember() {

		Member member = new Member();
		MemberProfile profile = new MemberProfile();
		profile.setLevelType(MemberLevel.MEMBER);
		MemberProfile savedProfile = memberProfileRepository.save(profile);
				
		member.setMemId("user1");
		member.setMemNm("사용자1");
		member.setMemPw("12345");
		member.setEmail("user1@test.com");
		member.setMobile("01000000000");
		member.setMemberProfile(savedProfile);
		member.setRegDt(LocalDateTime.now());

		Member savedMember = memberRepository.save(member);
		
		System.out.println(savedMember.getMemberProfile().getLevelType());
		
		savedProfile.setLevelType(MemberLevel.ADMIN);
		System.out.println(savedMember.getMemberProfile().getLevelType());
		
		memberRepository.flush();

		Member mem = memberRepository.findByMemId("user1");
		System.out.println(mem.getMemberProfile().getLevelType());
	}
}
