package uz.pdp.upg_magazin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category extends Base {
    @Column(unique = true,nullable = false)
    private String name;
    private String image;

}
