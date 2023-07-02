package com.example.assign.service;

import com.example.assign.dto.GalleryDTO;

import java.util.List;

public interface GalleryService {

    GalleryDTO addGallery(GalleryDTO dto);

    List<GalleryDTO> addAllGallery(List<GalleryDTO> dtoList);
}
