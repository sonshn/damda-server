package com.damda.domain.member.repository;

import com.damda.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * 회원 레퍼지토리
 */
public interface MemberRepository extends JpaRepository<Member, UUID> {

}
