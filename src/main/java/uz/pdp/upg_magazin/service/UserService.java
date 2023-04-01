package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.dto.request.UserLoginDto;
import uz.pdp.upg_magazin.entity.User;
import uz.pdp.upg_magazin.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User,UserRequestDto> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User add(UserRequestDto userRequestDto) {
//        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
//        if (byPhoneNumber.isPresent()) {
//            throw new IllegalArgumentException(String.format("PhoneNumber %s already exist", userRequestDto.getPhoneNumber()));
//        }
//        User user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(
//                ()-> new RuntimeException(String.format( userRequestDto.getEmail()+"this email is registered"))
//        );
//
//        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
//        return true;
        Optional<User> optionalUserEntity = userRepository.findByEmail(userRequestDto.getEmail());
        if (optionalUserEntity.isPresent()){
            return null;
        }
        User user = User.of(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userRepository.save(user);
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

    public User login(UserLoginDto userLoginDto) {
        Optional<User> user = userRepository.findByEmail(userLoginDto.getEmail());
        if(user.isPresent()){
            User userResponse = user.get();
            if (passwordEncoder.matches(userLoginDto.getPassword(), userResponse.getPassword())) {
                this.authenticate(userResponse);
                return userResponse;
            }
        }
        return null;
    }
    private void authenticate(User user) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
