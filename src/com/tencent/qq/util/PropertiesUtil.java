package com.tencent.qq.util;
//读取client.properties中的相关信息

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
public static final String FILENAME="client.properties";
	private static Properties pro=new Properties();
	
	static{
		try {
			pro.load(Thread.currentThread()//当前线程
					.getContextClassLoader()//类的加载器
					.getResourceAsStream(FILENAME));//获得资源
		} catch (IOException e) {
			System.out.println("读取"+FILENAME+"异常！");
			e.printStackTrace();
		}
	}
	
	public static String readPro(String key)
	{
		return pro.getProperty(key);
	}	
}
