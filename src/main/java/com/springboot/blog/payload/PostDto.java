package com.springboot.blog.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {

    private  Long id;

    //title should not be null or empty
    //title should have atleast 2 chgaracters

    @NotEmpty
    //@NotNull
    @Size(min=2,message = "post title atleast have 2 characters")
    private String title;

    @NotEmpty
    //@NotNull
    @Size(min=10,message = "post description atleast have 10 characters")
    private  String description;

    @NotEmpty(message = "content can't be null or empty")
    //@NotNull
    private String content;
    private Set<CommentDto> comments;
}
