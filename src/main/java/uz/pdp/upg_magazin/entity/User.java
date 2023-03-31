package uz.pdp.upg_magazin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.entity.enums.PermissionEnum;
import uz.pdp.upg_magazin.entity.enums.RoleEnum;

import java.util.ArrayList;
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
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String  phoneNumber;

    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private List<Product> productList;

    @Enumerated(value = EnumType.STRING)
    private List<RoleEnum> roleEnumList;

    @Enumerated(value = EnumType.STRING)
    private List<PermissionEnum> permissionEnumList;


    public static User of(UserRequestDto userRequestDto) {
        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .isActive(true)
                .phoneNumber(userRequestDto.getPhoneNumber())
                .build();

        if (userRequestDto.isUser()) {
            user.setPermissionEnumList(List.of());
            user.setRoleEnumList(List.of(RoleEnum.USER));
            return user;
        }


        user.setPermissionEnumList(userRequestDto.getPermissionEnumList());
        user.setRoleEnumList(userRequestDto.getRoleEnumList());
        return user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roleEnumList.forEach((rol) -> {
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol));
        });
        permissionEnumList.forEach((per) -> {
            roles.add(new SimpleGrantedAuthority(per.name()));
        });
        return roles;

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
