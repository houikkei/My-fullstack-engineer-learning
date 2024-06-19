package com.imooc.factory.computershop;

public class ShopAssistant {
    public Computer suggest(String purpose){
        Computer computer = null;
        if(purpose.equals("网站建设")){
            return new Server();
        }else if(purpose.equals("游戏")){
            return new Desktop();
        }else if(purpose.equals("办公")){
            return new Notebook();
        }else{
            return new Other();
        }
    }
}
