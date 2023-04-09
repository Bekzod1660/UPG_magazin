package uz.pdp.upg_magazin.controller.handle;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.pdp.upg_magazin.common.exception.RecordNotFountException;

@Controller
@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommonExceptionHandler {
    @ExceptionHandler(RecordNotFountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String recordNotFound(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "404";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequest(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "404";
    }
}
