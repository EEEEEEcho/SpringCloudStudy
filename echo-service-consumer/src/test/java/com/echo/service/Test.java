package com.echo.service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Test {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.age = "18";
//        userInfo.money = 1000;
//        System.out.println(userInfo.getName());
//        System.out.println(userInfo.getPassword());
//        System.out.println(userInfo.getAge());
        Field[] declaredFields = userInfo.getClass().getDeclaredFields();
        for (Field field : declaredFields){
            field.setAccessible(true);
            Object fieldValue = null;
            try{
                fieldValue = field.get(userInfo);
                System.out.println("userInfo对象的字段为"+ field.getName() + "值为" + fieldValue);
                if(fieldValue == null){
                    System.out.println("有空值，设置默认值为1234");
                    field.set(userInfo,"1234");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("userInfo的age字段：" + userInfo.getAge());
        System.out.println("userInfo的password字段：" + userInfo.getPassword());
        System.out.println("userInfo的name字段：" + userInfo.getName());
    }
}
