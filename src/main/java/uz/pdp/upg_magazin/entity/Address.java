package uz.pdp.upg_magazin.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.upg_magazin.dto.AddressDto;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends Base{


    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private City city;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Address parent;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Set<Address> parentId;

    public static Address of(AddressDto addressDto) {
        return Address.builder()
                .name(addressDto.getName())
                .build();
    }

}
