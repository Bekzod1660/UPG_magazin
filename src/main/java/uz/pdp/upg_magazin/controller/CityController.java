package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.upg_magazin.dto.CityDto;
import uz.pdp.upg_magazin.service.CityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;

    @PostMapping("/add")
    public String add(@ModelAttribute CityDto cityDto){
        cityService.add(cityDto);
        return "redirect:/api/city";
    }

    @GetMapping("")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.addObject("cityList",cityService.list());
        modelAndView.setViewName("city");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        cityService.delete(id);
        return "redirect:/api/city";
    }
}
