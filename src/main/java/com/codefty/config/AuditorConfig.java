package com.codefty.config;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@EnableJpaAuditing
public class AuditorConfig {
		
	@Bean
	public AuditorAware<String> auditorProvider() {
		
		String memId = null;
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if (attr != null) {
			HttpSession session = attr.getRequest().getSession();
			memId = (String)session.getAttribute("memId");
		}
		
		return new AuditorAwareImpl(memId);
	}
}
