package com.imooc.test;

import com.imooc.tel.Camera;
import com.imooc.tel.FourthPhone;
import com.imooc.tel.INet;
import com.imooc.tel.IPhoto;
import com.imooc.tel.SmartWatch;

public class PhoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FourthPhone phone=new FourthPhone();
		phone.call();
		phone.message();
		phone.vedio();
		phone.music();
		phone.photo();
		phone.network();
		System.out.println("================");
		IPhoto ip=new FourthPhone();
		ip.photo();
		ip=new Camera();
		ip.photo();
		System.out.println("================");
		System.out.println(INet.TEMP);
		INet net=new SmartWatch();
		System.out.println(net.TEMP);//20?30?
		net.connection();
		INet.stop();
//		SmartWatch sw=new SmartWatch();
//		System.out.println(sw.TEMP);
		System.out.println("================");
		INet net2=new SmartWatch();
		net2.connection();
		IPhoto ip2=new SmartWatch();
		ip2.connection();
		System.out.println("================");
		INet net3=new FourthPhone();
		net3.connection();
		IPhoto ip3=new FourthPhone();
		ip3.connection();
	}

}
