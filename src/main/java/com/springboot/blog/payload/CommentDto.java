package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
