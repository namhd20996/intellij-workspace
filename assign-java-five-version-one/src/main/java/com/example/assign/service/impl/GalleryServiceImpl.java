package com.example.assign.service.impl;

import com.example.assign.converter.GalleryConverter;
import com.example.assign.dto.GalleryDTO;
import com.example.assign.entity.Gallery;
import com.example.assign.repo.GalleryRepo;
import com.example.assign.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepo galleryRepo;

    private final GalleryConverter converter;

    @Override
    public GalleryDTO addGallery(GalleryDTO dto) {
        Gallery gallery = converter.toEntity(dto);
        return converter.toDTO(galleryRepo.save(gallery));
    }

    @Override
    public List<GalleryDTO> addAllGallery(List<GalleryDTO> dtoList) {
        List<Gallery> galleries = dtoList.stream()
                .map(converter::toEntity)
                .toList();
        List<Gallery> entities = galleryRepo.saveAll(galleries);
        return entities.stream()
                .map(converter::toDTO)
                .toList();
    }
}
