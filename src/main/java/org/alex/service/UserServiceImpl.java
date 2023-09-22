package org.alex.service;

import lombok.AllArgsConstructor;
import org.alex.Repository.UserRepository;
import org.alex.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void add(User user) {
        userRepository.add(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }


}
