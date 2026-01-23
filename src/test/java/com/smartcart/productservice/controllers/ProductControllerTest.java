//package com.smartcart.productservice.controllers;
//
//import com.smartcart.productservice.exceptions.ProductNotFoundException;
//import com.smartcart.productservice.models.Product;
//import com.smartcart.productservice.services.ProductService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ProductControllerTest {
//    @InjectMocks
//    private ProductController productController;
//
//    @Mock
//    private ProductService productService;
//
//    @Test
//    public void testGetSingleProductPositive() throws ProductNotFoundException {
//        Long productId = 1L;
//        Product expectedProduct=new Product();
//
//        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct);
//
//        Product actualProduct=productController.getSingleProduct(productId);
//
//        assertEquals(expectedProduct,actualProduct);
//    }
//
//    @Test
//    public void testGetSingleProductNegative() throws ProductNotFoundException {
//        Long productId = -2L;
//
//        when(productService.getSingleProduct(productId)).thenThrow(new ProductNotFoundException(productId));
//
//        assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct(productId));
//    }
//
//    @Test
//    public void testGetAllProducts() {
//
//        Product product1=new Product();
//
//        Product product2=new Product();
//
//        Product product3=new Product();
//
//        List<Product> expectedProducts=List.of(product1,product2,product3);
//
//        when(productService.getAllProducts()).thenReturn(expectedProducts);
//
//        List<Product> actualProducts=productController.getAllProducts();
//
//        assertArrayEquals(expectedProducts.toArray(),actualProducts.toArray());
//    }
//}