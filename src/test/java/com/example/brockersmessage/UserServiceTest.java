package com.example.brockersmessage;

import com.example.brockersmessage.exception.NotFoundUserException;
import com.example.brockersmessage.model.User;
import com.example.brockersmessage.model.repository.UserRepository;
import com.example.brockersmessage.service.impl.UserServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveTest() {
        User expectedUser = new User("name");
        expectedUser.setId(1L);
        when(this.userRepository.save(any())).thenReturn(expectedUser);

        assertEquals(expectedUser.getId(), userService.saveUser(expectedUser).getId());
    }

    @Test
    void getUserByIdTest() throws NotFoundUserException {
        User user = new User("name");
        user.setId(1L);
        Optional<User> result = Optional.of(user);
        when(userRepository.findById(user.getId())).thenReturn(result);
        assertEquals(user, userService.getUser(user.getId()));
    }

    @Test
    void getAllUsersWithName() {
        List<User> list = List.of(new User("Tim"), new User("Sam"), new User("Tim"));
        List<User> expectedList = list.stream().filter(x -> x.getName().equals("Tim")).collect(Collectors.toList());
        when(userService.getAllUsersWithName("Tim")).thenReturn(expectedList);
        assertEquals(expectedList, userService.getAllUsersWithName("Tim"));
    }

    @Test
    void userNotFoundExceptionTest() {
        assertThrows(NotFoundUserException.class, () -> userService.deleteUser(5L));
    }
}
