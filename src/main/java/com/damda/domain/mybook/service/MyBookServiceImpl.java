package com.damda.domain.mybook.service;

import com.damda.domain.book.entity.Author;
import com.damda.domain.book.entity.Book;
import com.damda.domain.mybook.entity.MyBook;
import com.damda.domain.mybook.model.*;
import com.damda.domain.mybook.repository.MyBookRepository;
import com.damda.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.damda.global.exception.ErrorCode.BOOK_NOT_OWNED_BY_MEMBER;
import static com.damda.global.exception.ErrorCode.NOT_FOUND_BOOK;

/**
 * 나의 책 서비스 구현체
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MyBookServiceImpl implements MyBookService {

    private final MyBookRepository myBookRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * 나의 책 상세 조회
     * @param memberId 회원 ID
     * @param mybookId 나의 책 ID
     * @return 나의 책 상세 정보
     */
    @Override
    @Transactional(readOnly = true)
    public MyBookDetailRes getMyBookDetail(UUID memberId, Long mybookId) {

        // 1. 나의 책 조회 (회원 소유 확인 포함)
        MyBook myBook = myBookRepository.findByMybookIdAndMemberId(mybookId, memberId)
                .orElseThrow(() -> {
//                    log.warn("나의 책 조회 실패: mybookId={}, memberId={}", mybookId, memberId);
                    return new BaseException(BOOK_NOT_OWNED_BY_MEMBER);
                });
        Book book = myBook.getBook();

        // 2. 작가명 리스트 생성 (콤마로 구분)
        List<Author> authors = book.getAuthor();
        String authorNames = authors.stream()
                .map(author -> author.getWriter().getWriterName())
                .collect(Collectors.joining(", "));

        // 3. BookInfo 생성
        MyBookDetailRes.BookInfo bookInfo = MyBookDetailRes.BookInfo.builder()
                .bookId(String.valueOf(book.getBookId()))
                .source(book.getSource().name())  // ALADIN or CUSTOM
                .title(book.getTitle())
                .author(authorNames)
                .coverImage(book.getCoverImage())
                .publisher(book.getPublisher())
                .totalPage(book.getTotalPage())
                .publishDate(book.getPublishDate() != null
                        ? book.getPublishDate().format(DATE_FORMATTER)
                        : null)
                .isbn(book.getIsbn())
                .aladinId(book.getAladinId())
                .build();

        // 4. HistoryInfo 생성
        MyBookDetailRes.HistoryInfo historyInfo = MyBookDetailRes.HistoryInfo.builder()
                .startedDate(myBook.getStartedDate() != null
                        ? myBook.getStartedDate().format(DATE_FORMATTER)
                        : null)
                .finishedDate(myBook.getFinishedDate() != null
                        ? myBook.getFinishedDate().format(DATE_FORMATTER)
                        : null)
                .build();

        // 5. MyBookDetailRes 생성
        MyBookDetailRes response = MyBookDetailRes.builder()
                .bookInfo(bookInfo)
                .historyInfo(historyInfo)
                .mybookId(String.valueOf(myBook.getMybookId()))
                .readingStatus(myBook.getReadingStatus().name())  // TODO, INPROGRESS, DONE
                .shelfType(myBook.getShelfType())  // STORE or HISTORY
                .createdDate(myBook.getCreatedAt().format(DATE_FORMATTER))
                .reason(myBook.getReason())
                .build();

//        log.info("나의 책 조회 완료: mybookId={}, memberId={}, shelfType={}",
//                mybookId, memberId, response.getShelfType());
        return response;
    }

    /**
     * 책 주인 확인
     * @param mybookId
     * @param memberId
     * @return
     */
    private MyBook getMyBookIfOwner(Long mybookId, UUID memberId) {
        MyBook myBook = myBookRepository.findById(mybookId)
                .orElseThrow(() -> new BaseException(NOT_FOUND_BOOK));
        if (!myBook.getMember().getMemberId().equals(memberId)) {
            throw new BaseException(BOOK_NOT_OWNED_BY_MEMBER);
        }
        return myBook;
    }
}