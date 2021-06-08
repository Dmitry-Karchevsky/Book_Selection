package bookselectionapp.services;

import bookselectionapp.entities.User;
import bookselectionapp.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void loadUserByUsername() {
        System.out.println("hi");
    }

    @Test
    void findUserById() {

    }

    @Test
    void findAllUsers() {
    }

    @Test
    void saveUserFalse() {
        User user = new User();

        user.setEmail("testEmail");
        user.setName("testName");
        user.setAge(20);
        user.setPassword("testPassword");


        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("testEmail");

        User createdUser = userService.saveUser(user);

        Assert.assertFalse(createdUser.getActive());
    }

    @Test
    void saveUserTrue() {
        User user = new User();

        user.setEmail("testEmail");
        user.setName("testName");
        user.setAge(20);
        user.setPassword("testPassword");


        Mockito.doReturn(null)
                .when(userRepository)
                .findByEmail("testEmail");

        User createdUser = userService.saveUser(user);

        Assert.assertTrue(createdUser.getActive());
    }

    @Test
    void updateUser() {
    }

    @Test
    void isTrueUser() {
    }
}