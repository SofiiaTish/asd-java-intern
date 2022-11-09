package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.ProductDto;
import team.asd.exception.ValidationException;
import team.asd.service.ProductService;
import team.asd.util.ConverterUtil;

@RestController
@RequestMapping(path = {"/product"})
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productService.readById(id), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = {"/"})
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(productService.createProduct(ConverterUtil.convertDtoToProduct(productDto)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = {"/"})
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(productService.updateProduct(ConverterUtil.convertDtoToProduct(productDto)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> deleteProductById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
