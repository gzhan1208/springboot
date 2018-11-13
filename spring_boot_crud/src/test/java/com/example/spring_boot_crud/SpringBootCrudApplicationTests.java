package com.example.spring_boot_crud;

import com.example.spring_boot_crud.model.User;
import com.example.spring_boot_crud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void add() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setAge(16);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2010-10-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirth(date);
        userService.add(user);
    }

    @Test
    public void delete(){
        userService.delete(1);
    }
    @Test
    public void query(){
        List<User> list =  userService.queryUser();
        System.out.println(list);
    }
    @Test
    public void update(){
        try {
            User user = new User();
            user.setUid(userService.getUserById(2).getUid());
            user.setUsername("gz");
            user.setPassword("123");
            user.setAge(22);
            user.setBirth(new SimpleDateFormat("yy-MM-dd").parse("1999-11-11"));
            userService.update(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
