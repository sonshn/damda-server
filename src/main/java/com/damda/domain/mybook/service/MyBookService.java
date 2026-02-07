package com.damda.domain.mybook.service;

import com.damda.domain.member.entity.Member;
import com.damda.domain.mybook.model.MyBookReq;
import com.damda.domain.mybook.model.MyBookRes;
import com.damda.domain.mybook.model.UpdateMyBookReq;

import java.util.UUID;

public interface MyBookService {
    MyBookRes addMyBook(UUID memberId, MyBookReq dto);

    void deleteMyBook(UUID memberId, Integer mybookId);

    Long updateMyBook(Member member, Integer mybookId, UpdateMyBookReq myBookReq);
}
