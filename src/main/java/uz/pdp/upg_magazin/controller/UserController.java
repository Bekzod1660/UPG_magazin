package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    
    @PostMapping("/add")
    public String add(@RequestBody UserRequestDto userRequestDto){
        return "redirect:/register";
    }
    @GetMapping("/list")
    public String list(){
        return "ishlad";
    }


}
