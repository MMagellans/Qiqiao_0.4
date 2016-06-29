package com.qiqiao.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {
	
	private static int pageSize;
	private static String style = "default";
	static {
		InputStream in = Configuration.class.getClassLoader().getResourceAsStream("default.properties");
		Properties props = new Properties();
		try {
			// 1，读取配置文件
			props.load(in);

			// 2，初始化配置
			pageSize = Integer.parseInt(props.getProperty("pageSize"));
			style = props.getProperty("style");
			
			System.out.println("------- 配置文件加载完毕 ------");
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 设置论坛风格
	 * @param style
	 * @return
	 */
	public static String setStyle(String style) {
		Properties p = null;
		try {
			File f = new File(Configuration.class.getClassLoader().getResource("default.properties").getFile());
			System.out.println(f.getAbsolutePath());
			InputStream inputStream = new FileInputStream(f);  
			p = new Properties(); 
			p.load(inputStream);
			inputStream.close();
			OutputStream fos = new FileOutputStream(f);
			p.put("style", style);
			p.store(fos, null);
			return "success";
		} catch (Exception e1) {
			e1.printStackTrace();
			return e1.getMessage();
		}
	}
	
	/**
	 * 设置每页显示多少条
	 * @param pageSize
	 * @return
	 */
	public static String setPageSize(String pageSize) {
		Properties p = null;
		try {
			File f = new File(Configuration.class.getClassLoader().getResource("default.properties").getFile());
			//System.out.println(f.getAbsolutePath());
			InputStream inputStream = new FileInputStream(f);  
			p = new Properties(); 
			p.load(inputStream);
			inputStream.close();
			OutputStream fos = new FileOutputStream(f);
			p.put("pageSize", pageSize);
			p.store(fos, null);
			return "success";
		} catch (Exception e1) {
			e1.printStackTrace();
			return e1.getMessage();
		}
	}
	
	public static int getPageSize() {
		return pageSize;
	}
	
	public static String getStyle() {
		return style;
	}
}
