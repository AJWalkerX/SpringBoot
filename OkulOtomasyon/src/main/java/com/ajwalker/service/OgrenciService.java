package com.ajwalker.service;

import com.ajwalker.dto.request.OgrenciEklemeRequestDto;
import com.ajwalker.entity.Ogrenci;
import com.ajwalker.mapper.OgrenciMapper;
import com.ajwalker.repository.OgrenciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OgrenciService {

    private final OgrenciRepository ogrenciRepository;

    public void ogrenciKaydet(OgrenciEklemeRequestDto dto){
        ogrenciRepository.save(OgrenciMapper.INSTANCE.fromOgrenciEklemeRequestDto(dto));

    }

    public List<Ogrenci> getOgrenciListesi(){
        return ogrenciRepository.findAll();
    }
}
