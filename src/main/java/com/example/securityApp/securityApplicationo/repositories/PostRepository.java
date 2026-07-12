package com.example.securityApp.securityApplicationo.repositories;


import com.example.securityApp.securityApplicationo.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
