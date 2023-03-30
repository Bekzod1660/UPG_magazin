package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.dto.ApiResponse;
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

        return false;
    }


    public List<User> listObject() {
        return userRepository.findAll();
    }

    @Override
    public boolean update(int id, UserRequestDto userRequestDto) {
        return false;
    }

    @Override
    public UserRequestDto getById(int id) {
        return null;
    }
}
