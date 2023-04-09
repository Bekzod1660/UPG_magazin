package uz.pdp.upg_magazin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserRequestDto {


    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;

    private String roles;
    private String permissions;

    @JsonIgnore
    public boolean isUser() {
        return roles == null && permissions == null;
    }

}
