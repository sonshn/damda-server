package com.damda.domain.book.entity;

import com.damda.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 작가 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamicInsert
@DynamicUpdate
public class Writer extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "writer_name", nullable = false, length = 100)
    private String writerName;

    @Builder
    public Writer(Long writerId, String writerName) {
        this.writerId = writerId;
        this.writerName = writerName;
    }
}