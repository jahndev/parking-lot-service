package com.charlie.parking.repository;

import com.charlie.parking.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
