package com.polling.api.controller.candidate.dto.response;

import com.polling.api.controller.candidate.dto.CommentDto;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.candidate.CandidateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindProfileResponseDto {
    private String name;
    private String content;
    private CandidateInfo candidateInfo;
    private Integer voteTotal;
    private List<CommentDto> comments;

    public static FindProfileResponseDto of(Candidate candidate, List<CommentDto> comments){
        return new FindProfileResponseDto(candidate.getName(), candidate.getContent(), candidate.getCandidateInfo(), candidate.getVoteTotal(), comments);
    }
}