package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId,@RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    /*@GetMapping("/post/{postId}/comments")
    public List<CommentDto> getAllComment(@PathVariable  Long postId){
    return commentService.getCommentByPostId(postId);
    }*/
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<?> getAllComment(@PathVariable  Long postId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId),HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId,@PathVariable Long id){
        return new ResponseEntity<CommentDto>(commentService.getCommentById(postId,id),HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId,@PathVariable long commentId,@RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(commentService.updatecomment(postId,commentId,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long postId,@PathVariable Long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<CommentDto>(HttpStatus.OK);
    }

}
