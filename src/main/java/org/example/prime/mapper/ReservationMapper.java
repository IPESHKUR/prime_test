package org.example.prime.mapper;

import org.example.prime.dto.ReservationDto;
import org.example.prime.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation reservationByReservationDto(ReservationDto reservationDto) {

        return Reservation.builder()
                .id(reservationDto.getLotId())
                .fullName(reservationDto.getFullName())
                .phoneNumber(reservationDto.getPhoneNumber())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .build();
    }
}
