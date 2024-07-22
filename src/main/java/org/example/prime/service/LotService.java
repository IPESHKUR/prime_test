package org.example.prime.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.prime.dto.FilterLotDto;
import org.example.prime.dto.LotDto;
import org.example.prime.entity.Lot;
import org.example.prime.mapper.LotMapper;
import org.example.prime.repository.LotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotService {

    private final LotMapper lotMapper;
    private final LotRepository lotRepository;

    @Transactional
    public Lot saveLot(LotDto lotDto) {
        log.info("Trying to save a lot");
        if (lotDto == null) {
            throw new IllegalArgumentException("LotDto cannot be null");
        }
        try {
            Lot lot = lotMapper.lotByLotDto(lotDto);
            Lot savedLot = lotRepository.save(lot);
            log.info("Lot saved successfully");
            return savedLot;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("An error occurred while saving the lot");
        }
    }

    @Transactional
    public void removeById(Long lotId) {
        Optional<Lot> optionalLot = lotRepository.findById(lotId);
        if (optionalLot.isPresent()) {
            log.info("Trying to remove a lot with id {}", lotId);
            lotRepository.deleteById(lotId);
            log.info("Lot with id {} successfully removed", lotId);
        } else {
            throw new EntityNotFoundException("Lot with id " + lotId + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public List<Lot> filterLot(FilterLotDto filterLotDto) {
        return lotRepository.getLotByFilter(
                filterLotDto.getNameLot(),
                filterLotDto.getMinFloor(),
                filterLotDto.getMaxFloor(),
                filterLotDto.getAddress(),
                filterLotDto.getMinArea(),
                filterLotDto.getMaxArea(),
                filterLotDto.getMinRoom(),
                filterLotDto.getMaxRoom(),
                filterLotDto.getCity(),
                filterLotDto.getStatus()
        );
    }

    public Lot findLotById(Long id) {
        return lotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lot not found"));
    }

}
