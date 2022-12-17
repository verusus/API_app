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
        Pattern patten = Pattern.compile("^([A-Za-z]{2,}\\s?)+$"); // no numbers || special characters are allowed
        // hamid, jr hamid,
//        Assertions.assertTrue(patten.matcher("     ").find());
        userService.getUsers(10).forEach(user -> Assertions.assertTrue(patten.matcher(user.getFirstName()).find()));
        userService.getUsers(10).forEach(user -> Assertions.assertTrue(patten.matcher(user.getLastName()).find()));
    }
    @Test
    public void getUserTestRegex_ok() throws Exception {
        Pattern patten = Pattern.compile("^.$"); //^=^=Début de ligne , $=Fin de ligne, .=N'importe quel caractère
        Assertions.assertTrue(patten.matcher("A").find());
        Assertions.assertTrue(patten.matcher("2").find());
        Assertions.assertTrue(patten.matcher("&").find());
        Assertions.assertTrue(patten.matcher("a").find());

        patten = Pattern.compile("^.{3}$"); //{}=nbr d'occurances
        Assertions.assertTrue(patten.matcher("Aa1").find());
        Assertions.assertTrue(patten.matcher("_$y").find());

        patten = Pattern.compile("^.{3,5}$"); //
        Assertions.assertTrue(patten.matcher("Aa1").find());
        Assertions.assertTrue(patten.matcher("_$y2").find());
        Assertions.assertTrue(patten.matcher("_$y6k").find());
        Assertions.assertFalse(patten.matcher("1").find());

        patten = Pattern.compile("^.{7,}$"); //{}=nbr d'occurances {}=Quantificateur
        Assertions.assertTrue(patten.matcher("Aa1aldòfdf9434L").find());
        Assertions.assertFalse(patten.matcher("_$y").find());

        patten = Pattern.compile("^.{0,5}$"); //au max 5 char
        Assertions.assertFalse(patten.matcher("Aa1aldòfdf9434L").find());
        Assertions.assertTrue(patten.matcher("_$y").find());

        patten = Pattern.compile("^.+$"); //+=un ou plusieurs fois l'expression qui précède
        Assertions.assertTrue(patten.matcher("Aa1aldòfdf9434L").find());
        Assertions.assertTrue(patten.matcher("_$y").find());
        Assertions.assertFalse(patten.matcher("").find());

        patten = Pattern.compile("^.*$"); //*= 0 ou plusieurs fois l'expression qui précède
        Assertions.assertTrue(patten.matcher("Aa1aldòfdf9434L").find());
        Assertions.assertTrue(patten.matcher("_$y").find());
        Assertions.assertTrue(patten.matcher("").find());

        patten = Pattern.compile("^.?$"); //?=0 ou une fois l'expression qui précède
        Assertions.assertTrue(patten.matcher("").find());
        Assertions.assertTrue(patten.matcher("a").find());
        Assertions.assertFalse(patten.matcher("aa").find());

        patten = Pattern.compile("^.\\+?$"); //\=le caractère n'est pas considéré comme méta-caractère
        Assertions.assertTrue(patten.matcher("@+").find());
        Assertions.assertTrue(patten.matcher("1").find());

        patten = Pattern.compile("^[a,x,z]+@$"); //[]=définie un ensemble de caractères à l'intérieur d'une expre et ,= une ou plusieurs
        Assertions.assertTrue(patten.matcher("a@").find());
        Assertions.assertTrue(patten.matcher("axz@").find());
        Assertions.assertTrue(patten.matcher("axxz@").find());
        Assertions.assertTrue(patten.matcher("aaaxz@").find());
        Assertions.assertFalse(patten.matcher("aaabxz@").find());
        Assertions.assertFalse(patten.matcher("@aaabxz@").find());

        patten = Pattern.compile("^[a,x,z]*[@|!]?$"); //|= Opérateur OU
        Assertions.assertTrue(patten.matcher("").find());
        Assertions.assertTrue(patten.matcher("a").find());
        Assertions.assertTrue(patten.matcher("a!").find());
        Assertions.assertTrue(patten.matcher("a@").find());
        Assertions.assertTrue(patten.matcher("axz@").find());
        Assertions.assertTrue(patten.matcher("axxz@").find());
        Assertions.assertTrue(patten.matcher("aaaxz!").find());
        Assertions.assertTrue(patten.matcher("!").find());
        Assertions.assertTrue(patten.matcher("@").find());

        patten = Pattern.compile("^[B-D]+@{1}$"); //
        Assertions.assertTrue(patten.matcher("B@").find());

        patten = Pattern.compile("^\\d*$"); //\d=Un chiffre: [0-9]
        Assertions.assertTrue(patten.matcher("").find());
        Assertions.assertTrue(patten.matcher("0").find());
        Assertions.assertTrue(patten.matcher("00").find());
        Assertions.assertTrue(patten.matcher("12").find());
        Assertions.assertTrue(patten.matcher("123").find());

        patten = Pattern.compile("^\\D*$"); //\D=Tout caractère sauf les chiffres [^0-9]
        Assertions.assertTrue(patten.matcher("").find());
        Assertions.assertTrue(patten.matcher("$").find());
        Assertions.assertFalse(patten.matcher("A1#").find());

        patten = Pattern.compile("^[^12357]+$"); //\D=Tout caractère sauf chiffres premiers [1 2 3 5 7]
        Assertions.assertTrue(patten.matcher("a").find());
        Assertions.assertTrue(patten.matcher("@").find());
        Assertions.assertFalse(patten.matcher("2").find());

        patten = Pattern.compile("^\\s$"); //\s=Un caractère blanc: retour à la ligne, espace: [ \t\n\x0B\f\r]
        Assertions.assertTrue(patten.matcher("\n").find());
        Assertions.assertTrue(patten.matcher(" ").find());
        Assertions.assertTrue(patten.matcher("\t").find());
        Assertions.assertTrue(patten.matcher("\r").find());

        patten = Pattern.compile("^\\S$"); //\S=Un caractère non blanc: [^\s]
        Assertions.assertTrue(patten.matcher("\n").find());
        Assertions.assertTrue(patten.matcher(" ").find());
        Assertions.assertTrue(patten.matcher("\t").find());
        Assertions.assertTrue(patten.matcher("\r").find());

        patten = Pattern.compile("^\\S$"); //\w=Un caractère de mot: [a-zA-Z_0-9]  \W=Un caractère qui n'est pas un mot: [^\w]
        Assertions.assertTrue(patten.matcher("\n").find());
        Assertions.assertTrue(patten.matcher(" ").find());
        Assertions.assertTrue(patten.matcher("\t").find());
        Assertions.assertTrue(patten.matcher("\r").find());

        patten = Pattern.compile("^\\S$"); //\W=Un caractère qui n'est pas un mot: [^\w]
        Assertions.assertTrue(patten.matcher("\n").find());
        Assertions.assertTrue(patten.matcher(" ").find());
        Assertions.assertTrue(patten.matcher("\t").find());
        Assertions.assertTrue(patten.matcher("\r").find());



//        patten = Pattern.compile("^[0-9]+$"); //naturel relatif
//        Assertions.assertTrue(patten.matcher("0").find());
//        Assertions.assertFalse(patten.matcher("00").find());
//        Assertions.assertTrue(patten.matcher("192").find());
//        Assertions.assertTrue(patten.matcher("-31").find());
//        Assertions.assertFalse(patten.matcher("-0").find());
//        Assertions.assertTrue(patten.matcher("-30").find());
//        Assertions.assertFalse(patten.matcher("-03").find());


    }





}
