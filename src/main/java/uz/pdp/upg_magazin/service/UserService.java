package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.entity.User;
import uz.pdp.upg_magazin.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User, UserRequestDto> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User add(UserRequestDto userRequestDto) {
        Optional<User> optionalUserEntity = userRepository.findByEmail(userRequestDto.getEmail());
        if (optionalUserEntity.isPresent()) {
            throw new RecordNotFountException(userRequestDto.getEmail() + " there is a user at this email address");
        }
        User user = User.of(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecordNotFountException(id + " no user found for this ID")
        );
        user.setActive(false);
        userRepository.save(user);
        return true;
    }

    public List<User> getAdminList() {
        return userRepository.findByAllAdmin();
    }

    @Override
    public boolean update(int id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecordNotFountException(id + " no user found for this id")
        );
        user.setLastname(userRequestDto.getLastname());
        user.setFirstname(userRequestDto.getFirstname());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        userRepository.save(user);
        return true;
    }

    public User info(int id) {
        return userRepository.findAll().get(id);
    }
}
