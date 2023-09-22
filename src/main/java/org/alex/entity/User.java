package org.alex.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private int id;
    private String email;
    private String password;
    private String salt;
    private String userType;
}

