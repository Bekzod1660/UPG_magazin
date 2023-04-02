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
import uz.pdp.upg_magazin.entity.enums.RoleEnum;
import uz.pdp.upg_magazin.service.UserService;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
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
        return "login";
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
        if (loginUser!=null){
           if (loginUser.getRoleEnumList().equals(List.of(RoleEnum.USER))){
               modelAndView.setViewName("home");
           }
           else if (loginUser.getRoleEnumList().equals(List.of(RoleEnum.ADMIN))){
               modelAndView.setViewName("product");
           }
           else {
               modelAndView.setViewName("CrudAdmin");
           }
        }
        else {
            modelAndView.setViewName("login");
        }
//
//        modelAndView.setViewName(loginUser != null ? (loginUser.getRoleEnumList().equals(List.of(RoleEnum.SUPER_ADMIN)) ?  "CrudAdmin" :
//                loginUser.getRoleEnumList().equals(List.of(RoleEnum.ADMIN))? "index":"home" ) : "login");
        return modelAndView;
    }

}
