package com.ajwalker.controller;

import com.ajwalker.dto.request.OdaEklemeRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Oda;
import com.ajwalker.service.OdaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(ODA)
@RequiredArgsConstructor
public class OdaController {

    private final OdaService odaSevice;

    @PostMapping(ADD)
    public ResponseEntity<BaseResponse<Boolean>> odaEkle(OdaEklemeRequestDto dto){
        odaSevice.odaEkle(dto);
        return ResponseEntity.ok(
                BaseResponse.<Boolean>builder()
                        .data(true)
                        .message("Oda eklendi!")
                        .success(true)
                        .code(200)
                        .build());
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<BaseResponse<List<Oda>>> odaListele(){
        return ResponseEntity.ok(
          BaseResponse.<List<Oda>>builder()
                  .code(200)
                  .success(true)
                  .message("Oda listesi!")
                  .data(odaSevice.odaListele())
                  .build());
    }
}
