package com.example.assign.api;

import com.example.assign.api.output.CategoryOutput;
import com.example.assign.converter.CategoryConverter;
import com.example.assign.dto.CategoryDTO;
import com.example.assign.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO dto) {
        if(categoryService.existsByName(dto.getName())){
            dto.setMessage("Category name is taken!..");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryService.addCategory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryOutput>> getAllStatus() {
        return new ResponseEntity<>(categoryConverter.toCategoriesOut(categoryService.findAllByStatus(1)), HttpStatus.OK);
    }
}
