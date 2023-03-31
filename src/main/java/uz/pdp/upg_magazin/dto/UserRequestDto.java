package uz.pdp.upg_magazin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import uz.pdp.upg_magazin.entity.enums.PermissionEnum;
import uz.pdp.upg_magazin.entity.enums.RoleEnum;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserRequestDto {


    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String  phoneNumber;

    private List<RoleEnum> roleEnumList;

    private List<PermissionEnum> permissionEnumList;

    public  boolean isUser(){
        return roleEnumList==null&& permissionEnumList==null;
    }

}
