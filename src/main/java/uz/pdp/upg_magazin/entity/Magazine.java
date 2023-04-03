package uz.pdp.upg_magazin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.upg_magazin.dto.MagazineDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Magazine extends Base {

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String workingTime;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Address address;


    public static Magazine of(MagazineDto magazineDto){
        return Magazine.builder()
                .name(magazineDto.getName())
                .phoneNumber(magazineDto.getPhoneNumber())
                .workingTime(magazineDto.getWorkingTime())
                .address(magazineDto.getAddress())
                .build();
    }

}
