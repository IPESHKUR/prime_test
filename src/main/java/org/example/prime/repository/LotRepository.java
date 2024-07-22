package org.example.prime.repository;

import org.example.prime.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query("""
            SELECT l FROM Lot l
            WHERE (:nameLot IS NULL OR l.name LIKE %:nameLot%)
            AND (:minFloor IS NULL OR l.floor >= :minFloor)
            AND (:maxFloor IS NULL OR l.floor <= :maxFloor)
            AND (:address IS NULL OR l.address LIKE %:address%)
            AND (:minArea IS NULL OR l.area >= :minArea)
            AND (:maxArea IS NULL OR l.area <= :maxArea)
            AND (:minRoom IS NULL OR l.room >= :minRoom)
            AND (:maxRoom IS NULL OR l.room <= :maxRoom)
            AND (:city IS NULL OR l.city = :city)
            AND (:status IS NULL OR l.status = :status)
            """)
    List<Lot> getLotByFilter(@Param(value = "nameLot") String nameLot,
                             @Param(value = "minFloor") Integer minFloor,
                             @Param(value = "maxFloor") Integer maxFloor,
                             @Param(value = "address") String address,
                             @Param(value = "minArea") Float minArea,
                             @Param(value = "maxArea") Float maxArea,
                             @Param(value = "minRoom") Short minRoom,
                             @Param(value = "maxRoom") Short maxRoom,
                             @Param(value = "city") String city,
                             @Param(value = "status") Boolean status
                             );
}
