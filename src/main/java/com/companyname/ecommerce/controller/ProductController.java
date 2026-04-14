package com.companyname.ecommerce.controller;

import com.companyname.ecommerce.exception.ProductImgNotFoundException;
import com.companyname.ecommerce.model.Product;
import com.companyname.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get the product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product fetchedProduct = productService.getProductById(productId);
        return new ResponseEntity<>(fetchedProduct, HttpStatus.OK);
    }

    @GetMapping("/{productId}/img")
    @Operation(summary = "Get product image by product ID")
    public ResponseEntity<byte []> getProductImgByProductId(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        if (product.getProductImg() == null) {
            throw new ProductImgNotFoundException("Product (" + productId + ")'s image is not available.");
        }
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType(product.getProductImgType()));
        return new ResponseEntity<>(product.getProductImg(), header,  HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Search product by name keyword")
    public ResponseEntity<List<Product>> searchProductByProductName(@RequestParam String keyword){
        List<Product> searchedProducts = productService.searchProductByProductName(keyword);
        return new ResponseEntity<>(searchedProducts, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update product details")
    public ResponseEntity<Product> updateProductByProductId(@PathVariable int productId, @RequestBody Product product){
        Product updatedProduct = productService.updateProductByProductId(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PutMapping(path = "/{productId}/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update product image")
    public ResponseEntity<Product> updateProductImgByProductId(@PathVariable int productId, @RequestPart("imgFile") MultipartFile file) throws IOException{
        Product updatedImg = productService.updateProductImgByProductId(productId, file);
        return new ResponseEntity<>(updatedImg, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<Void> deleteProductById(@PathVariable int productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
