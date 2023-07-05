package com.example.assign.api;

import com.example.assign.api.output.OrderDetailsResp;
import com.example.assign.api.output.OrderResp;
import com.example.assign.converter.OrderDetailsConverter;
import com.example.assign.dto.OrderDTO;
import com.example.assign.dto.OrderDetailsDTO;
import com.example.assign.service.OrderDetailsService;
import com.example.assign.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    private final OrderDetailsService orderDetailsService;

    private final OrderDetailsConverter orderDetailsConverter;

    @PostMapping("/add")
    public ResponseEntity<OrderResp> addOrder(@RequestBody OrderDTO dto) {
        try {
            return new ResponseEntity<>(orderService.addOrder(dto), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(OrderResp.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-email")
    public ResponseEntity<List<OrderDetailsResp>> getAllByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(orderDetailsConverter.orderDetailsResp(orderDetailsService.findProductsByEmail(email)), HttpStatus.OK);
    }
}
