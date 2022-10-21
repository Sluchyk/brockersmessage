package com.example.brockersmessage.service.impl;

import com.example.brockersmessage.exception.NotFoundUserException;
import com.example.brockersmessage.model.User;
import com.example.brockersmessage.model.repository.UserRepository;
import com.example.brockersmessage.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws NotFoundUserException {
        User user = findUser(id);
        userRepository.delete(user);

    }

    @Override
    public User updateUser(User newUser, Long id) throws NotFoundUserException {
        User user = findUser(id);
        user.setName(newUser.getName());
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersWithName(String username) {
        ArrayList<User> userArrayList = new ArrayList<>();
        getAll().iterator().forEachRemaining(userArrayList::add);
        return userArrayList
                .stream()
                .filter(user -> user.getName().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(Long id) throws NotFoundUserException {
        return findUser(id);
    }
     private  User findUser(Long id) throws NotFoundUserException {
         return  userRepository.findById(id).orElseThrow(()->new NotFoundUserException("Not found user with id: "+id));
     }

}
