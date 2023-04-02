package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.upg_magazin.entity.City;

import java.util.Optional;

public interface CityRepository  extends JpaRepository<City,Integer> {
    Optional<City>findByName(String name);
}
