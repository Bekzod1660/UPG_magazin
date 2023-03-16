package uz.pdp.upg_magazin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.upg_magazin.dto.AdminRequestDto;
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
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private int phoneNumber;

    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private List<Product> productList;

    @Enumerated(value = EnumType.STRING)
    private List<RoleEnum> roleEnumList;

    @Enumerated(value = EnumType.STRING)
    private List<PermissionEnum> permissionEnumList;


    public static User ofUser(UserRequestDto userRequestDto) {

        return User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .username(userRequestDto.getUsername())
                .build();
    }

    public static User ofAdmin(AdminRequestDto adminRequestDto) {
        return User.builder()
                .name(adminRequestDto.getName())
                .email(adminRequestDto.getEmail())
                .phoneNumber(adminRequestDto.getPhoneNumber())
                .username(adminRequestDto.getUsername())
                .build();


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
