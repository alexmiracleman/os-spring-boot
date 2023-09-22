package org.alex.service;

import lombok.RequiredArgsConstructor;
import org.alex.common.Session;
import org.alex.entity.Credentials;
import org.alex.entity.User;
import org.alex.util.PasswordUtils;
import org.alex.util.SessionTime;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private final List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());

    private final UserServiceImpl userService;

    private final PasswordUtils passwordUtils;

    private final SessionTime sessionTime;


    @Override
    public Session authenticate(Credentials credentials) throws NoSuchAlgorithmException, NoSuchProviderException {
        User user = getUser(credentials.getEmail(), credentials.getPassword());
        if (user != null) {
            String token = generateToken();
            LocalDateTime expireDate = LocalDateTime.now().plusMinutes(sessionTime.getSessionTimeToLive());
            Session session = Session.builder()
                    .token(token)
                    .expireDate(expireDate)
                    .userType(String.valueOf(user.getUserType()))
                    .cart(new ArrayList<>())
                    .build();
            sessionList.add(session);
            return session;
        }
        return null;
    }

    @Override
    public Session getSession(String token) {
        for (Session session : sessionList) {
            if (session.getToken().equals(token)) {
                if (session.getExpireDate().isAfter(LocalDateTime.now())) {
                    return session;
                }
            }
        }
        sessionList.removeIf(session -> session.getExpireDate().isBefore(LocalDateTime.now()));
        return null;
    }

    @Override
    public void logout(String tokenToRemove) {
        sessionList.removeIf(session -> session.getToken().equals(tokenToRemove));
    }

    @Override
    public User getUser(String email, String password) throws NoSuchAlgorithmException {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(passwordUtils.generateHash(password, user.getSalt()))) {
                    return user;
                }
                break;
            }
        }
        return null;
    }

    @Override
    public String userRegistration(String email, String password) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (password.isBlank() || email.isBlank()) {
            return "The email or password cannot be empty";
        } else {
            String salt = passwordUtils.generateSalt();
            User user = User.builder()
                    .email(email)
                    .salt(salt)
                    .password(passwordUtils.generateHash(password, salt))
                    .userType("USER")
                    .build();
            try {
                userService.add(user);
            } catch (Exception e) {
                return "The email you entered is already registered, please proceed to log in page";
            }
        }
        return "CONGRATULATIONS. YOU'RE NOW REGISTERED. PLEASE LOG IN WITH YOUR EMAIL AND PASSWORD";
    }
}

