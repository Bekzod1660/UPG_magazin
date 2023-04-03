package uz.pdp.upg_magazin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import uz.pdp.upg_magazin.entity.Address;

@Data
public class MagazineDto {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String workingTime;
    @NotBlank
    private Address address;
}
