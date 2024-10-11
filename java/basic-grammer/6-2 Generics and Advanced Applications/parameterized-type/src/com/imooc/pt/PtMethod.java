package com.imooc.pt;

import java.util.ArrayList;
import java.util.List;

public class PtMethod {
    //泛型方法，要求返回值类型与参数类型保持一致
    public <T> List<T> transferToList(T[] array){
        List<T> list = new ArrayList();
        for(T item : array){
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        PtMethod ptMethod = new PtMethod();
        String[] array = new String[]{"A", "B", "C", "D", "E"};
        List<String> list = ptMethod.transferToList(array);
        System.out.println(list);

    }
}
