package com.vn.ecommerce.multivendor.controller;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.ecommerce.multivendor.exception.ProductException;
import com.vn.ecommerce.multivendor.exception.SellerException;
import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.request.CreateProductRequest;
import com.vn.ecommerce.multivendor.service.ProductService;
import com.vn.ecommerce.multivendor.service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sellers/products")
@RequiredArgsConstructor
public class SellerProductController {

     private final ProductService productService;
     private final SellerService sellerService;

     @GetMapping
     public ResponseEntity<List<Product>> getProductBySellerId(
               @RequestHeader("Authorization") String jwt) throws SellerException {
          Seller seller = sellerService.getSellerProfile(jwt);

          List<Product> products = productService.getProductBySellerId(seller.getId());
          return new ResponseEntity<>(products, HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<Product> createProduct(
               @RequestBody CreateProductRequest request,
               @RequestHeader("Authorization") String jwt) throws SellerException {
          Seller seller = sellerService.getSellerProfile(jwt);
          Product product = productService.createProduct(request, seller);
          System.out.println("jwt: " + jwt);
          return new ResponseEntity<>(product, HttpStatus.CREATED);
     }

     @DeleteMapping("/{productId}")
     public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
          try {
               productService.deleteProduct(productId);
               return new ResponseEntity<>(HttpStatus.OK);
          } catch (ProductException exception) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }

     @PutMapping("{productId}")
     public ResponseEntity<Product> updateProduct(
          @PathVariable Long productId,
          @RequestBody Product product
     ) {
          try {
               Product updateProduct = productService.updateProduct(productId, product);
               return new ResponseEntity<>(updateProduct, HttpStatus.OK);
          } catch(ProductException exception) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }
}
