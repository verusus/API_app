package com.entrepriseName.service;

import com.entrepriseName.entity.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    public List<User> getUsers(Integer count) throws Exception {
        if (count == null){
            throw new Exception("number of users is null!");
        }
        if (count <= 0){
            throw new Exception("number of users is not valid!");
        }
        if (count > 100000){
            throw new Exception("number of users exceeded the Max (100 000)!");
        }

        List<User> users = new ArrayList();
        Faker faker = new Faker();
        User user;

        for(int i = 0; i < count; i++){
            user = new User();

            user.setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setBirthDate(faker.date().birthday())
            .setCity(faker.address().city())
            .setCountry(faker.address().country())
            .setAvatar(faker.internet().avatar())
            .setCompany(faker.company().name())
            .setJobPosition(faker.job().position())
            .setMobile(faker.phoneNumber().phoneNumber())
            .setUsername(faker.name().username())
            .setEmail(faker.internet().emailAddress())
            .setPassword(faker.internet().password(8,12,true, false, true))
            .setRole(faker.options().nextElement(Arrays.asList("admin", "user")));

            users.add(user);
        }
        return users;
    }
}
