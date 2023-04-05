package uz.pdp.upg_magazin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityRequestDTO {
    @NotBlank
    private String name;
}
