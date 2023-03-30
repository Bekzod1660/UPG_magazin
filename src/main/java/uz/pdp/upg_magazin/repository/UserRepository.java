package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.upg_magazin.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);

    Optional<User>findByIdAndEmail(int id, String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
