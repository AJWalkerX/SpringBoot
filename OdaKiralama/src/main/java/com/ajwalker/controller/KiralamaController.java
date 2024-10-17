package com.ajwalker.controller;

import com.ajwalker.dto.request.OdaKiralamaRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Oda;
import com.ajwalker.service.KiralamaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(KIRALAMA)
@RequiredArgsConstructor
public class KiralamaController {

    private final KiralamaService kiralamaService;


    @PostMapping(ADD)
    public ResponseEntity<BaseResponse<String>> odaKiralama(@RequestBody @Valid OdaKiralamaRequestDto dto) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .data(kiralamaService.odaKirala(dto))
                .code(200)
                .success(true)
                .message("Oda Kiralandi!")
                .build());
    }

    @GetMapping(GUNU_GECEN_ODALAR)
    public ResponseEntity<BaseResponse<List<Oda>>> gunuGecenOdalar() {
        return ResponseEntity.ok(BaseResponse.<List<Oda>>builder()
                .message("Gunu Gecen Odalar!")
                .success(true)
                .code(200)
                .data(kiralamaService.getTarihiGecmisOdalar())
                .build());
    }

}
