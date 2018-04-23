package com.house.utils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {

	
	/**
	 * 获取客户端域名
	 * @param request
	 * @return
	 */
	public static String getDoMain(HttpServletRequest request) {
		//String strDoMain=request.getRequestURL().toString();//获取URL整串地址
		String strDoMain=request.getServerName().toString();//获取域名

		return strDoMain.toString();
		
	}
	/**
	 * 设置头信息页面不缓存
	 * @param response
	 */
	public static void setHeader(HttpServletResponse response) {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Server", "PayBank V1.0");
	}
	/**
	 * 获取完整URL 方法 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestURL(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String url = "";
		url = request.getContextPath();
		url = url + request.getServletPath();

		Enumeration names = request.getParameterNames();
		int i = 0;
		if (!"".equals(request.getQueryString())
				|| request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}

		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}
				url = url + name + "=" + value;
				try {
					// java.net.URLEncoder.encode(url, "ISO-8859");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			// String enUrl = java.net.URLEncoder.encode(url, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return url;
	}

	/**
	 * 获取客户端IP
	 * @param request IP地址
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String params) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + params;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段

			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			result=JsonUtil.toString(in.toString());
			/*String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}*/
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			//Https链接
			//HttpsURLConnection conn=(HttpsURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			result=in.readLine();
			/*
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}*/
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 向指定Https发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendHttps(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			//URLConnection conn = realUrl.openConnection();
			//Https链接
			HttpsURLConnection conn=(HttpsURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			result=in.readLine();
			
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	   /**
	    * 
	    * @param urlAll:请求接口
	    * @param charset:字符编码
	    * @return 返回json结果
	    */
	   public static String getHttp(String urlAll,String charset){
		   BufferedReader reader = null;
		   String result = null;
		   //StringBuffer sbf = new StringBuffer();
		   String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";//模拟浏览器
		   try {
			   URL url = new URL(urlAll);
			   HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setReadTimeout(30000);
			   connection.setConnectTimeout(30000);
			   connection.setRequestProperty("User-agent",userAgent);
			   connection.connect();
			   InputStream is = connection.getInputStream();
			   reader = new BufferedReader(new InputStreamReader(is, charset));
			   result=reader.readLine();
			  //JSONObject jsonObj = JSONObject.fromObject(reader);
				//result=jsonObj.toString();
				/*String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
					sbf.append("\r\n");
				}*/
				reader.close();
				//result = sbf.toString();
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		   return result;
	   }
	/**
	 * 生成对帐订单号
	 * 
	 * @return 订单号(14014357636233798)
	 */
	public static String getOrderDZ() {
		String orderNO = DateUtil.getDateTime() + getRandom4();
		return orderNO;
	}

	/**
	 * 生成正常订单20位
	 * 
	 * @return 订单号(20140530154243033740)
	 */
	public static String getOrderNO() {
		String orderNO = DateUtil.toStringDH(new Date())
				+ getRandomInt(6);
		return orderNO;
	}
	/**
	 * 获取随机整数(0~9).
	 * @param 随机整数的长度
	 * @return 随机整数
	 */
	public static String getRandomInt(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int c = random.nextInt(10);
			String strRand=Integer.toString(c);
			sb.append(strRand);
		}
		return sb.toString();
	}
	/**
	 * 获取5位随机数10000-99999之间的数
	 * @return
	 */
	public static String getRandom() {
		return String.valueOf(Math.round(Math.random()*89999+10000));
	}
	/**
	 * 获取4位随机数10000-99999之间的数
	 * @return
	 */
	public static String getRandom4() {
		return String.valueOf(Math.round(Math.random()*8999+1000));
	}
	/**
	 * 获取随机数(0~9,a~z).
	 * @param 随机数的长度
	 * @return 随机数
	 */
	public static String getRandomStr(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		String strRand = null;
		for (int i = 0; i < len; i++) {
			// 得到随机产生的验证码数字
			while (i >= 0) {
				int c = random.nextInt(123);
				if ((c <= '9' && c >= '0') ||(c <= 'z' && c >= 'a')) {
					strRand = String.valueOf((char) c);
					break;
				}
			}
			// 产生四个随机数字组合在一起
			sb.append(strRand);
		}
		return sb.toString();
	}
	/**
	   * 判断是否为整数
	   *
	   * @param str 传入的字符串
	   * @return 是整数返回true,否则返回false
	   */
	public static boolean isInteger(String str) {
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	}
	
	/**
	   * 判断是否为浮点数，包括double和float
	   *
	   * @param str 传入的字符串
	   * @return 是浮点数返回true,否则返回false
	   */
	public static boolean isDouble(String str) {
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * 检测手机格式
	 * @param mobiles 手机号码
	 * @return true/false
	 */
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
		
	}
	/**
	 * 防止SQL注入,验证字符类型不能包含特殊字
	 * @param string
	 * @return
	 */
    public static boolean checkNonlicetCharacters(String string) {
        boolean flag = true;
        // 不许出现单引号
        if (string != null && string.indexOf("'") > 0) {
            flag = false;
        }

        return flag;
    }
    /**
     * 防止SQL注入
     * @param string
     * @return
     */
    public static String getValidSQLPara(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        string=string.replaceAll("'", "");
        string=string.replaceAll(" ", "");
        return string;
    }
    public static String getValues(HttpServletRequest request,String values) {
    	String reString=request.getParameter(values);
    	if(reString!=null && reString.length()> 0){
    		reString=getValidSQLPara(reString);
    	}else{
    		reString="";
    	}
    	/*if(null != request.getParameter(values) && request.getParameter(values).toString()!="")
		{
    		reString=getValidSQLPara(request.getParameter(values));
		}*/
		return reString;
	}
}
