package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/search/{email}", method = RequestMethod.GET)
    public ModelAndView searchUserByEmail(@PathVariable("email") String email) {
        ModelAndView mv = new ModelAndView("users/view");
        UserDTO user = null;

        if(email != null) {
            user = userFacade.findUserByEmail(email);
        }

        mv.addObject("user", user);
        return mv;
    }
}
