package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.exception.ProductException;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request, Seller seller);
    public void deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product product) throws ProductException;
    public Product findProductById(Long productId) throws ProductException;
    public List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );
    public List<Product> getProductBySellerId(Long sellerId);
}
