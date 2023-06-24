package com.example.spring.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "createddate")
    private Date createdDate;
    @LastModifiedDate
    @Column(name = "lastmodifieddate")
    private Date lastModifiedDate;
    @CreatedBy
    @Column(name = "createdby")
    private String createdBy;
    @LastModifiedBy
    @Column(name = "lastmofiedby")
    private String lastModifiedBy;

}
