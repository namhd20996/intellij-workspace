package com.example.assign.api;

import com.example.assign.dto.ProductDTO;
import com.example.assign.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> get() {
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<ProductDTO>> getAllByCategory(@RequestParam("id") UUID id) {
        return new ResponseEntity<>(productService.findAllByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/get-id")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("id") UUID id) {
        return new ResponseEntity<>(productService.findOneProductById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> post(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public String put() {
        return "PUT:: product controller";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "DELETE:: product controller";
    }

}
