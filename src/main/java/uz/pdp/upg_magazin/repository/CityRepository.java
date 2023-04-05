package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.upg_magazin.entity.CityEntity;

import java.util.Optional;

public interface CityRepository  extends JpaRepository<CityEntity,Integer> {
    Optional<CityEntity>findByName(String name);
}
