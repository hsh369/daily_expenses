package com.example.dailyexpenses.controller;

import com.example.dailyexpenses.dto.ProductDto;
import com.example.dailyexpenses.entity.ProductEntity;
import com.example.dailyexpenses.repository.ProductRepository;
import com.example.dailyexpenses.repository.UserRepository;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.dailyexpenses.controller.UserController.getUser;

@Controller
public class ProductController {

    private static ProductRepository productRepository;
    private static UserRepository userRepository;
    private static final HashMap<Long, ProductDto> addingProducts = new HashMap<>();

    @Autowired
    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        ProductController.userRepository = userRepository;
        ProductController.productRepository = productRepository;
    }

    public static void addProductToDB(long chatId, ProductDto product) {
        ProductEntity p = new ProductEntity(product.getProductType(), product.getProductPay(), product.getProductName(), product.getProductAmount(), product.getProductPrice(), product.getProductDate(),getUser(chatId));
        productRepository.save(p);
    }

    public static void addProductType(Long chatId, String type, Date date) {
        ProductDto productDto = new ProductDto();
        productDto.setProductType(getWithoutEmoji(type));
        productDto.setProductDate(date);
        addingProducts.put(chatId, productDto);
    }

    public static void addProPay(Long chatId, String type) {
        addingProducts.get(chatId).setProductPay(type);
    }


    public static void addProName(Long chatId, String name) {
        addingProducts.get(chatId).setProductName(name);
    }

    public static void addProAmount(long chatId, BigDecimal amount) {
        addingProducts.get(chatId).setProductAmount(amount);
    }

    public static void addProPrice(long chatId, BigDecimal amount) {
        addingProducts.get(chatId).setProductPrice(amount);
    }

    public static String getProductDot(long chatId) {
        return addingProducts.get(chatId).toString();
    }

    private static String getWithoutEmoji(String text) {
        return EmojiParser.removeAllEmojis(text);
    }

    public static void deleteCurrentPro(long chatId) {
        addingProducts.remove(chatId);
    }

    public static ProductDto getCurrentProDto(long chatId) {
        return addingProducts.get(chatId);
    }
}
