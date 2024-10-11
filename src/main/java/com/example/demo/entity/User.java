package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private int enabled;

    // mappedBy need para with same name with the association
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authority authority;

    public User() {
    }

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

}
