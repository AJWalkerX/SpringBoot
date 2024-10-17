package com.ajwalker.controller;

import com.ajwalker.dto.request.OgrenciEklemeRequestDto;
import com.ajwalker.dto.responce.BaseResponse;
import com.ajwalker.entity.Ogrenci;
import com.ajwalker.service.OgrenciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ajwalker.constants.RestAPIs.*;

@RestController
@RequestMapping(OGRENCI)
@RequiredArgsConstructor
public class OgrenciController {
    private final OgrenciService ogrenciService;
    /*
     * öğrenci ekleme, düzenleme, listeleme, arama öğrenciyi bir sınıfa atama
     */

    @PostMapping(ADD)
    public ResponseEntity<BaseResponse<Boolean>> ogrenciEkleme(@RequestBody OgrenciEklemeRequestDto dto) {
        ogrenciService.ogrenciKaydet(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .message("Ogrenci eklendi.")
                .success(true)
                .build());
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<BaseResponse<List<Ogrenci>>> ogrenciListele() {
        return ResponseEntity.ok(BaseResponse.<List<Ogrenci>>builder()
                .message("Ogrenci Listele")
                .code(200)
                .success(true)
                .data(ogrenciService.getOgrenciListesi())
                .build());
    }

    /*
    * Oda Kiralama
    * -------------
    * Müşteri
    * Oda
    * Kiralama
    * -------------
    * Müşteri ekleme, Listeleme, Oda Ekleme, Listeleme Kiralama
    *
    */
}
