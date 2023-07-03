package com.example.assign.api;

import com.example.assign.api.output.ProductOutPut;
import com.example.assign.converter.ProductConverter;
import com.example.assign.dto.CategoryDTO;
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

    private final ProductConverter productConverter;

    @GetMapping("/all")
    public ResponseEntity<List<ProductOutPut>> get() {
        return new ResponseEntity<>(productConverter.productOutPutList(productService.findAllProduct()), HttpStatus.OK);
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<ProductOutPut>> getAllByCategory(@RequestParam("id") UUID id) {
        return new ResponseEntity<>(productConverter.productOutPutList(productService.findAllByCategoryId(id)), HttpStatus.OK);
    }

    @GetMapping("/get-id")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("id") UUID id){

        return  null;
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
