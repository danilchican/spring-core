package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("users", userFacade.findAll());
        return "users/view/index";
    }

    @GetMapping("/create")
    public String save(Model model) {
        model.addAttribute("user", new UserDTO());
        return "users/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("user") UserDTO user) {
        userFacade.save(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long userId) {
        userFacade.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/search/{email}")
    public ModelAndView searchUserByEmail(@PathVariable("email") String email) {
        ModelAndView modelAndView = new ModelAndView("users/view");
        userFacade.findUserByEmail(email).ifPresent(user -> modelAndView.addObject("user", user));

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView viewUser(@PathVariable("id") long userId) {
        ModelAndView modelAndView = new ModelAndView("users/view");
        userFacade.findById(userId).ifPresent(user -> modelAndView.addObject("user", user));

        return modelAndView;
    }
}
