package org.alex.service;

import org.alex.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void add(User user);

    void deleteAll();


}
