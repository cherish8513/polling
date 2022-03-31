package com.polling.auth.dto.request;

import com.polling.entity.member.Member;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthRequestDto {

  @NotNull
  private String nickname;
  @NotNull
  private String wallet;
  @NotNull
  private String accessToken;
  @NotNull
  private String phoneNumber;
}