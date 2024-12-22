package org.example.clothingstore.services.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.dto.ProductEditDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.repositories.ProductRepository;
import org.example.clothingstore.services.BrandService;
import org.example.clothingstore.services.ClothingCategoryService;
import org.example.clothingstore.services.ProductService;
import org.example.clothingstore.utils.ValidationUtil;
import org.example.clothingstorecontracts.viewmodel.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ClothingCategoryService clothingCategoryService;
    private final BrandService brandService;

    public ProductServiceImpl(ProductRepository productRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                              ClothingCategoryService clothingCategoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.clothingCategoryService = clothingCategoryService;
        this.brandService = brandService;
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void addProduct(ProductDTO productDto) {
        if (!this.validationUtil.isValid(productDto)) {
            this.validationUtil.violations(productDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving product");
        }
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setClothingCategory(clothingCategoryService.findByCategoryName(productDto.getClothingCategory().
                getCategoryName()));
        product.setBrand(brandService.findByBrandName(productDto.getBrand().getBrandName()));
    }

    @Override
    @Cacheable("products")
    public Product findByProductName(String productName) {
        return this.productRepository.findByProductName(productName);
    }

    @Override
//    @Cacheable("products")
    public Page<ProductDTO> getProducts(String searchWord, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productsPage = searchWord != null
                ? productRepository.findByProductNameContainingIgnoreCase(searchWord, pageable)
                : productRepository.findAll(pageable);

        var result = productsPage.map(p -> new ProductDTO(p.getId(), p.getClothingCategory(), p.getBrand(), p.getProductName(),
                p.getColor(), p.getSize(), p.getPrice()));

        result.forEach(p -> System.out.println(p.getId()));

        return result;
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public String createProduct(String categoryName, String brandName, String name, String color,
                                String size, float price) {
        Product newProduct = new Product(clothingCategoryService.findByCategoryName(categoryName),
                brandService.findByBrandName(brandName), name, color, size, price);
        productRepository.save(newProduct);
        return newProduct.getId();
    }

    @Override
//    @Cacheable("products")
    public ProductDTO getProduct(String id) {
        try {
            var product = productRepository.findById(id);
            return new ProductDTO(product.getId(), product.getClothingCategory(), product.getBrand(), product.getProductName(),
                    product.getColor(), product.getSize(), product.getPrice());
        }
        catch (RuntimeException e) {
            System.out.println("Товар не найден");
            return null;
        }
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public ProductEditDTO getProductEdit(String id) {
        var product = productRepository.findById(id);
        return new ProductEditDTO(product.getId(), product.getPrice(), product.getProductName(),
                product.getClothingCategory().getCategoryName(),
                product.getBrand().getBrandName(), product.getColor(), product.getSize(),
                String.valueOf(product.getClothingCategory().getSeason()));
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void updateProduct(ProductEditDTO productEditDto) {
        try {
            var product = productRepository.findById(productEditDto.id());
            product.setClothingCategory(clothingCategoryService.findByCategoryName(productEditDto.categoryName()));
            product.setBrand(brandService.findByBrandName(productEditDto.brandName()));
            product.setProductName(productEditDto.productName());
            product.setColor(productEditDto.color());
            product.setSize(productEditDto.size());
            product.setPrice(productEditDto.price());
            productRepository.save(product);
        }
        catch (RuntimeException e) {
            System.out.println("Товар не найден");
        }
    }

    @Override
    @Cacheable("products")
    public Product getProductById(String id) {
        return productRepository.findById(id);
    }
}
