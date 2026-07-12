package com.example.securityApp.securityApplicationo.service;



import com.example.securityApp.securityApplicationo.dto.PostDTO;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
