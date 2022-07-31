package com.codefty.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	private String memId; 
	
	public AuditorAwareImpl(String memId) {
		// 테스트 코드 추가 
		if (memId == null) {
			memId = "user1";
		}
		this.memId = memId;
	}
	
	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.of(memId);
	}
}
