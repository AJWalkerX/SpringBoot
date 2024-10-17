package com.ajwalker.service;

import com.ajwalker.dto.request.MusteriRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.Musteri;
import com.ajwalker.mapper.MusteriMapper;
import com.ajwalker.repository.MusteriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusteriService {

    private final MusteriRepository musteriRepository;

    public void addMusteri(MusteriRequestDto dto) {
        musteriRepository.save(MusteriMapper.INSTANCE.fromMusteriRequestDto(dto));
    }

    public List<Musteri> musteriListele() {
        return musteriRepository.findAll();
    }

    public boolean existById(Long id) {
        return musteriRepository.existsById(id);
    }
}
