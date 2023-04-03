package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.upg_magazin.dto.AddressDto;
import uz.pdp.upg_magazin.service.AddressService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/add")
    public String add(@ModelAttribute AddressDto addressDto){
        addressService.add(addressDto);
        return "redirect:/api/address";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id ){
        addressService.delete(id);
        return "redirect:/api/address";
    }

    @GetMapping("")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("addressList",addressService.listAddress());
        modelAndView.setViewName("address");   /////////// paje quyish
        return modelAndView;

    }
    @GetMapping("/{name}")
    public String get(
            @PathVariable String name
    ){
        addressService.getByName(name);
        return "redirect:/api/address";
    }

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable("id") int id,
            @ModelAttribute AddressDto addressDto
    ){
        addressService.update(id,addressDto);
        return "redirect:/api/address";
    }

}
