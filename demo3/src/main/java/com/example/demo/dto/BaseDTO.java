package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class BaseDTO<T> {

    private UUID id;
    private Date createdDate;
    private String createdBy;
    private Date lastModifiedDate;
    private String lastModifiedBy;
    private Long[] ids;
    private List<T> listResult;
    private Integer page;
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String sortName;
    private String sortBy;
    private String alert;
    private String message;
    private String type;
    private String token;
}
