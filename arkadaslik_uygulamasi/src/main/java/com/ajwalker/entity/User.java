package com.ajwalker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String avatar;
    private String password;
    private String username;
    private String phone;
    private String email;
    private String address;
    private Integer age;
    private Integer weight;
    private Integer height;
    private Integer followerCount;
    private Integer followingCount;
    private Boolean isActive;


    @Enumerated(EnumType.STRING)
    private EGender gender;
}
