package com.sbpcrs.project.uber.uberapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    private Long id;

    private UserDTO user;

    private Double rating;

    private Boolean available;

    private String vehicleId;

}
