package com.example.spring_boot_crud.mapper;

import com.example.spring_boot_crud.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
/*
 * @author gz
 * @Description 增删改查操作
 * @date 2018/11/8 19:47
 */
@Mapper
public interface UserMapper {
    /*
     * @author gz
     * @Description 删除user
     * @params [uid]
     */
    @Delete("delete from user_ where uid=#{uid}")
    public void delete(int uid);

    /*
     * @author gz
     * @Description 查询所有user
     * @params []
     */
    @Select("select * from user_")
    public List<User> queryUser();

    /*
     * @author gz
     * @Description 修改user信息
     * @params [user]
     */
    @Update("update user_ set " +
            "username=#{username}," +
            "password=#{password}," +
            "age=#{age}," +
            "birth=#{birth} where uid=#{uid}")
    public void update(User user);

    /*
     * @author gz
     * @Description 添加user
     * @params [user]
     */
    @Insert("insert into user_ values(null,#{username},#{password},#{age},#{birth})")
    public void add(User user);

    /*
     * @author gz
     * @Description 根据id查询user
     * @params [uid]
     */
    @Select("select * from user_ where uid = #{uid}")
    public User getUserById(int uid);
}
