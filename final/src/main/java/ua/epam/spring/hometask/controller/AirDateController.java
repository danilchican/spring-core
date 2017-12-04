package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.dto.AirDateDTO;
import ua.epam.spring.hometask.facade.AirDateFacade;

@Controller
@RequestMapping("/dates")
public class AirDateController {

    @Autowired
    private AirDateFacade airDateFacade;

    @GetMapping("/create")
    public String save(Model model) {
        model.addAttribute("date", new AirDateDTO());
        return "dates/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("date") AirDateDTO date) {
        airDateFacade.addAirDate(date);
        return "redirect:/dates";
    }
}
