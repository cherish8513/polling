package com.polling.contact.dto;

import com.polling.entity.contact.status.ContactStatus;
import com.polling.entity.contact.status.ContactType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllContactResponseDto {

  private Long id;
  private ContactStatus contactStatus;
  private ContactType contactType;
  private String title;
  private String content;
  private Long memberId;
  private String answer;
}