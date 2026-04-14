package com.companyname.ecommerce.service;

import com.companyname.ecommerce.exception.ProductNotFoundException;
import com.companyname.ecommerce.model.Product;
import com.companyname.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ("+ productId + ") is not available."));
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProductByProductId(int productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product (" + productId + ") is not available."));
        if (product.getProductName() != null) {
            existingProduct.setProductName(product.getProductName());
        }
        if (product.getProductDesc() != null) {
            existingProduct.setProductDesc(product.getProductDesc());
        }
        if (product.getProductBrand() != null) {
            existingProduct.setProductBrand(product.getProductBrand());
        }
        if (product.getProductPrice() != null) {
            existingProduct.setProductPrice(product.getProductPrice());
        }
        if (product.getProductCategory() != null) {
            existingProduct.setProductCategory(product.getProductCategory());
        }
        if (product.getProductReleaseDate() != null) {
            existingProduct.setProductReleaseDate(product.getProductReleaseDate());
        }
        if (product.getProductAvailability() != null) {
            existingProduct.setProductAvailability(product.getProductAvailability());
        }
        if (product.getProductQuantity() != null) {
            existingProduct.setProductQuantity(product.getProductQuantity());
        }
        return productRepository.save(existingProduct);
    }

    public Product updateProductImgByProductId(int productId, MultipartFile file) throws IOException{
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product (" + productId + ") is not available."));
        product.setProductImgName(file.getOriginalFilename());
            product.setProductImgType(file.getContentType());
            product.setProductImg(file.getBytes());
            return productRepository.save(product);
    }

    public void deleteProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product (" + productId + ") is not available."));
        productRepository.delete(product);
    }

    public List<Product> searchProductByProductName(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }
}
