package com.imooc.factory.computershop;

public class Server implements Computer{
    @Override
    public String describe() {
        return "Intel Xeon32核64线程 / 256G内存 / 高性能塔式服务器";
    }
}
