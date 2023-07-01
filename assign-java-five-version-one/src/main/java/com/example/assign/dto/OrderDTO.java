package com.example.assign.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO extends BaseDTO<OrderDTO>{

    private Date orderDate;
    private Double price;
    private Integer quantity;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Integer status;
    private Double totalMoney;
    private UserDTO user;
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
