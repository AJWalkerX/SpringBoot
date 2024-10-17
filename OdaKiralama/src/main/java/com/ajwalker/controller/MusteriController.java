package com.ajwalker.controller;

import com.ajwalker.dto.request.MusteriRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Musteri;
import com.ajwalker.mapper.MusteriMapper;
import com.ajwalker.service.MusteriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(MUSTERI)
@RequiredArgsConstructor
public class MusteriController {

    private final MusteriService musteriService;

    @PostMapping(ADD)
    public ResponseEntity<BaseResponse<Boolean>> addMusteri(MusteriRequestDto dto) {
        musteriService.addMusteri(dto);
        return ResponseEntity.ok(
                BaseResponse.<Boolean>builder()
                        .data(true)
                        .message("Musteri eklendi.")
                        .code(200)
                        .success(true)
                        .build());
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<BaseResponse<List<Musteri>>> MusterileriListele(){
        return ResponseEntity.ok(
                BaseResponse.<List<Musteri>>builder()
                        .success(true)
                        .data(musteriService.musteriListele())
                        .code(200)
                        .message("Musterileri Listele")
                        .build());
    }
}
