package uz.pdp.upg_magazin.controller.handle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request,ModelAndView modelAndView) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.addObject("message",HttpStatus.NOT_FOUND);
                modelAndView.setViewName("404");
                return modelAndView;
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.addObject("message",HttpStatus.INTERNAL_SERVER_ERROR);
                modelAndView.setViewName("500");
                return modelAndView;
            }
        }
        return modelAndView;
    }

    public String getErrorPath() {
        return "/error";
    }
}
