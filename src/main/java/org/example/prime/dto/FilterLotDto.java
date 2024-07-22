package org.example.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterLotDto {

    private String nameLot;
    private Integer minFloor;
    private Integer maxFloor;
    private String address;
    private Float minArea;
    private Float maxArea;
    private Short minRoom;
    private Short maxRoom;
    private String city;
    private Boolean status;
    private LocalDate startDate;
    private LocalDate endDate;

}
