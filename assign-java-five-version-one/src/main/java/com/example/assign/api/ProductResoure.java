package com.example.assign.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductResoure {

    @GetMapping
    public String get() {
        return "GET:: product controller";
    }

    @PostMapping
    public String post() {
        return "POST:: product controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: product controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: product controller";
    }
}
