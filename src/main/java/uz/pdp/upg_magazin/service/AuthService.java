package uz.pdp.upg_magazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.upg_magazin.dto.request.UserLoginDto;
import uz.pdp.upg_magazin.entity.User;
import uz.pdp.upg_magazin.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("username %s not found", email))
        );

    }
    public User findUserByUsername(){
        Optional<User> userEntity = userRepository.findByEmail(getAuthentication().getName());
        return userEntity.orElse(null);
    }
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
