package org.example.prime.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.prime.dto.ReservationDto;
import org.example.prime.exception.LotWasReservedException;
import org.example.prime.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserved")
    public ResponseEntity<String> reserveLot
            (@RequestBody @Valid ReservationDto reservationDto) throws LotWasReservedException {
        try {
            reservationService.reserveLot(reservationDto);
            return ResponseEntity.ok("Reserved Lot");
        } catch (LotWasReservedException ex) {
            throw ex;
        }
    }

}
