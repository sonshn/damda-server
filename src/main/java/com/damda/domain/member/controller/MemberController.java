package com.damda.domain.member.controller;


import com.damda.domain.member.entity.Member;
import com.damda.domain.member.model.MemberReq;
import com.damda.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 컨트롤러
 */
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 생성 테스트
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@RequestBody MemberReq dto) {
        Member member = memberService.createMember(dto);
        return ResponseEntity.ok(member);
    }
}
