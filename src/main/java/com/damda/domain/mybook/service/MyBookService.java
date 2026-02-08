package com.damda.domain.mybook.service;

import com.damda.domain.mybook.model.*;

import java.util.UUID;

/**
 * 나의 책 서비스
 */
public interface MyBookService {
    MyBookDetailRes getMyBookDetail(UUID memberId, Long mybookId);
}