package uz.pdp.upg_magazin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AddressDto {
    @NotBlank //
    private String name;

    private String cityName;

}
