package org.example.clothingstore.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.entities.CountryOfOrigin;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.example.clothingstore.service.BrandService;
import org.example.clothingstore.service.ClothingCategoryService;
import org.example.clothingstore.service.DiscountCouponService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService {

    public final DiscountCouponRepository discountCouponRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;
    public final BrandService brandService;
    public final ClothingCategoryService clothingCategoryService;

    public DiscountCouponServiceImpl(DiscountCouponRepository discountCouponRepository,
                                     ValidationUtil validationUtil, ModelMapper modelMapper, BrandService brandService, ClothingCategoryService clothingCategoryService) {
        this.discountCouponRepository = discountCouponRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.clothingCategoryService = clothingCategoryService;
    }

    @Override
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
    public DiscountCoupon findById(int id) {
        return this.discountCouponRepository.findById(id);
    }

    @Override
    public List<DiscountCoupon> findByDiscountPercentage(float discountPercentage) {
        return this.discountCouponRepository.findByDiscountPercentage(discountPercentage);
    }
}
