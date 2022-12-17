package com.entrepriseName.controller;


import com.entrepriseName.entity.User;
import com.entrepriseName.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/generate")
    public List<User> generateUsersFile(@RequestParam Integer count) throws Exception {

        return userService.getUsers(count); // ex test

    }

//    @GetMapping("/generate/{count}")
//    public List<User> generateUsersFile_(@PathVariable("count") Integer county) throws Exception {
//        return userService.getUsers(county);
//
//    }
}
