package com.entrepriseName.service;


import com.entrepriseName.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.regex.*;
import java.util.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired()
    UserService userService;

    @Test
    public void getUserTestCount_ok() throws Exception {
        Assertions.assertEquals(1, userService.getUsers(1).size());
        Assertions.assertEquals(2, userService.getUsers(2).size());
        Assertions.assertEquals(3, userService.getUsers(3).size());

    }

    @Test
    public void getUserTestCount_ko(){
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            userService.getUsers(-1);
            //Code under test
        });

        Assertions.assertEquals("number of users is not valid!", thrown.getMessage());
    }


    @Test
    public void getUserTestCount_ko_exceptionNull() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            userService.getUsers(null);
            //Code under test
        });

        Assertions.assertEquals("number of users is null!", thrown.getMessage());
    }

    @Test
    public void getUserTestCount_ko_exceedMax(){
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            userService.getUsers(200000);
            //Code under test
        });

        Assertions.assertEquals("number of users exceeded the Max (100 000)!", thrown.getMessage());
    }


    @Test
    public void getUserTestAllEmail_ok() throws Exception {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        for(User user : userService.getUsers(3)){
            //Create instance of matcher
            Matcher matcher = pattern.matcher(user.getEmail());
            Assertions.assertTrue(matcher.matches());
        }
    }


    @Test
    public void getUserTestAllAttributes_notNull_ok() throws Exception {

        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getUsername()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getLastName()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getBirthDate()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getCity()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getCountry()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getAvatar()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getCompany()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getJobPosition()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getMobile()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getUsername()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getUsername()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getEmail()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getPassword()));
        userService.getUsers(3).forEach(user -> Assertions.assertNotNull(user.getRole()));
        userService.getUsers(3).forEach(user -> Assertions.assertTrue(user.getRole()=="admin"||user.getRole()=="user"));
    }

    @Test
    public void getUserTestPasswordStrength_ok() throws Exception {
        Pattern patten = Pattern.compile("[a-zA-Z0-9]{8,}");
        userService.getUsers(10).forEach(user -> Assertions.assertTrue(patten.matcher(user.getPassword()).find()));
    }

    @Test
    public void getUserTestRealNames_ok() throws Exception {
        Pattern patten = Pattern.compile("[a-zA-Z0-9]{8,}");
        userService.getUsers(10).forEach(user -> Assertions.assertTrue(patten.matcher(user.getPassword()).find()));
    }



}
