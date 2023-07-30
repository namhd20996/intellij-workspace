package com.example.assign.product;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final ValidationHandle validationHandle;

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

    @PostMapping("/add/{uuid}")
    public ResponseEntity<?> post(
            @PathVariable("uuid") UUID uuid,
            @Validated @RequestBody ProductAddRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        productService.addProduct(uuid, request);
        return new ResponseEntity<>("add success...", HttpStatus.CREATED);
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
