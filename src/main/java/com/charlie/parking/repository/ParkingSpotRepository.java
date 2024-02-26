package com.charlie.parking.repository;

import com.charlie.parking.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findAll();
    List<ParkingSpot> findByVehicleLicensePlate(String LicensePlate);

    @Query("SELECT ps FROM ParkingSpot AS ps WHERE ps.parkingSpotType IN :spotTypes AND ps.vehicle IS NULL "
            + "ORDER BY CASE ps.parkingSpotType "
            + "WHEN 'MOTORCYCLE' THEN 1 "
            + "WHEN 'COMPACT' THEN 2 "
            + "WHEN 'REGULAR' THEN 3 "
            + "END")
    List<ParkingSpot> findAvailableSlotsByVehicleType(@Param("spotTypes") Set<ParkingSpotType> spotTypes);
/*
    default List<ParkingSpot> findAvailableSpotsByVehicleType(Set<ParkingSpotType> parkingSpotTypes) {
        return findAvailableSlotsByVehicleType(parkingSpotTypes).stream()
                .map(arr -> {
                    ParkingSpot spot = new ParkingSpot();
                    spot.setId((Long) arr[0]); // Assuming the first element is ID
                    spot.setParkingSpotType((ParkingSpotType) arr[1]); // Assuming the second element is ParkingSpotType
                    // Set other properties if needed
                    return spot;
                })
                .collect(Collectors.toList());
    }*/


    //@Query(value = "SELECT ps.parkingSpotType, COUNT(ps) FROM ParkingSpot AS ps WHERE ps.vehicle IS NULL GROUP BY ps.parkingSpotType", nativeQuery = true)
    @Query("SELECT ps.parkingSpotType, COUNT(ps) FROM ParkingSpot AS ps WHERE ps.vehicle IS NULL GROUP BY ps.parkingSpotType")
    List<Object[]> countAvailableSpotsBySpotType();

    default Map<ParkingSpotType, Long> getCountsByParkingSpotTypes() {
        return countAvailableSpotsBySpotType().stream()
                .collect(Collectors.toMap(
                        result -> (ParkingSpotType) result[0],
                        result ->  (Long)result[1]
                ));
    }
}
