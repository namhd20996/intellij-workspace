package com.example.assign.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO<RoleDTO> {

    private String name;

    private String code;
}
