package com.damda.domain.mybook.model;

import java.util.List;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyBookStoreRes {
    private Long mybookId;
    private LocalDateTime createdDate;
    private String reason; // 읽고 싶은 이유
    private BookInfo bookInfo;

    @Getter
    @Builder
    public static class BookInfo {
        private String title;
        private List<String> author;
        private String coverImage;
        private String description;
    }
}
