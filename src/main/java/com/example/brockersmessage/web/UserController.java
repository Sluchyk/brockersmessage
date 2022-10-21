package com.example.brockersmessage.web;

import com.example.brockersmessage.exception.NotFoundUserException;
import com.example.brockersmessage.model.User;
import com.example.brockersmessage.service.impl.UserServiceImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws NotFoundUserException {
        return userService.getUser(id);
    }

    @GetMapping("/list/{name}")
    public List<User> getAllByUserName(@PathVariable String name) {
        System.out.println(name);
        return userService.getAllUsersWithName(name);
    }

    @GetMapping("/list")
    public Iterable<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) throws NotFoundUserException {
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable Long id) throws NotFoundUserException {
        return userService.updateUser(user, id);
    }

}
