package org.example.clothingstore.services;

import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.dto.ProductEditDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstorecontracts.viewmodel.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    void addProduct(ProductDTO productDto);

    Product findByProductName(String productName);

    Page<ProductDTO> getProducts(String searchWord, int page, int size);

    ProductDTO getProduct(String id);

    ProductEditDTO getProductEdit(String id);

    String createProduct(String categoryName, String brandName, String name, String color,
                         String size, float price);

    void deleteProduct(String id);

    void updateProduct(ProductEditDTO productEditDTO);

    Product getProductById(String id);
}
