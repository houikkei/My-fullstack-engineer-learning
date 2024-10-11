package com.imooc.method;

import java.util.Scanner;

public class MethodDemo {
	// 打印输出星号的方法
	public void printStar() {
		System.out.println("*****************************");
	}

	public static void main(String[] args) {
		//声明一个MethodDemo类的对象myMethodDemo
		MethodDemo myMethodDemo=new MethodDemo();
		//使用对象名.方法名()去调用方法
		myMethodDemo.printStar();
		System.out.println("欢迎来到Java的世界！");
		myMethodDemo.printStar();
	}
}
