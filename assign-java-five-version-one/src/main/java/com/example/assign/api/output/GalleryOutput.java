package com.example.assign.api.output;

import com.example.assign.dto.GalleryDTO;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryOutput extends BaseOutput<GalleryDTO>{

    private UUID id;
    private String thumbnail;
}
