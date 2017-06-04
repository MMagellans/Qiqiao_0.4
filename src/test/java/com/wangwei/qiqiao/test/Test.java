package com.wangwei.qiqiao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			
			System.out.println(format.parse("2013-06-09 07:45"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
