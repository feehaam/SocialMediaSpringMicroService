package com.feeham.social.postfeed.service;
import com.feeham.social.postfeed.model.dto.PostCreateDTO;
import com.feeham.social.postfeed.model.dto.PostReadDTO;
import com.feeham.social.postfeed.model.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    public List<Post> getPosts(String userId);
    public List<PostReadDTO> readPosts(String userId);
    public boolean createPost(PostCreateDTO postDTO);
    public boolean updatePost(UUID postId, PostCreateDTO postDTO);
    public boolean deletePost(UUID postId);
}
