package uz.pdp.upg_magazin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class Product extends Base {

    @Column(unique = true, nullable = false)
    private String name;

    private String info;
    @Column(nullable = false)
    private double prays;

    @Column(nullable = false)
    private int quantity;

    private boolean isAction;

    @Column(nullable = false)
    private String madel;
    @Column(nullable = false)
    private String image;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Category category;
    ///////////////////////////////
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany
    private List<Magazine>magazineList;

    ////////////////////////////////
}
