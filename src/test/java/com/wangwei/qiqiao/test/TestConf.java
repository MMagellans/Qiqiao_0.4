package com.wangwei.qiqiao.test;

import org.junit.Test;

import com.qiqiao.cfg.Configuration;


public class TestConf {
	
	@Test
	public void testGetStyle() throws Exception {
		System.out.println(Configuration.getStyle());
		Configuration.setStyle("red");
	}
}
