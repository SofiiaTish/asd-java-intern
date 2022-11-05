package team.asd.controller;


import org.springframework.web.bind.annotation.*;
import team.asd.entity.Product;

@RestController
@RequestMapping(path = {"/product"})
public class ProductController {

    @GetMapping(path = {"/{id}"})
    public void getProductById(@PathVariable Integer id) {

    }

    @PostMapping(path = {"/"})
    public void createProduct(@RequestBody Product product) {

    }

    @PutMapping(path = {"/"})
    public void updateProduct(@RequestBody Product product) {

    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteProductById(@PathVariable Integer id) {

    }

}
