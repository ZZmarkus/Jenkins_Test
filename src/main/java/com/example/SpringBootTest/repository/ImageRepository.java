package com.example.SpringBootTest.repository;

import com.example.SpringBootTest.entity.ImageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageResult, Long> {
}
