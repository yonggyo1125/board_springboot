package com.codefty.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.codefty.repository.MemberProfileRepository;
import com.codefty.repository.MemberRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberTest {
	
	@Autowired
	MemberRepository memberRepository;
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	@DisplayName("Auditing 테스트")
	public void auditingTest() {
		Member newMember = new Member();
		newMember.setMemId("user2");
		newMember.setMemNm("사용자2");
		newMember.setMemPw("1234");
		memberRepository.save(newMember);
		
		em.flush();
		em.close();
		
		Member member = memberRepository.findById(newMember.getId())
					.orElseThrow(EntityNotFoundException::new);
		
		System.out.println("regDt : " + member.getRegDt());
		System.out.println("modDt : " + member.getModDt());
		System.out.println("createdBy : " + member.getCreatedBy());
		System.out.println("modifiedBy : " + member.getModifiedBy());
		
	}
}
