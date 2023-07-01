package com.example.assign.repo;

import com.example.assign.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GalleryRepo extends JpaRepository<Gallery, UUID> {
}
