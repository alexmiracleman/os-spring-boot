package org.alex.Repository;


import org.alex.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    void add(User user);

    void deleteAll();


}
