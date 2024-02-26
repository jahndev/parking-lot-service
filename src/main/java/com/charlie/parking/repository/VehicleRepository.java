package com.charlie.parking.repository;

import com.charlie.parking.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Vehicle findByLicensePlate(String licensePlate);
}
