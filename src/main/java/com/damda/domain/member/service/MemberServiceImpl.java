package com.damda.domain.member.service;

import com.damda.domain.member.entity.Member;
import com.damda.domain.member.model.MemberReq;
import com.damda.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 서비스 구현체
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member createMember(MemberReq dto) {
        Member member = dto.toEntity();
        return memberRepository.save(member);
    }
}
