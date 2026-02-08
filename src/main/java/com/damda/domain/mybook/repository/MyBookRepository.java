package com.damda.domain.mybook.repository;

import com.damda.domain.mybook.entity.MyBook;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * 나의 책 Repository
 */
public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    /**
     * 나의 책 상세 조회 (회원 ID와 책 ID로 조회)
     * - ACTIVE 상태인 책만 조회
     * - Book, Author, Writer를 join fetch로 한 번에 조회
     */
    @Query("""
        SELECT m FROM MyBook m
        JOIN FETCH m.book b
        LEFT JOIN FETCH b.author a
        LEFT JOIN FETCH a.writer w
        WHERE m.mybookId = :mybookId 
            AND m.member.memberId = :memberId
            AND m.status = 'ACTIVE'
    """)
    Optional<MyBook> findByMybookIdAndMemberId(
            @Param("mybookId") Long mybookId,
            @Param("memberId") UUID memberId
    );
}
