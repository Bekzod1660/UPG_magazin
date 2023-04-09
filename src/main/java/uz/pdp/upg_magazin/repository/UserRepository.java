package uz.pdp.upg_magazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.upg_magazin.entity.User;

import java.util.*;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u.* FROM USERS u " +
            "    INNER JOIN role_permission_entity rpe " +
            "        ON rpe.id=u.role_permission_entities_id AND is_active=true " +
            "    INNER JOIN role_permission_entity_role_enum rpere " +
            "    ON rpe.id = rpere.role_permission_entity_id AND role_enum='ADMIN'",nativeQuery = true)
    List<User> findByAllAdmin();
    Optional<User> findByPhoneNumber(String phoneNumber);
}
