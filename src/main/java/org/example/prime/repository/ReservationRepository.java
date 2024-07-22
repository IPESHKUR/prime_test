package org.example.prime.repository;

import org.example.prime.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Override
    @Query("SELECT r FROM Reservation r JOIN FETCH r.lot l")
    List<Reservation> findAll();
}
