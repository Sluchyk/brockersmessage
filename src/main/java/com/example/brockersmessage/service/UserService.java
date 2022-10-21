package com.example.brockersmessage.service;

import com.example.brockersmessage.exception.NotFoundUserException;
import com.example.brockersmessage.model.User;
import com.example.brockersmessage.model.dto.UserDto;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    void deleteUser(Long id) throws NotFoundUserException;
    User updateUser(User newUser,Long id) throws NotFoundUserException;
    Iterable<User> getAll();
    User getUser(Long id) throws NotFoundUserException;

}
