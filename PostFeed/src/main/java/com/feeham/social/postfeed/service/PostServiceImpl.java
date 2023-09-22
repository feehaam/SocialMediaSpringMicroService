package com.feeham.social.postfeed.service;

import com.feeham.social.postfeed.model.dto.PostCreateDTO;
import com.feeham.social.postfeed.model.dto.PostReadDTO;
import com.feeham.social.postfeed.model.dto.UnifiedTime;
import com.feeham.social.postfeed.model.entity.Post;
import com.feeham.social.postfeed.networks.TimeFusionProxy;
import com.feeham.social.postfeed.networks.UserDTO;
import com.feeham.social.postfeed.networks.UserServiceProxy;
import com.feeham.social.postfeed.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserServiceProxy userServiceProxy;
    private final TimeFusionProxy timeFusionProxy;

    public PostServiceImpl(PostRepository postRepository, UserServiceProxy userServiceProxy, TimeFusionProxy timeFusionProxy) {
        this.postRepository = postRepository;
        this.userServiceProxy = userServiceProxy;
        this.timeFusionProxy = timeFusionProxy;
    }

    @Override
    public List<Post> getPosts(String userId) {
        UUID userUUID = UUID.fromString(userId);
        return postRepository.findAll().stream().filter(post -> post.getUserId().equals(userUUID)).collect(Collectors.toList());
    }

    @Override
    public List<PostReadDTO> readPosts(String userId){
        ResponseEntity<UserDTO> userResponse;
        List<Post> posts = getPosts(userId);
        List<PostReadDTO> result = new ArrayList<>();
        for(Post post : posts){
            PostReadDTO readDTO = new PostReadDTO();
            readDTO.setContent(post.getContent());
            ResponseEntity<UnifiedTime> timeResponse;
            try{
                 timeResponse = timeFusionProxy.getUnifiedTime(post.getTimeCreated().toString());
                 readDTO.setUnifiedTime(timeResponse.getBody());
            }
            catch (Exception e){
                new UnifiedTime(post.getTimeCreated().toString(), "", "", "", "");
            }
            if(readDTO.getUnifiedTime() == null){
                readDTO.setUnifiedTime(new UnifiedTime(post.getTimeCreated().toString(), "", "", "", ""));
            }
            readDTO.setUserFirstName("Anonymous");
            readDTO.setUserLastName("User");
            try{
                userResponse = userServiceProxy.getUser(userId);
                UserDTO user = userResponse.getBody();
                readDTO.setUserFirstName(user.getFirstName());
                readDTO.setUserLastName(user.getLastName());
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + " | User ID: "+userId);
            }
            result.add(readDTO);
        }
        return result;
    }

    @Override
    public boolean createPost(PostCreateDTO postDTO) {
        Post post = new Post();
        ResponseEntity<UserDTO> userResponse;
        try{
            userResponse = userServiceProxy.getUser(postDTO.getUserId());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        post.setTimeCreated(LocalDateTime.now());
        post.setUserId(UUID.fromString(postDTO.getUserId()));
        post.setContent(postDTO.getContent());
        postRepository.save(post);
        return true;
    }

    @Override
    public boolean updatePost(UUID postId, PostCreateDTO postDTO) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()) return false;
        Post upPost = post.get();
        upPost.setContent(postDTO.getContent());
        return true;
    }

    @Override
    public boolean deletePost(UUID postId) {
        postRepository.deleteById(postId);
        return true;
    }
}
