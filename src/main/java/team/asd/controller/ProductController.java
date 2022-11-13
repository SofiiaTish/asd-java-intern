package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.asd.dto.ProductDto;
import team.asd.service.ProductService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/product"})
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(ConverterUtil.convertProductToDto(productService.readById(id)), HttpStatus.OK);
    }

    @PostMapping(path = {"/"})
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(ConverterUtil.convertProductToDto(productService.createProduct(ConverterUtil.convertDtoToProduct(productDto))), HttpStatus.OK);
    }

    @PutMapping(path = {"/"})
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(ConverterUtil.convertProductToDto(productService.updateProduct(ConverterUtil.convertDtoToProduct(productDto))), HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> deleteProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
