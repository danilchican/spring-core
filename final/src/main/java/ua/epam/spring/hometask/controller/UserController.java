package ua.epam.spring.hometask.controller;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;

import javax.validation.constraints.Pattern;

@Controller
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("users", userFacade.findAll());
        return "users/view/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("user", new UserDTO());
        return "users/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") UserDTO user) {
        userFacade.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@Pattern(regexp = "\\d", message = "User id should be a number")
                         @RequestParam("id") long userId) {
        userFacade.delete(userId);
        return "redirect:/users";
    }

    @RequestMapping(value = "/search/{email}", method = RequestMethod.GET)
    public ModelAndView searchUserByEmail(@Email @PathVariable("email") String email) {
        ModelAndView modelAndView = new ModelAndView("users/view");
        userFacade.findUserByEmail(email).ifPresent(user -> modelAndView.addObject("user", user));

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@Pattern(regexp = "\\d", message = "User id should be a number")
                                 @PathVariable("id") long userId) {
        ModelAndView modelAndView = new ModelAndView("users/view");
        userFacade.findById(userId).ifPresent(user -> modelAndView.addObject("user", user));

        return modelAndView;
    }
}
