package org.example.prime.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lot")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_name")
    private String name;
    private String city;
    private String address;
    private float area;
    private int floor;

    @Column(name = "count_room")
    private short room;
    private boolean status;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Reservation> reservation;
}
