package com.spring.journalingapp.module.user.model;

import javax.persistence.*;

import com.spring.journalingapp.core.BaseModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Entity
@Table(name = "t_user")
@Data
public class User extends BaseModel<String> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "pin")
    private String pin;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "last_online")
    private LocalDateTime lastOnline;
}
