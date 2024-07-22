package org.example.prime.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.prime.dto.ReservationDto;
import org.example.prime.entity.Lot;
import org.example.prime.entity.Reservation;
import org.example.prime.exception.LotWasReservedException;
import org.example.prime.mapper.ReservationMapper;
import org.example.prime.repository.LotRepository;
import org.example.prime.repository.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final LotService lotService;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final LotRepository lotRepository;


    @Transactional
    public void reserveLot(ReservationDto reservationDto) throws LotWasReservedException {
        Lot lot = lotService.findLotById(reservationDto.getLotId());
        if (!lot.isStatus()) {
            log.info("Lot available for booking");
            Reservation savedReservation = reservationMapper.reservationByReservationDto(reservationDto);
            savedReservation.setLot(lot);
            reservationRepository.save(savedReservation);
            lot.setStatus(true);
        } else {
            throw new LotWasReservedException("Lot reserved, choose another lot");
        }
    }

    @Scheduled(cron = "${job.schedule.cron}")
    public void checkReservationTime() {
        LocalDateTime timeNow = LocalDateTime.now();
        log.info("Checking reservation time");
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (timeNow.isAfter(reservation.getEndDate())) {
                log.info("Reservation has expired");
                Lot lot = reservation.getLot();
                lot.setStatus(false);
                lotRepository.save(lot);
                reservationRepository.delete(reservation);
                log.info("Lot is open");
            }
        }
    }
}
