package com.ajwalker.entity;

import com.ajwalker.entity.enums.EFollowState;
import com.ajwalker.entity.enums.EProfileState;
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
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    private String avatar;
    private String phone;
    private String about;
    private Long locationId;
    private Integer followCount;
    private Integer followingCount;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EProfileState state = EProfileState.ACTIVE;
    private Boolean hide;
    private Long birthDate;
    private Long memberDate;
    private Long createDate;
    private Long updateDate;

}
