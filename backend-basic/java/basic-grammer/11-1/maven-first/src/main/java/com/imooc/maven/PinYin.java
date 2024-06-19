package com.imooc.maven;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYin {
    public static void main(String[] args) {
        char c = '重';
        String[] arr = PinyinHelper.toHanyuPinyinStringArray(c);
        for (String py : arr){
            System.out.println(py);
        }
    }
}
