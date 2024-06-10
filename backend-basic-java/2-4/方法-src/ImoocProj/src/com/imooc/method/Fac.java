package com.imooc.method;

public class Fac {
    //求阶乘的方法
	public int fac(int n){
		int s=1;
		for(int i=1;i<=n;i++){
			s*=i;//s=s*i;
		}
		return s;
	}
	public static void main(String[] args) {
		Fac method=new Fac();
		int s=method.fac(3);
		System.out.println("3!="+s);

	}

}
