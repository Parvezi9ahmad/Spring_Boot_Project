package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapTodto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long PostId) {
        List<Comment> byPostId = commentRepository.findByPostId(PostId);

        return byPostId.stream().map(p-> mapTodto(p)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        //Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does't belongs to the post");

        }
        return mapTodto(comment);
    }

    @Override
    public CommentDto updatecomment(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does't belongs to the post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updateComment = commentRepository.save(comment);
        CommentDto commentDto = mapTodto(updateComment);
        System.out.println(commentDto);

        return commentDto;
    }

    @Override
    public void deleteComment(long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does't belongs to the post");
                   }

        commentRepository.delete(comment);
    }

    private CommentDto mapTodto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
       /* CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());*/
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        /*Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());*/
        return comment;
    }
}
