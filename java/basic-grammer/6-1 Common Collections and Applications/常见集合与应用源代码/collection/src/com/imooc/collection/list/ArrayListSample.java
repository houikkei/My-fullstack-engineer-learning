package com.imooc.collection.list;

import java.util.ArrayList;

public class ArrayListSample {
    public static void main(String[] args) {
        //实例化ArrayList集合
        ArrayList<String> bookList = new ArrayList<String>();
        bookList.add("三国演义"); //“ctrl+shift+回车”自动增加结尾分号
        bookList.add("水浒传");
        //bookList.add("三国演义");
        System.out.println(bookList);//快捷键：sout
        String bookName1 =  bookList.get(1);
        System.out.println(bookName1);

        //bookList.get(10); //Ctrl + / 注释当前行
        bookList.add(1,"红楼梦");
        System.out.println(bookList);

        //add方法返回值代表List集合是否发生变化
        boolean result1 = bookList.add("西游记");
        System.out.println("列表是否发生变化：" + result1);

        //set方法用于更新指定索引的数据，返回值是更新前的原数据
        String before = bookList.set(3,"西游记后传");
        System.out.println(before);
        System.out.println(bookList);

        //remove方法有两种形式
        //按数据删除，传入数据，返回是否删除成功的布尔类型
        boolean result2 = bookList.remove("西游记后传");
        System.out.println(result2);
        System.out.println(bookList);

        //按索引位置删除，返回被删除的数据
        String item = bookList.remove(0);
        System.out.println(item);
        System.out.println(bookList);

        //size方法用于获取List集合的总长度
        int count = bookList.size();
        System.out.println(count);
        //更新最后一个数据
        bookList.set(bookList.size() - 1, "测试数据");
        System.out.println(bookList);
        //删除最后一个数据
        bookList.remove(bookList.size() - 1);
        System.out.println(bookList);
    }
}








