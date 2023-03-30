package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements  BaseService<UserRequestDto>{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean add(UserRequestDto userRequestDto) {
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
        if (byPhoneNumber.isPresent()){
            throw new IllegalArgumentException(String.format("PhoneNumber %s already exist",userRequestDto.getPhoneNumber()));
        }
        User user=User.of(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return true;
    }

    @Override
    public boolean delete(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        User user = byId.get();
        user.setActive(false);
        userRepository.save(user);
        return true;
    }


    public List<User> listObject() {
        return userRepository.findAll();
    }

    @Override
    public boolean update(int id, UserRequestDto userRequestDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        User user = byId.get();
        user.setName(userRequestDto.getName());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        userRepository.save(user);

        return true;
    }


    public User getByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
