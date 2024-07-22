package org.example.prime.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.prime.dto.FilterLotDto;
import org.example.prime.dto.LotDto;
import org.example.prime.entity.Lot;
import org.example.prime.service.LotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/lot")
public class LotController {

    private final LotService lotService;

    @GetMapping("/get")
    public ResponseEntity<List<Lot>> getAllLots() {
        return new ResponseEntity<>(lotService.getAllLots(), HttpStatus.OK);
    }

    @GetMapping("/getById/{lotId}")
    public ResponseEntity<Lot> getByIdLot(@PathVariable("lotId") Long lotId) {
        try {
            return new ResponseEntity<>(lotService.findLotById(lotId), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            throw ex;
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Lot>> searchLots(@RequestBody FilterLotDto filterLot) {
        List<Lot> lots = lotService.filterLot(filterLot);
        log.info("Lots found: {}", lots);
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Lot> addLot(@RequestBody @Valid LotDto lotDto) {
        Lot lot = lotService.saveLot(lotDto);
        return new ResponseEntity<>(lot, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{lotId}")
    public ResponseEntity<?> removeLot(@PathVariable("lotId") Long lotId) {
        try {
            lotService.removeById(lotId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            throw ex;
        }
    }
}
