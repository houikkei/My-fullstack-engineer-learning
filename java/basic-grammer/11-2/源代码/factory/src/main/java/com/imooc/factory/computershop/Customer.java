package com.imooc.factory.computershop;

public class Customer {
    public static void main(String[] args) {
        ShopAssistant shopAssistant = new ShopAssistant();
        Computer c = shopAssistant.suggest("游戏");
        System.out.println(c.describe());
    }
}
