package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.upg_magazin.dto.UserRequestDto;
import uz.pdp.upg_magazin.service.UserService;

@Controller
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public ModelAndView get(ModelAndView modelAndView){
        modelAndView.addObject("adminList",userService.listObject());
        modelAndView.setViewName("CrudAdmin");
        return modelAndView;
    }
    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public String add(@ModelAttribute UserRequestDto userRequestDto){
        userService.add(userRequestDto);
        return "redirect:/api/admin";
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public String delete(
            @PathVariable("id") int id
    ){
        userService.delete(id);
        return "redirect:/api/admin";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public String update(
            @PathVariable("id") int id,
            @ModelAttribute UserRequestDto userRequestDto
    ){
        userService.update(id,userRequestDto);
        return "redirect:/api/admin";
    }
    @PostMapping("/info/{id}")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public String info(
            @PathVariable("id") int id
    ){
        userService.info(id);
        return "redirect:/api/admin";
    }


}
