package uz.pdp.upg_magazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.upg_magazin.dto.MagazineDto;
import uz.pdp.upg_magazin.service.MagazineService;



@Controller
@RequestMapping("/api/magazine")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @PostMapping("/add")
    public String add(@RequestBody MagazineDto magazineDto) {
        magazineService.add(magazineDto);
        return "redirect:/api/magazine";
    }

    @GetMapping("")
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.addObject("magazineList", magazineService.magazineList());
        modelAndView.setViewName("dd");////////////////////////////////////////////////////
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        magazineService.delete(id);
        return "redirect:/api/magazine";
    }

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable int id,
            @RequestBody MagazineDto magazineDto
    ){
        magazineService.update(id,magazineDto);
        return "redirect:/api/magazine";
    }

}
