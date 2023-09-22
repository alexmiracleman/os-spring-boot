package org.alex.service;

import org.alex.common.Session;
import org.alex.entity.Credentials;
import org.alex.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.UUID;

public interface SecurityService {
    default String generateToken() {
        return UUID.randomUUID().toString();
    }

    Session authenticate(Credentials credentials) throws NoSuchAlgorithmException, NoSuchProviderException;

    Session getSession(String token);

    void logout(String tokenToRemove);

    User getUser(String email, String password) throws NoSuchAlgorithmException;

    String userRegistration(String email, String password) throws NoSuchAlgorithmException, NoSuchProviderException;
}
