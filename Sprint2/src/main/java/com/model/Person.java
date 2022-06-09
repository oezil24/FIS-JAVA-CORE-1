package com.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Person extends AbstractEntity{
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
}
