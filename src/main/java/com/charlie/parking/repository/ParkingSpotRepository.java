package com.charlie.parking.repository;

import com.charlie.parking.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {
    // Custom query to retrieve all parking spots
    List<ParkingSpot> findAll();
    List<ParkingSpot> findByVehicleLicensePlate(String LicensePlate);

    @Query("SELECT ps FROM ParkingSpot ps WHERE ps.parkingSpotType IN :spotTypes AND ps.vehicle IS NULL "
            + "ORDER BY CASE ps.parkingSpotType "
            + "WHEN 'COMPACT' THEN 1 "
            + "WHEN 'REGULAR' THEN 2 "
            + "END")
    List<ParkingSpot> findAvailableSlotsByVehicleType(@Param("spotTypes") Set<ParkingSpotType> spotTypes);


    @Query("SELECT ps.parkingSpotType, COUNT(ps) FROM ParkingSpot ps WHERE ps.vehicle IS NULL GROUP BY ps.parkingSpotType")
    List<Object[]> countAvailableSpotsBySpotType();

    default Map<ParkingSpotType, Integer> getCountsByParkingSpotTypes() {
        return countAvailableSpotsBySpotType().stream()
                .collect(Collectors.toMap(
                        result -> (ParkingSpotType) result[0],
                        result -> (Integer) result[1]
                ));
    }
}
