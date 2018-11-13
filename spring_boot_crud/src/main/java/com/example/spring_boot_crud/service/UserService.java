package com.example.spring_boot_crud.service;

import com.example.spring_boot_crud.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    /*删除*/
    public void delete(int uid);

    /*查询所有user*/
    public List<User> queryUser();

    /*修改user信息*/
    public void update(User user);

    /*添加user*/
    public void add(User user);

    /*通过id查找user*/
    public User getUserById(int uid);
}
