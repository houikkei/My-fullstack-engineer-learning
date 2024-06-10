package com.imooc.collection.map;

import java.util.HashMap;

public class HashMapSample {
    public static void main(String[] args) {
        //实例化HashMap，HashMap同样存储在java.util包下
        //泛型可以只写在左边，右边泛型可以省略
        HashMap<String, Object> student = new HashMap<>();
        //put方法向Map放入键值对
        student.put("name", "张三");
        //多次为同一个key赋值，新的value会覆盖旧value，同时将旧value返回
        String name = (String)student.put("name", "李四");
        System.out.println(name + "已被替换为李四");
        //Map可以存储多组键值对，且value可以是不同类型
        student.put("age", 18);
        student.put("height", 182);
        student.put("weight", 60);
        System.out.println(student);

        //利用get方法获取指定key的value
        String n = (String)student.get("name");
        System.out.println(n);

        //containsKey用于判断传入的key是否存在
        boolean r1 =  student.containsKey("name");
        System.out.println(r1);
        //containsValue用于判断传入的value是否存在
        boolean r2 =  student.containsValue(61);
        System.out.println(r2);
        //size方法返回当前键值对的总数
        int count = student.size();
        System.out.println(count);
        //remove方法将指定的键值对删除，并将value返回
        Integer w = (Integer)student.remove("weight");
        System.out.println("weight项已被移除,其值为:" + w);
        System.out.println(student);
    }
}
