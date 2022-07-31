package com.codefty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.codefty.entity.MemberProfile;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long>, QuerydslPredicateExecutor {

}
