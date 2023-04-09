package uz.pdp.upg_magazin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.upg_magazin.dto.UserRequestDto;

import uz.pdp.upg_magazin.entity.role.PermissionEnum;
import uz.pdp.upg_magazin.entity.role.RoleEnum;
import uz.pdp.upg_magazin.entity.role.RolePermissionEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends Base implements UserDetails {

    @Column(nullable = false)
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> productList;
    @OneToOne(cascade = CascadeType.ALL)
    private RolePermissionEntity rolePermissionEntities;

    /**
     * This method is used to convert UserRequestDto to User
     */
    public static User of(UserRequestDto userRequestDto) {

        User user = User.builder()
                .firstname(userRequestDto.getFirstname())
                .lastname(userRequestDto.getLastname())
                .email(userRequestDto.getEmail())
                .isActive(true)
                .phoneNumber(userRequestDto.getPhoneNumber())
                .build();
        RolePermissionEntity rolePermission = new RolePermissionEntity();
        if (userRequestDto.isUser()) {
            rolePermission.setPermissionEnum(List.of(PermissionEnum.READ.name()));
            rolePermission.setRoleEnum(List.of(RoleEnum.USER.name()));
            user.setRolePermissionEntities(rolePermission);
            return user;
        }
        rolePermission.setRoleEnum(Arrays.stream(userRequestDto.getRoles().split(",")).toList());
        rolePermission.setPermissionEnum(Arrays.stream(userRequestDto.getPermissions().split(",")).toList());
        user.setRolePermissionEntities(rolePermission);
        return user;}
//public static User of(UserRequestDto userRegisterDTO) {
//
//    if (userRegisterDTO.isUser()) {
//        RolePermissionEntity rolePermission = new RolePermissionEntity();
//        rolePermission.setRoleEnum(List.of(RoleEnum.USER.name()));
//        return User.builder()
//                .email(userRegisterDTO.getEmail())
//                .lastname(userRegisterDTO.getLastname())
//                .firstname(userRegisterDTO.getFirstname())
//                .email(userRegisterDTO.getEmail())
//                .rolePermissionEntities(rolePermission)
//                .build();
//    }
//
//    return User.builder()
//            .email(userRegisterDTO.getEmail())
//            .lastname(userRegisterDTO.getLastname())
//            .firstname(userRegisterDTO.getFirstname())
//            .email(userRegisterDTO.getEmail())
//            .rolePermissionEntities(
//                    new RolePermissionEntity(userRegisterDTO.getRole(),
//                            userRegisterDTO.getPermissions()))
//            .build();
//}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//        roleEnumList.forEach((rol) -> {
//            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.name()));
//        });
//        permissionEnumList.forEach((per) -> {
//            roles.add(new SimpleGrantedAuthority(per.name()));
//        });
//
//        return roles;
        return rolePermissionEntities.getAuthority();

    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
