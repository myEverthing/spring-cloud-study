package com.lwh.springcloud.test;

import com.lwh.springcloud.mapper.UserMapper;
import com.lwh.springcloud.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser()
    {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void insertTest()
    {
        User user = new User();
        user.setAge(18);
        user.setEmail("1103963111@qq.com");
        user.setName("魏紫慧");
        userMapper.insert(user);
    }

    @Test
    public void updateTest()
    {
        User user = new User();
        user.setId(6L);
        user.setName("吕伟豪");
        user.setAge(23);
        userMapper.updateById(user);
    }

    @Test
    public void testVersionSucceed()
    {
        User user = userMapper.selectById(1L);
        user.setName("lwh");
        user.setAge(32);

        User user1 = userMapper.selectById(1L);
        user1.setName("lwh11");
        user1.setAge(31);
        userMapper.updateById(user1);
        userMapper.updateById(user);
    }

    //批量查询
    @Test
    public void selectUsersByIdList()
    {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        users.forEach(System.out::println);
    }

    @Test
    public void testDelete()
    {
        userMapper.deleteById(1L);
    }

    @Test
    public void testSelect()
    {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
}
