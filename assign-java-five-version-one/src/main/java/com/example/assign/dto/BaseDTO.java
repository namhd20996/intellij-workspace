package com.example.assign.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BaseDTO<T> {

    private UUID id;
    private Date createdDate;
    private String createdBy;
    private Date lastModifiedDate;
    private String lastModifiedBy;
    @JsonIgnore
    private UUID[] ids;
    @JsonIgnore
    private List<T> listResult;
    @JsonIgnore
    private Integer page;
    @JsonIgnore
    private Integer limit;
    @JsonIgnore
    private Integer totalPage;
    @JsonIgnore
    private Integer totalItem;
    @JsonIgnore
    private String sortName;
    @JsonIgnore
    private String sortBy;
    @JsonIgnore
    private String alert;
    @JsonIgnore
    private String message;
    @JsonIgnore
    private String type;
    @JsonIgnore
    private String token;
}
