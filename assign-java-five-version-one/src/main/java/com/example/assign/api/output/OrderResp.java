package com.example.assign.api.output;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResp {

    private UUID id;
    private Date orderDate;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double totalMoney;
    private String message;
    private List<ProductOut> products = new ArrayList<>();
}
