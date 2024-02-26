package com.charlie.parking.controller;

import com.charlie.parking.model.*;
import com.charlie.parking.service.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class ParkingSpotControllerTest {

    @InjectMocks
    private ParkingSpotController parkingSpotController;

    @Mock
    private ParkingSpotService parkingSpotService;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(parkingSpotController).build();
    }

    @Test
    public void testParkVehicle() throws Exception {
        // Mock request data
        final Vehicle vehicle = new Car("ABC123");
        vehicle.setVehicleType(VehicleType.CAR);

        List<ParkingSpot> parkingSpotList = new ArrayList<>();

        ParkingSpot spot = new ParkingSpot();
        spot.setParkingSpotType(ParkingSpotType.COMPACT);
        spot.setVehicle(vehicle);
        spot.setId(1l);

        parkingSpotList.add(spot);

        // Mock service behavior
        when(parkingSpotService.parkVehicle(any(Vehicle.class))).thenReturn(parkingSpotList);

        // Perform the request
        mockMvc.perform(post("/parking/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vehicle)))
                .andExpect(status().isCreated());

        // Verify service method was called
        verify(parkingSpotService).parkVehicle(any(Vehicle.class));
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
