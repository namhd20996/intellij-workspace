package com.example.assign.order;

import com.example.assign.orderdetails.OrderDetailsDTO;
import com.example.assign.product.ProductDTO;
import com.example.assign.user.UserDTO;
import com.example.assign.util.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer status;
    private Double totalMoney;
    @JsonIgnore
    private UserDTO user;
    @JsonIgnore
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
    private List<ProductDTO> products = new ArrayList<>();
}
