package com.imooc.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSorter {
    class SampleComparator implements Comparator<Integer>{
        //70 90 30 50
        // 结果>0，则交换位置
        // 结果=0或小于0，则位置不变
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    public List<Integer> sort(List<Integer> list){
        //利用Collections.sort方法实现对List、Set进行排序
        Collections.sort(list,new SampleComparator());
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(70);
        list.add(90);
        list.add(30);
        list.add(50);
        System.out.println(list);
        ListSorter listSorter = new ListSorter();
        List<Integer> list1 = listSorter.sort(list);
        System.out.println(list);

    }
}
