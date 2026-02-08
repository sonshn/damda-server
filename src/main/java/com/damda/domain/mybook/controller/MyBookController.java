package com.damda.domain.mybook.controller;

import com.damda.domain.mybook.model.*;
import com.damda.domain.mybook.service.MyBookService;
import com.damda.global.auth.model.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 나의 책 컨트롤러
 */
@RestController
@RequestMapping("/mybooks")
@RequiredArgsConstructor
public class MyBookController {

    private final MyBookService myBookService;

    /**
     * 나의 책 상세 정보 조회
     * @param authMember Spring Security에서 인증된 회원 정보
     * @param mybookId 나의 책 ID
     * @return 나의 책 상세 정보
     */
    @GetMapping("/{mybookId}")
    public ResponseEntity<MyBookDetailRes> getMyBookDetail(@AuthenticationPrincipal AuthMember authMember,
                                                           @PathVariable Long mybookId) {
        MyBookDetailRes res = myBookService.getMyBookDetail(authMember.getMember().getMemberId(), mybookId);
        return ResponseEntity.ok(res);
    }
}