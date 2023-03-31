package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.dto.ApiResponse;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.dto.request.UserLoginDto;
import uz.pdp.upg_magazin.entity.User;
import uz.pdp.upg_magazin.entity.enums.RoleEnum;
import uz.pdp.upg_magazin.repository.UserRepository;

import java.util.ArrayList;
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
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        User user = byId.get();
        user.setActive(false);
        userRepository.save(user);
        return true;
    }


    public List<User> listObject() {
        List<User> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.isActive() && user.getRoleEnumList().equals(List.of(RoleEnum.ADMIN,RoleEnum.USER))) {
                userList.add(user);
            }
            if (user.isActive() && user.getRoleEnumList().equals(List.of(RoleEnum.ADMIN))) {
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public boolean update(int id, UserRequestDto userRequestDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        User user = byId.get();
        user.setName(userRequestDto.getName());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        userRepository.save(user);
        return true;
    }

    public User info(int id){
        return userRepository.findAll().get(id);
    }

    public User login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("user not found" + userLoginDto.getEmail())
        );
        if(user!=null){
            if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                this.authenticate(user);
                return user;
            }
        }
        return new User();
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
