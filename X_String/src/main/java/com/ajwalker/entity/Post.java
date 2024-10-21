package com.ajwalker.entity;

import com.ajwalker.entity.enums.EPostState;
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
@Table(name = "tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String comment;
    private String imageUrl;
    private Long date;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    @Builder.Default
    private EPostState state = EPostState.ACTIVE;
}
