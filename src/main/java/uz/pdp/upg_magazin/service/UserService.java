package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;
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
        Optional<User> optionalUserEntity = userRepository.findByEmail(userRequestDto.getEmail());
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
        if (optionalUserEntity.isPresent()&& byPhoneNumber.isPresent()){
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
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFountException(id+" user not found"));
            user.setLastname(userRequestDto.getLastname());
            user.setFirstname(userRequestDto.getFirstname());
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
