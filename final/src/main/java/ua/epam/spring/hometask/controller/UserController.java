package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.epam.spring.hometask.facade.UserFacade;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;

    @GetMapping("/all")
    public String viewAll() {

        return "users/all";
    }
}
