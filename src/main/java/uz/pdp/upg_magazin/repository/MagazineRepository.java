package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.upg_magazin.entity.Magazine;

import java.util.Optional;

public interface MagazineRepository extends JpaRepository<Magazine,Integer> {
    Optional<Magazine>findByPhoneNumber(String phoneNumber);
}
