package com.example.assign.api.output;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryResp {

    private UUID id;
    private String thumbnail;
}
