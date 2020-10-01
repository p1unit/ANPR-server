package com.anpr.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Email
    @Column(name = "username", nullable = false,unique = true)
    private String username;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "password",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Transient()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

}