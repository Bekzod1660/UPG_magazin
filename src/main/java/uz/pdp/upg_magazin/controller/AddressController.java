package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.upg_magazin.dto.AddressDto;
import uz.pdp.upg_magazin.service.AddressService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/add")
    public String add(@RequestBody AddressDto addressDto){
        addressService.add(addressDto);
        return "redirect:/api/address";
    }

    @GetMapping("/{name}")
    public String get(
            @PathVariable String name
    ){
        addressService.getByName(name);
        return "redirect:/api/address";
    }

}
