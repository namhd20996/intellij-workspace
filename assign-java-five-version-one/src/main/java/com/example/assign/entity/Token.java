package com.example.assign.entity;

import com.example.assign.sysenum.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_token")
public class Token {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String token;

    @Column
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column
    private boolean expired;

    @Column
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
