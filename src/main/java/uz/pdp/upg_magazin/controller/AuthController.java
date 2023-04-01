package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.dto.request.UserLoginDto;
import uz.pdp.upg_magazin.entity.User;
import uz.pdp.upg_magazin.service.UserService;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("authentication");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(
            @ModelAttribute UserRequestDto userRequestDto,
            ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "user", userService.add(userRequestDto) != null ? userRequestDto : "ERROR"
        );
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute UserLoginDto userLoginDto) {
        return "authentication";
    }

    @PostMapping("/login")
    public ModelAndView login(
            @ModelAttribute UserLoginDto userLoginDto,
            ModelAndView modelAndView
    ) {
        User loginUser = userService.login(userLoginDto);
        modelAndView.addObject(
                "user", loginUser != null ? loginUser : "ERROR"
        );
        modelAndView.setViewName(loginUser != null ? "home" : "authentication");
        return modelAndView;
    }

}
