//package org.example.clothingstore.cml;
//
//import com.github.javafaker.Faker;
//import jakarta.transaction.Transactional;
//import org.example.clothingstore.entities.*;
//import org.example.clothingstore.repositories.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final ClothingCategoryRepository clothingCategoryRepository;
//    private final BrandRepository brandRepository;
//    private final ProductRepository productRepository;
//    private final DiscountCouponRepository discountCouponRepository;
//    private final UserRepository userRepository;
//    private final OrderRepository orderRepository;
//
//    @Autowired
//    public DataLoader(ClothingCategoryRepository clothingCategoryRepository, BrandRepository brandRepository,
//                      ProductRepository productRepository, DiscountCouponRepository discountCouponRepository,
//                      UserRepository userRepository, OrderRepository orderRepository) {
//        this.clothingCategoryRepository = clothingCategoryRepository;
//        this.brandRepository = brandRepository;
//        this.productRepository = productRepository;
//        this.discountCouponRepository = discountCouponRepository;
//        this.userRepository = userRepository;
//        this.orderRepository = orderRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        Faker faker = new Faker();
//        Random random = new Random();
//
//        List<ClothingCategory> categories = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            String categoryName = faker.commerce().department();
//            SeasonEnum season = SeasonEnum.values()[random.nextInt(SeasonEnum.values().length)];
//            categories.add(new ClothingCategory(categoryName, season));
//        }
//        clothingCategoryRepository.saveAll(categories);
//
//        List<Brand> brands = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            String brandName = faker.company().name();
//            brands.add(new Brand(brandName));
//        }
//        brandRepository.saveAll(brands);
//
//        List<Product> products = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            ClothingCategory category = categories.get(random.nextInt(categories.size()));
//            Brand brand = brands.get(random.nextInt(brands.size()));
//            String productName = faker.commerce().productName();
//            String color = faker.commerce().color();
//            String size = faker.options().option("S", "M", "L", "XL", "XXL", "XXXL");
//            float price = Float.parseFloat(faker.commerce().price().replace("$", "").replace(",", ""));
//            products.add(new Product(category, brand, productName, color, size, price));
//        }
//        productRepository.saveAll(products);
//
//        List<DiscountCoupon> discountCoupons = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            ClothingCategory category = categories.get(random.nextInt(categories.size()));
//            Brand brand = brands.get(random.nextInt(brands.size()));
//            float discountPercentage = random.nextFloat() * 100; // Скидка от 0 до 100%
//            float minOrderAmount = Float.parseFloat(faker.commerce().price().replace("$", "").replace(",", ""));
//            discountCoupons.add(new DiscountCoupon(category, brand, discountPercentage, minOrderAmount));
//        }
//        discountCouponRepository.saveAll(discountCoupons);
//
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            String username = faker.name().username();
//            String login = faker.name().firstName();
//            String password = faker.internet().password();
//            String email = faker.internet().emailAddress();
//            String phone = faker.phoneNumber().phoneNumber();
//            users.add(new User(username, login, password, email, phone));
//        }
//        userRepository.saveAll(users);
//
//        List<DiscountCoupon> availableCoupons = discountCouponRepository.getAllDiscountCoupons();
//
//        // Генерация заказов
//        List<Order> orders = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            User user = users.get(random.nextInt(users.size()));
//            DiscountCoupon discountCoupon = null;
//
//            // Используем купон, если доступные купоны есть
//            if (!availableCoupons.isEmpty() && random.nextBoolean()) {
//                // Выбор случайного купона
//                int couponIndex = random.nextInt(availableCoupons.size());
//                discountCoupon = availableCoupons.get(couponIndex);
//                // Удаляем купон из списка доступных, чтобы избежать повторного использования
//                availableCoupons.remove(couponIndex);
//            }
//
//            Date date = faker.date().past(30, TimeUnit.DAYS); // Дата заказа в прошлом
//            float orderAmount = Float.parseFloat(faker.commerce().price().replace("$", "").replace(",", ""));
//            OrderStatusEnum orderStatus = random.nextBoolean() ? OrderStatusEnum.PAID : OrderStatusEnum.NOT_PAID;
//            int quantityOfProducts = random.nextInt(5) + 1; // Случайное количество товаров от 1 до 5
//
//            orders.add(new Order(user, discountCoupon, date, orderAmount, orderStatus, quantityOfProducts));
//        }
//        orderRepository.saveAll(orders);
//    }
//
//}
