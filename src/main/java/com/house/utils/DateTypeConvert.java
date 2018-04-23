package com.house.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTypeConvert {
	/**
	 * 
	* @Title: strToOtherType
	* @Description: TODO(字符串转换为其他类型，转换后需要强转相应类型)
	* @param @param str 处理字符串
	* @param @param i 转换类型标志
	* @param @param dateFormat 转换为日期时的格式
	* @param @return    设定文件
	* @return Object    返回类型
	* @throws
	 */
	public static Object strToOtherType(String str,int i,String dateFormat){
		Object obj = str;
		try {
			switch (i) {
			case 1://转换为int
				obj = Integer.parseInt(str);
				break;
			case 2://转换为long
				obj = Long.parseLong(str);
				break;
			case 3://转换为short
				obj = Short.parseShort(str);
				break;
			case 4://转换为date
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				obj = format.parse(str);
				break;
			case 5://转换为BigDecimal
				obj = new BigDecimal(str);
				break;
			case 6://转换为double
				obj = new Double(str);
				break;
			case 7://转换为Byte
				obj = Byte.parseByte(str);
				break;
			default:
				obj = str;
				break;
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
