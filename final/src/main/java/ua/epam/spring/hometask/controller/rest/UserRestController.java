package ua.epam.spring.hometask.controller.rest;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;

import javax.validation.constraints.Pattern;
import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserRestController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpEntity<UserDTO> save(@RequestBody UserDTO userDto) {
        userFacade.save(userDto);

        return userFacade.findUserByEmail(userDto.getEmail())
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity<HttpStatus> delete(@Pattern(regexp = "\\d", message = "User id should be a number")
                                         @PathVariable("id") long userId) {
        userFacade.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<UserDTO> findUser(@Pattern(regexp = "\\d", message = "User id should be a number")
                                        @PathVariable("id") long userId) {
        return userFacade.findById(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public HttpEntity<UserDTO> searchUserByEmail(@Email @RequestParam("email") String email) {
        return userFacade.findUserByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Collection> index() {
        return new ResponseEntity<>(userFacade.findAll(), HttpStatus.OK);
    }
}
