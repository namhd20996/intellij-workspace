package com.example.assign.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepo galleryRepo;

    private final GalleryDTOMapper galleryDTOMapper;

    @Override
    public void addAllGallery(List<GalleryDTO> request) {
        List<Gallery> galleries = request.stream()
                .map(galleryDTOMapper::toEntity)
                .toList();
        galleryRepo.saveAll(galleries);
    }
}
