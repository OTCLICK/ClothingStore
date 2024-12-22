package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.*;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.example.clothingstore.services.BrandService;
import org.example.clothingstore.services.ClothingCategoryService;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableCaching
public class DiscountCouponServiceImpl implements DiscountCouponService {

    private final DiscountCouponRepository discountCouponRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BrandService brandService;
    private final ClothingCategoryService clothingCategoryService;

    public DiscountCouponServiceImpl(DiscountCouponRepository discountCouponRepository,
                                     ValidationUtil validationUtil, ModelMapper modelMapper, BrandService brandService, ClothingCategoryService clothingCategoryService) {
        this.discountCouponRepository = discountCouponRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.clothingCategoryService = clothingCategoryService;
    }

    @Override
    @CacheEvict(cacheNames = "discountCoupons", allEntries = true)
    public void addDiscountCoupon(DiscountCouponDTO discountCouponDto) {
        if (!this.validationUtil.isValid(discountCouponDto)) {
            this.validationUtil.violations(discountCouponDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving discount coupon");
        }
        DiscountCoupon discountCoupon = this.modelMapper.map(discountCouponDto, DiscountCoupon.class);
        discountCoupon.setBrand(brandService.findByBrandName(discountCouponDto.getBrand().getBrandName()));
        discountCoupon.setClothingCategory(clothingCategoryService.findByCategoryName(discountCouponDto.
                getClothingCategory().getCategoryName()));
    }

    @Override
    @Cacheable("discountCoupons")
    public DiscountCoupon findById(String id) {
        return this.discountCouponRepository.findById(id);
    }

    @Override
    @Cacheable("discountCoupons")
    public List<DiscountCoupon> findListByDiscountPercentage(float discountPercentage) {
        return this.discountCouponRepository.findListByDiscountPercentage(discountPercentage);
    }

    @Override
//    @Cacheable("discountCoupons")
    public Page<DiscountCouponDTO> getDiscountCoupons(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<DiscountCoupon> couponsPage = discountCouponRepository.findAll(pageable);
        return couponsPage.map(dc -> new DiscountCouponDTO(dc.getId(), dc.getClothingCategory(), dc.getBrand(),
                dc.getDiscountPercentage(), dc.getMinOrderAmount()));
    }

    @Override
    @CacheEvict(cacheNames = "discountCoupons", allEntries = true)
    public String createDiscountCoupon(String categoryName, String brandName,
                                       float discountPercentage, float minOrderAmount) {
            DiscountCoupon newCoupon = new DiscountCoupon(clothingCategoryService.findByCategoryName(categoryName),
                    brandService.findByBrandName(brandName), discountPercentage, minOrderAmount);
            discountCouponRepository.save(newCoupon);
            return newCoupon.getId();
    }

    @Override
    @CacheEvict(cacheNames = "discountCoupons", allEntries = true)
    public void deleteDiscountCoupon(String id) {
        discountCouponRepository.deleteById(id);
    }

    @Override
    @Cacheable("discountCoupons")
    public DiscountCoupon findByDiscountPercentage(float discountPercentage) {
        return this.discountCouponRepository.findByDiscountPercentage(discountPercentage);
    }

    @Override
//    @Cacheable("discountCoupons")
    public List<DiscountCouponDTO> getAvailableCouponsForOrder(Order order) {
        List<DiscountCoupon> allCoupons = discountCouponRepository.findAllCoupons();
        List<DiscountCouponDTO> availableCoupons = new ArrayList<>();

        for (DiscountCoupon coupon : allCoupons) {
            boolean isValid = true;
            for (Product product : order.getProducts()) {
                if (!product.getClothingCategory().equals(coupon.getClothingCategory()) &&
                        !product.getBrand().equals(coupon.getBrand())) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                availableCoupons.add(new DiscountCouponDTO(coupon.getId(), coupon.getClothingCategory(), coupon.getBrand(), coupon.getDiscountPercentage(), coupon.getMinOrderAmount()));
            }
        }

        return availableCoupons;
    }

    @Override
//    @Cacheable("discountCoupons")
    public List<DiscountCoupon> getTopBrands(int limit) {
        return discountCouponRepository.findTopBrands(limit);
    }

    @Override
//    @Cacheable("discountCoupons")
    public List<DiscountCoupon> getTopCategories(int limit) {
        return discountCouponRepository.findTopCategories(limit);
    }
}

