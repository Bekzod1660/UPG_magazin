package uz.pdp.upg_magazin.dto.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}
