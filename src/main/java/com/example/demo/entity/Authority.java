package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "authority" }) })
public class Authority implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "username", nullable = false, foreignKey = @ForeignKey(name = "fk_username") // Đặt tên cho
                                                                                                    // constraint khóa
                                                                                                    // ngoại
    )
    private User user; // Liên kết đến thực thể User (người dùng)

    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    public Authority() {
    }

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}