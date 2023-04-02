package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.upg_magazin.entity.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address>findByName(String name);
}
