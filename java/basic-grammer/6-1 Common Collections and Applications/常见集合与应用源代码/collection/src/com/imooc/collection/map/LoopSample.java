package com.imooc.collection.map;

import java.util.*;

public class LoopSample {
    //利用for循环遍历所有key，再获取value
    public void doForLoop(Map map){
        Set<String> keys =  map.keySet();
        for(String k : keys){
            System.out.println(k + ":" + map.get(k));
        }
    }
    //利用forEach方法+Lambda表达式循环遍历（推荐）
    public void doForEach(Map map){
        map.forEach((key,value) -> {
            System.out.println(key + ":" + value);
        });
    }

    //使用迭代器对象Iterator循环遍历每一个Entry对象，通过Entry对象获取键值对
    public void doIterator(Map map){
        Iterator<Map.Entry<String,Object>> itr = map.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<String,Object> entry = itr.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, Object> student = new LinkedHashMap<>();
        student.put("name", "张三");
        student.put("age", 18);
        student.put("height", 182);
        student.put("weight", 60);
        System.out.println(student);
        LoopSample loopSample = new LoopSample();
        loopSample.doForLoop(student);
        loopSample.doForEach(student);
        loopSample.doIterator(student);

    }
}
