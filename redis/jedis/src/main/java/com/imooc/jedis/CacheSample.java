package com.imooc.jedis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CacheSample {
    public CacheSample(){
        Jedis jedis = new Jedis("192.168.132.144");
        try {
            List<Goods> goodsList = new ArrayList<Goods>();
            goodsList.add(new Goods(8818, "红富士苹果", "", 3.5f));
            goodsList.add(new Goods(8819, "进口脐橙", "", 5f));
            goodsList.add(new Goods(8820, "进口香蕉", "", 25f));
            jedis.auth("12345");
            jedis.select(3);
            for (Goods goods : goodsList) {
                String json = JSON.toJSONString(goods);
                System.out.println(json);
                String key = "goods:" + goods.getGoodsId();
                jedis.set(key , json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        new CacheSample();
        System.out.printf("请输入要查询的商品编号：");
        String goodsId = new Scanner(System.in).next();
        Jedis jedis = new Jedis("192.168.132.144");
        try{
            jedis.auth("12345");
            jedis.select(3);
            String key = "goods:" + goodsId;
            if(jedis.exists(key)){
                String json = jedis.get(key);
                System.out.println(json);
                Goods g = JSON.parseObject(json, Goods.class);
                System.out.println(g.getGoodsName());
                System.out.println(g.getPrice());
            }else{
                System.out.println("您输入的商品编号不存在，请重新输入！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }
}
