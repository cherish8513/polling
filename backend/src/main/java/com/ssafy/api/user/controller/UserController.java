package com.ssafy.api.user.controller;

import com.ssafy.api.user.dto.request.SaveUserRequestDto;
import com.ssafy.api.user.dto.request.UpdateUserRequestDto;
import com.ssafy.api.user.dto.response.FindUserResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<Void> save(@RequestBody SaveUserRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<FindUserResponseDto> get(@PathVariable Long id) {
        FindUserResponseDto responseDto = new FindUserResponseDto();
        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<Void> update(@RequestBody UpdateUserRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }
}
