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
//import java.util.*;
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
//    private final UserRolesRepository rolesRepository;
//    private static final String[] CATEGORIES = {
//            "T-shirt", "Jeans", "Jacket", "Dress", "Skirt", "Shorts", "Sweater",
//            "Suit", "Coat", "Activewear", "Loungewear", "Pajamas", "Swimwear",
//            "Footwear", "Accessories"
//    };
//    private static final String[] BRANDS = {
//            "Nike", "Adidas", "Puma", "Levi's", "Zara", "H&M", "Uniqlo",
//            "Gucci", "Prada", "Chanel", "Under Armour", "Reebok", "Lacoste",
//            "Tommy Hilfiger", "Calvin Klein", "Forever 21"
//    };
//    private Set<String> generatedCategories = new HashSet<>();
//    private Set<String> generatedBrands = new HashSet<>();
//
//    @Autowired
//    public DataLoader(ClothingCategoryRepository clothingCategoryRepository, BrandRepository brandRepository,
//                      ProductRepository productRepository, DiscountCouponRepository discountCouponRepository,
//                      UserRepository userRepository, OrderRepository orderRepository, UserRolesRepository rolesRepository) {
//        this.clothingCategoryRepository = clothingCategoryRepository;
//        this.brandRepository = brandRepository;
//        this.productRepository = productRepository;
//        this.discountCouponRepository = discountCouponRepository;
//        this.userRepository = userRepository;
//        this.orderRepository = orderRepository;
//        this.rolesRepository = rolesRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        Faker faker = new Faker();
//        Random random = new Random();
//
//        List<ClothingCategory> categories = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            String categoryName;
//            do {
//                categoryName = CATEGORIES[random.nextInt(CATEGORIES.length)];
//            } while (generatedCategories.contains(categoryName));
//            generatedCategories.add(categoryName);
//            SeasonEnum season = SeasonEnum.values()[random.nextInt(SeasonEnum.values().length)];
//            categories.add(new ClothingCategory(categoryName, season));
//        }
//        clothingCategoryRepository.saveAll(categories);
//
//        List<Brand> brands = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            String brandName;
//            do {
//                brandName = BRANDS[random.nextInt(BRANDS.length)];
//            } while (generatedBrands.contains(brandName));
//
//            generatedBrands.add(brandName);
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
//            float price = 20 * Float.parseFloat(faker.commerce().price().replace("$", "₽").replace(",", ""));
//            products.add(new Product(category, brand, productName, color, size, price));
//        }
//        productRepository.saveAll(products);
//
//        List<DiscountCoupon> discountCoupons = new ArrayList<>();
//        for (int i = 0; i < 80; i++) {
//            ClothingCategory category = categories.get(random.nextInt(categories.size()));
//            Brand brand = brands.get(random.nextInt(brands.size()));
//            float discountPercentage = 5 + random.nextFloat() * 90;
//            float minOrderAmount = 10 * Float.parseFloat(faker.commerce().price().replace("$", "₽").replace(",", ""));
//            discountCoupons.add(new DiscountCoupon(category, brand, discountPercentage, minOrderAmount));
//        }
//        discountCouponRepository.saveAll(discountCoupons);
//
////        if (rolesRepository.count() == 0) {
////            var customerRole = new Role(UserRolesEnum.CUSTOMER);
////            var adminRole = new Role(UserRolesEnum.ADMIN);
////            rolesRepository.save(customerRole);
////            rolesRepository.save(adminRole);
////        }
//
////        var customerRole = rolesRepository.findRoleByName(UserRolesEnum.CUSTOMER).orElseThrow();
////        var customerUser = new User("customer", "customer", "customer123",
////                "ivan.budko.04@mail.ru", "89201234567");
////        customerUser.setRoles(List.of(customerRole));
////        userRepository.save(customerUser);
//
////        List<DiscountCoupon> availableCoupons = discountCouponRepository.getAllDiscountCoupons();
////        List<User> users = userRepository.findAll(); // Получаем всех пользователей для генерации заказов
//
////        List<Order> orders = new ArrayList<>();
////        for (int i = 0; i < 25; i++) {
////            System.out.println(users.size() + " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
////            User user = users.get(random.nextInt(users.size())); // Случайный пользователь
////            DiscountCoupon discountCoupon = availableCoupons.isEmpty() ? null : availableCoupons.get(random.nextInt(availableCoupons.size()));
////            availableCoupons.remove(discountCoupon);
////            Date orderDate = faker.date().past(30, TimeUnit.DAYS); // Случайная дата в пределах последних 30 дней
////            float orderAmount = Float.parseFloat(faker.commerce().price().replace("$", "").replace(",", ""));
////            OrderStatusEnum orderStatus = OrderStatusEnum.values()[random.nextInt(OrderStatusEnum.values().length)]; // Случайный статус заказа
////            int quantityOfProducts = 0;
////
////            orders.add(new Order(user, discountCoupon, orderDate, orderAmount, orderStatus, quantityOfProducts));
////        }
////        orderRepository.saveAll(orders);
//    }
//    }
//
//
