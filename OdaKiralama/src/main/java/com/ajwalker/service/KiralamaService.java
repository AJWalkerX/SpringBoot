package com.ajwalker.service;

import com.ajwalker.controller.KiralamaController;
import com.ajwalker.dto.request.OdaKiralamaRequestDto;
import com.ajwalker.entity.Kiralama;
import com.ajwalker.entity.Oda;
import com.ajwalker.entity.OdaDurum;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.KiralamaException;
import com.ajwalker.mapper.KiralamaMapper;
import com.ajwalker.mapper.MusteriMapper;
import com.ajwalker.repository.KiralamaRepository;
import com.ajwalker.view.VwKiralamaTarih;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class KiralamaService {
    private final int BASE_VALUE = 100;
    private final KiralamaRepository kiralamaRepository;
    private final OdaService odaService;
    private final MusteriService musteriService;

    public String odaKirala(OdaKiralamaRequestDto dto) {
        //? Oda yerine odaView olabilir!
        Oda oda = odaService.findById(dto.odaId());
        if (oda == null) {
            //TODO: Buraya Exception gelecek!
                return "Oda bulunamadı!";
        }
        if (oda.getOdaDurum().equals(OdaDurum.DOLU)){
            throw new KiralamaException(ErrorType.ODA_DOLU_ERROR);
        }
        if (!musteriService.existById(dto.musteriId())){
            //TODO: Buraya Exception gelecek!
            return "Once musteri kaydı oluşturup numara alınız!";
        }

        double gunlukFiyat = BASE_VALUE/oda.getOdaType().getRate();
        double toplamFiyat = gunlukFiyat * dto.kiralanacakGunSayisi();

        long bugun = System.currentTimeMillis();
        long bitisTarihi = bugun + TimeUnit.MINUTES.toMillis(dto.kiralanacakGunSayisi());

        Kiralama kiralama = KiralamaMapper.INSTANCE.fromOdaKiralamaRequestDto(dto);
        kiralama.setToplamFiyat(toplamFiyat);
        kiralama.setKiralananGun(bitisTarihi);
        kiralamaRepository.save(kiralama);
        return "Toplam odenecek tutar: "+ toplamFiyat;
    }

    public List<Oda> getTarihiGecmisOdalar(){
        long startTime = System.currentTimeMillis();
        List<Long> byBitisTarihi = kiralamaRepository.findByBitisTarihi(startTime);

        return odaService.doluOdaListele(byBitisTarihi);
    }
}
