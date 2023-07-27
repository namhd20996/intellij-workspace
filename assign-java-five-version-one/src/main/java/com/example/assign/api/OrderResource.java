package com.example.assign.api;

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

    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO dto) {
        return new ResponseEntity<>(orderService.addOrder(dto), HttpStatus.OK);
    }

    @PostMapping("/get-email")
    public ResponseEntity<List<OrderDetailsDTO>> getAllByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(orderDetailsService.findProductsByEmail(email), HttpStatus.OK);
    }
}
