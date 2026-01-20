package com.damda.domain.member.model;

import com.damda.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 회원 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberReq {
    @NotBlank
    @Size(min=1, max=20)
    private String nickname;

    private Member.Provider provider;

    @Builder
    public MemberReq(String nickname, Member.Provider provider) {
        this.nickname = nickname;
        this.provider = provider;
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .provider(provider)
                .build();
    }
}
