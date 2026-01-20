package com.damda.domain.member.service;

import com.damda.domain.member.entity.Member;
import com.damda.domain.member.model.MemberReq;

/**
 * 회원 서비스
 */
public interface MemberService {
    Member createMember(MemberReq dto);
}
