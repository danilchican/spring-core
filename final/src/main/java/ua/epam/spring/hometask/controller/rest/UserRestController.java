package ua.epam.spring.hometask.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.dto.UserDTO;
import ua.epam.spring.hometask.facade.UserFacade;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserFacade userFacade;

    @ResponseBody
    @PostMapping("/create")
    public HttpEntity<UserDTO> save(@RequestBody UserDTO userDto) {
        userFacade.save(userDto);

        return userFacade.findUserByEmail(userDto.getEmail())
                .map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .orElse(ResponseEntity.noContent().build());
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public HttpEntity<HttpStatus> delete(@PathVariable("id") long userId) {
        userFacade.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public HttpEntity<UserDTO> findUser(@PathVariable("id") long userId) {
        return userFacade.findById(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @ResponseBody
    @GetMapping("/search/{email}")
    public HttpEntity<UserDTO> searchUserByEmail(@PathVariable("email") String email) {
        return userFacade.findUserByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.noContent().build());
    }

    @ResponseBody
    @GetMapping
    public HttpEntity<Collection> findAll() {
        return new ResponseEntity<>(userFacade.findAll(), HttpStatus.OK);
    }
}
