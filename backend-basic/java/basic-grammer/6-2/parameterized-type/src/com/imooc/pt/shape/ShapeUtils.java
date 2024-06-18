package com.imooc.pt.shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeUtils {
    //通过extends确定类型的上限
    public void drawAll(List<? extends Shape> shapeList){
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }

    //通过super确定类型下限
    public void doSth(List<? super Rectangle> shapeList){
        System.out.println("");
    }

    public static void main(String[] args) {
        ShapeUtils utils = new ShapeUtils();
        List<Circle> circleList = new ArrayList();
        utils.drawAll(circleList);
        List<Square> list = new ArrayList<>();
        //utils.doSth(list);
    }
}
