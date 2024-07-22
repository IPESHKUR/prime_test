package org.example.prime.mapper;

import org.example.prime.dto.LotDto;
import org.example.prime.entity.Lot;
import org.springframework.stereotype.Component;

@Component
public class LotMapper {

    public Lot lotByLotDto(LotDto lotDto) {
        return Lot.builder()
                .name(lotDto.getName())
                .city(lotDto.getCity())
                .address(lotDto.getAddress())
                .area(lotDto.getArea())
                .floor(lotDto.getFloor())
                .room(lotDto.getRoom())
                .build();
    }
}
