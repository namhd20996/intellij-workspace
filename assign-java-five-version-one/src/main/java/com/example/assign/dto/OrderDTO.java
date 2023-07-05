package com.example.assign.dto;

import com.example.assign.api.output.ProductOut;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO extends BaseDTO<OrderDTO> {

    private Date orderDate;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Integer status;
    private Double totalMoney;
    private UserDTO user;
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
    private List<ProductOut> products = new ArrayList<>();
}
