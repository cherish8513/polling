package com.polling.candidate.dto.response;


import com.polling.candidate.dto.CommentDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateInfo;
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
    private List<String> imagePaths;
    private Integer voteTotal;
    private List<CommentDto> comments;

    public static FindProfileResponseDto of(Candidate candidate, List<CommentDto> comments){
        return new FindProfileResponseDto(candidate.getName(), candidate.getProfile(), candidate.getImagePaths(), candidate.getVoteTotal(), comments);
    }
}
