package com.json;

import com.alibaba.fastjson.JSON;
import com.service.UserService;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-10:38
 */
public class FastJsonDemo {
    public static void main(String[] args) {
        final User user = new User(1, "本当迷", "123");

        // 将对象转为JSON
        final String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        // 将JSON转为对象
        final User user1 = JSON.parseObject(jsonString, User.class);
        System.out.println(user1);


    }
}
