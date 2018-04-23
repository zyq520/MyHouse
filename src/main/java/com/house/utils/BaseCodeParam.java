package com.house.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * 
* @ClassName: BaseCodeParam
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 庄友权
* @date 2016年9月8日 上午10:35:48
*
 */
public class BaseCodeParam {

	public static String get(String name){
		Properties prop = new Properties();
		try {
			Reader inputStream = new InputStreamReader(BaseCodeParam.class.getClassLoader().getResourceAsStream("system.properties"),"UTF-8");
			prop.load(inputStream);
			return prop.getProperty(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
