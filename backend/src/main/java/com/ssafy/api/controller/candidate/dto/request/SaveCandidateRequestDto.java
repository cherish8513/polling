package com.ssafy.api.controller.candidate.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SaveCandidateRequestDto {
    private String name;
    private String profilePath;
    private Integer voteTotal;
}
