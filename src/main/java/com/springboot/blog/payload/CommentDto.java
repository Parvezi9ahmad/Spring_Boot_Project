package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "name can't be null or empty")
    private String name;

    @NotEmpty(message = "Email can't be null or empty")
    @Email
    private String email;

    @NotEmpty(message = "Body can't be null or empty")
    @Size(min=10,message = "Body can have atleast 10 chacrters")
    private String body;
}
