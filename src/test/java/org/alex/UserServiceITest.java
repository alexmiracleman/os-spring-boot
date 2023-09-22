package org.alex;

import org.alex.entity.User;
import org.alex.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DisplayName("UserService integration test")
public class UserServiceITest {

    @Autowired
    private UserServiceImpl userService;

    private User user1 = User.builder()
            .email("email1")
            .salt("salt1")
            .password("password1")
            .userType("USER")
            .build();

    private User user2 = User.builder()
            .email("email2")
            .salt("salt2")
            .password("password2")
            .userType("USER")
            .build();

    private User user3 = User.builder()
            .email("email3")
            .salt("salt3")
            .password("password3")
            .userType("USER")
            .build();

    @BeforeEach
    void setUp() {
        userService.deleteAll();
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
    }

    @Test
    @DisplayName("Get all users from DB test")
    public void findAllUsersTest() {
        List<User> users = userService.findAll();
        int expectedSize = 3;
        int actualSize = users.size();

        User expectedFirstUser = user1;
        User expectedSecondUser = user2;
        User expectedThirdUser = user3;

        assertFalse(users.isEmpty());
        assertEquals(expectedSize, actualSize);

        assertEquals(expectedFirstUser.getEmail(), users.get(0).getEmail());
        assertEquals(expectedSecondUser.getEmail(), users.get(1).getEmail());
        assertEquals(expectedThirdUser.getEmail(), users.get(2).getEmail());

        assertEquals(expectedFirstUser.getPassword(), users.get(0).getPassword());
        assertEquals(expectedSecondUser.getPassword(), users.get(1).getPassword());
        assertEquals(expectedThirdUser.getPassword(), users.get(2).getPassword());

        assertEquals(expectedFirstUser.getSalt(), users.get(0).getSalt());
        assertEquals(expectedSecondUser.getSalt(), users.get(1).getSalt());
        assertEquals(expectedThirdUser.getSalt(), users.get(2).getSalt());

        assertEquals(expectedFirstUser.getUserType(), users.get(0).getUserType());
        assertEquals(expectedSecondUser.getUserType(), users.get(1).getUserType());
        assertEquals(expectedThirdUser.getUserType(), users.get(2).getUserType());

    }

    @Test
    @DisplayName("Add user with the duplicate email in DB test")
    void addUserWithTheDuplicateEmail() {

        User duplicateUser = User.builder()
                .email("email3")
                .salt("salt3")
                .password("password3")
                .userType("USER")
                .build();
        try {
            userService.add(duplicateUser);
        } catch (Exception e) {

            List<User> users = userService.findAll();

            int expectedSize = 3;
            int actualSize = users.size();
            assertEquals(expectedSize, actualSize);
        }
    }
}
