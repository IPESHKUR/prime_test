package org.example.prime.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LotDto {

    private Long id;
    @NotEmpty(message = "The name Lot's cannot be empty")
    private String name;
    @NotEmpty(message = "The city Lot's cannot be empty")
    private String city;
    @NotEmpty(message = "The address Lot's cannot be empty")
    private String address;
    @NotNull
    private float area;
    @NotNull
    private int floor;
    @NotNull
    private short room;

}
