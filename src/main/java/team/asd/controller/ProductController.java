package team.asd.controller;


import org.springframework.web.bind.annotation.*;
import team.asd.dao.ProductDao;
import team.asd.entity.Product;
import team.asd.service.ProductService;

@RestController
@RequestMapping(path = {"/product"})
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductDao productDao) {
        productService = new ProductService(productDao);
    }

    @GetMapping(path = {"/{id}"})
    public Product getProductById(@PathVariable Integer id) {
        return productService.readById(id);
    }

    @PostMapping(path = {"/"})
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping(path = {"/"})
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(path = {"/{id}"})
    public String deleteProductById(@PathVariable Integer id) {
        return "Deleting: " + productService.deleteProduct(id);
    }
}
