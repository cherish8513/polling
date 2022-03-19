package com.polling.member.service;


import com.polling.api.controller.exception.CustomErrorResult;
import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.response.FindMemberResponseDto;
import com.polling.api.service.member.MemberService;
import com.polling.core.entity.member.Member;
import com.polling.core.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService target;
    @Mock
    private MemberRepository memberRepository;

    private final String email = "test@email.com";
    private final String nickname = "testNick";

    @Test
    public void memberServiceIsNotNull() throws Exception{
        assertThat(target).isNotNull();
        assertThat(memberRepository).isNotNull();
    }
    
    @Test
    public void 멤버등록실패_이미존재함() throws Exception{
        //given
        doReturn(true).when(memberRepository).existsByEmail(email);
        SaveNativeMemberRequestDto requestDto =
                new SaveNativeMemberRequestDto(nickname, email, "test", "01012345678");

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.addMember(requestDto));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.DUPLICATE_EMAIL);
    }
    
    @Test
    public void 멤버등록성공() throws Exception{
        //given
        doReturn(false).when(memberRepository).existsByEmail(email);
        doReturn(createMember()).when(memberRepository).save(any(Member.class));
        SaveNativeMemberRequestDto requestDto = new SaveNativeMemberRequestDto(nickname, email, "test", "01012345678");

        //when
        target.addMember(requestDto);
    
        //then
        verify(memberRepository, times(1)).existsByEmail(email);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void 멤버조회실패_멤버없음() throws Exception{
        //given
        doReturn(Optional.empty()).when(memberRepository).findById(any(Long.class));

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.findMember(1L));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.USER_NOT_FOUND);
    }

    @Test
    public void 멤버조회성공() throws Exception{
        //given
        doReturn(Optional.of(createMember())).when(memberRepository).findById(any(Long.class));

        //when
        FindMemberResponseDto findMember = target.findMember(1L);

        //then
        assertThat(findMember.getEmail()).isEqualTo(email);
        verify(memberRepository, times(1)).findById(any(Long.class));
    }

    private Member createMember(){
        return Member.builder()
                .nickname(nickname)
                .password("test")
                .email(email)
                .phoneNumber("01012345678")
                .build();
    }
}
