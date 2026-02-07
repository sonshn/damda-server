package com.damda.domain.mybook.repository;

import com.damda.domain.book.entity.Book;
import com.damda.domain.member.entity.Member;
import com.damda.domain.mybook.entity.MyBook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {
    boolean existsByMemberAndBookAndStatus(Member member, Book Book, MyBook.Status active);

    @EntityGraph(attributePaths = {"book"})
    Optional<MyBook> findByMybookIdAndStatusIs(Long mybookId, MyBook.Status status);
}
