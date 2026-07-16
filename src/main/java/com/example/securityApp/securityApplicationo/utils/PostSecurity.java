package com.example.securityApp.securityApplicationo.utils;

import com.example.securityApp.securityApplicationo.dto.PostDTO;
import com.example.securityApp.securityApplicationo.entities.User;
import com.example.securityApp.securityApplicationo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {
    private final PostService postService;

    public boolean isOwnerOfPost(Long postId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO postDTO = postService.getPostById(postId);

        return postDTO.getAuthor().getId().equals(user.getId());
    }
}
