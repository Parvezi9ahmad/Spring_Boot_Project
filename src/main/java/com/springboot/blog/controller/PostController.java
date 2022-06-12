package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //Code for ResponseENtity for list<Object>
    /*@GetMapping
    public ResponseEntity<Object> getAllPosts(@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
                                    @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize
                                     ) {
        PostResponse allPost = postService.getAllPost(pageNo, pageSize);
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }*/
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value="pageNo",defaultValue = AppConstant.default_page_Number,required = false) int pageNo,
                                              @RequestParam(value="pageSize",defaultValue = AppConstant.default_page_size,required = false) int pageSize,
                                    @RequestParam(value="sortBy",defaultValue = AppConstant.default_sort_by,required = false) String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstant.default_sort_direction,required = false) String sortDir
    ) {

        return postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostbyId(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
        //return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatepost(@Valid @RequestBody PostDto postDto, @PathVariable Long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id) {
        postService.deletePostByid(id);
        return new ResponseEntity<>("post is deleted for this id :" + id, HttpStatus.OK);
    }
}
