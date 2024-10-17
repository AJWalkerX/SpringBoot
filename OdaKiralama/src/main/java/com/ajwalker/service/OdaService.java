package com.ajwalker.service;

import com.ajwalker.dto.request.OdaEklemeRequestDto;
import com.ajwalker.entity.Oda;
import com.ajwalker.entity.OdaDurum;
import com.ajwalker.mapper.OdaMapper;
import com.ajwalker.repository.OdaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OdaService {

    private final OdaRepository odaRepository;

    public void odaEkle(OdaEklemeRequestDto dto) {
        odaRepository.save(OdaMapper.INSTANCE.fromOdaEklemeRequestDto(dto));
    }

    public List<Oda> odaListele() {
        return odaRepository.findAll();
    }

    public Oda findById(Long odaId) {
        if(odaRepository.findById(odaId).isPresent()) {
            return odaRepository.findById(odaId).get();
        }
        return null;
    }

    public List<Oda> doluOdaListele(List<Long> odaIdList) {
        return odaRepository.findAllOdaIdByStatus(OdaDurum.DOLU, odaIdList);
    }
}
