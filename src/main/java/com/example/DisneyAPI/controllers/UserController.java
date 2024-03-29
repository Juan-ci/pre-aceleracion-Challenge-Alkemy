package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.Credential;
import com.example.DisneyAPI.dto.UserDto;
import com.example.DisneyAPI.services.UserService;
import com.mailjet.client.errors.MailjetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDto guardarUsuario(@RequestBody UserDto user) throws MailjetException {
        return this.userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Credential credentials) throws Exception {
        return userService.logIn(credentials);
    }

    @GetMapping("/users")
    public List<UserDto> obtenerUsuarios() {
        return userService.getUsers();
    }

    @DeleteMapping(path = "/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        boolean borrado = userService.deleteUser(id);

        if (borrado) {
            return "Se borró el usuario con id " + id;
        } else {
            return "No se pudo borrar el usuario con id " + id;
        }
    }

    @PutMapping(path = "/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto character) {
        System.out.println("Paso por put USER");
        return this.userService.updateUser(id, character);
    }

}
