package com.polling.member.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.member.status.MemberRole;
import com.polling.member.dto.request.ChangePasswordRequestDto;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.service.MemberService;
import com.polling.repository.member.MemberRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<Void> save(@RequestBody SaveNativeMemberRequestDto requestDto) {
        memberService.addMember(requestDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto) {
        memberRepository.deleteById(memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<FindMemberResponseDto> getMember(@CurrentUser MemberDto memberDto) {
        FindMemberResponseDto responseDto = memberService.findMember(memberDto.getId());
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/nickname/{n}")
    @ApiOperation(value = "닉네임 중복체크")
    public ResponseEntity<Void> checkNickname(@PathVariable("n") String nickname) {
        memberService.checkDuplicateMemberNickname(nickname);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/nickname/{nickname}")
    @ApiOperation(value = "닉네임 수정")
    public ResponseEntity<Void> changeNickname(@CurrentUser MemberDto memberDto, @PathVariable String nickname) {
        memberService.changeNickname(memberDto.getId(),nickname);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/password/{id}")
    @ApiOperation(value = "패스워드 수정")
    public ResponseEntity<Void> changePassword(@CurrentUser MemberDto memberDto, @RequestBody ChangePasswordRequestDto requestDto) {
        memberService.changePassword(memberDto.getId(),requestDto);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/role/{id}")
    @ApiOperation(value = "회원 권한 수정")
    public ResponseEntity<Void> changeRole(@PathVariable Long id, Set<MemberRole> memberRole) {
        memberService.changeRole(id, memberRole);
        return ResponseEntity.status(200).build();
    }
}