package com.example.dailyexpenses.controller;


import com.example.dailyexpenses.entity.ProductEntity;
import com.example.dailyexpenses.entity.UserEntity;
import com.example.dailyexpenses.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    private static UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        UserController.userRepository = userRepository;
    }

    public static void addUser(UserEntity user) {
        userRepository.save(user);
    }

    public static boolean checkUser(long chatId) {
        Optional<UserEntity> byUserChatId = userRepository.findByUserChatId(chatId);
        return byUserChatId.isPresent();
    }

    public static UserEntity getUser(long chatId) {
        Optional<UserEntity> byUserChatId = userRepository.findByUserChatId(chatId);
        return byUserChatId.orElse(null);
    }

    public static long getUsersNum(){
        return userRepository.count();
    }

    public static void setProToUser(long chatId, ProductEntity product){
        getUser(chatId).setProducts(List.of(product));
    }

}
