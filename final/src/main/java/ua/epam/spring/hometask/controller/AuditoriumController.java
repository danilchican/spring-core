package ua.epam.spring.hometask.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.facade.AuditoriumFacade;

@Controller
@RequestMapping("/auditoriums")
@Validated
public class AuditoriumController {

    @Autowired
    private AuditoriumFacade auditoriumFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("auditoriums", auditoriumFacade.findAll());
        return "auditoriums/view/index";
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public ModelAndView searchAuditoriumByName(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("auditoriums/view");

        auditoriumFacade
                .findAuditoriumByName(name)
                .ifPresent(auditorium -> modelAndView.addObject("auditorium", auditorium));

        return modelAndView;
    }
}
