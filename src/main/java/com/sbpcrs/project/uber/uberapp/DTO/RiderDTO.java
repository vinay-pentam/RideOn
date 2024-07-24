package com.sbpcrs.project.uber.uberapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {

    private UserDTO userDTO;

    private Double rating;

}
