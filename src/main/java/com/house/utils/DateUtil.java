package com.house.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtil {

	/**
	 * 
	* @Title: addMonth
	* @Description: TODO(月份加monthCount个月)
	* @param @param date 要增加月份的日期，格式为：yyyy-MM-dd
	* @param @param monthCount 增加的月数
	* @param @return    设定文件 返回增加后的日期
	* @return Date    返回类型
	* @throws
	 */
	public static Date addMonth(Date date,int monthCount){
		// 创建格式化格式  
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH,monthCount);//日期加monthCount个月
        return rightNow.getTime();
	}
	/**
	 * 
	* @Title: addDay
	* @Description: TODO(制定日期增加dayCount天)
	* @param @param date 要增加天数的日期，格式为：yyyy-MM-dd
	* @param @param dayCount 增加的天数
	* @param @return    设定文件 返回增加后的日期
	* @return Date    返回类型
	* @throws
	 */
	public static Date addDay(Date date,int dayCount){
		// 创建格式化格式  
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE,dayCount);//日期加monthCount个月
        return rightNow.getTime();
	}
	/**
	 * 将日期格式化为 yyyy-MM－dd 的字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToStringForYYYYMMDD(Date date){
		String value = null;
		if(date == null)return null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		value = format.format(date);
		
		return value;
	}
	/**
	 * 将日期格式化为 yyyy年MM月dd日 的字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date){
		if(date == null)return null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String datestr=sdf.format(date);
		return datestr;
	}
	
	/** 
     * 得到当前日期的月首 格式为：2009-08-01 
     */  
    public static String monthFist() {  
        Calendar localTime = Calendar.getInstance();  
        String strY = null;								// 日期属性：日  
        int x = localTime.get(Calendar.YEAR); 			// 日期属性：年  
        int y = localTime.get(Calendar.MONTH) + 1; 		// 日期属性：月  
        strY = y >= 10 ? String.valueOf(y) : ("0" + y); // 组合月份  
        return x + "-" + strY + "-01"; 					// 最后组合成yyyy-MM-dd形式字符串  
    }  
    
    
  
    /** 
     * 得到上个月月首 格式为：2009-08-01 
     */  
    public static String beforeMonth() {  
        Calendar localTime = Calendar.getInstance();  
        localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号  
        String strz = null;  
        int x = localTime.get(Calendar.YEAR); // 得到年  
        int y = localTime.get(Calendar.MONTH) + 1; // 得到月  
        strz = y >= 10 ? String.valueOf(y) : ("0" + y);  
        return x + "-" + strz + "-01";  
    }  
  
    /** 
     * 得到当前日期 格式为：2009-08-01 
     */  
    public static String curDate() {  
        // 分别根据日历时间提取当前年月日组合成字符串  
        Calendar localTime = Calendar.getInstance();  
        int x = localTime.get(Calendar.YEAR);  
        int y = localTime.get(Calendar.MONTH) + 1;  
        int z = localTime.get(Calendar.DAY_OF_MONTH);  
        return x + "-" + y + "-" + z;  
    } 
    
    
    /**
     * 指定的日期加一个月
     * @param strdate
     * @return
     */
    @SuppressWarnings("static-access")
	public static String addMonth(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        // 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        // 加减日期所用  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(2, 1); // 2表示月的加减，年代表1依次类推(周,天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    }
    
    
    /** 
     * 给定的日期减一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String subDay(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(5, -1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    }  
  
    /** 
     * 给定的日期加一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String addDay(String strdate) {  
        Date date = new Date(); // 构造一个日期型中间变量  
        String dateresult = null; // 返回的日期字符串  
        // 创建格式化格式  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        // 加减日期所用  
        GregorianCalendar gc = new GregorianCalendar();  
        try {  
            date = df.parse(strdate); // 将字符串格式化为日期型  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        gc.setTime(date); // 得到gc格式的时间  
        gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        // 把运算完的时间从新赋进对象  
        gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    } 
    
    
    /** 
     * 给定的日期加一天 格式为：2009-08-01 
     */  
    @SuppressWarnings("static-access")
	public static String addDay(Date date) {  
    	
    	if(date == null)return null;
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	String dateresult = null; 
    	GregorianCalendar gc = new GregorianCalendar(); 
        gc.setTime(date); 
        gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)  
        
        gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DATE));  
        // 在格式化回字符串时间  
        dateresult = df.format(gc.getTime());  
        return dateresult;  
    } 
    
    /**
     * 根据日期返回星期
     * @param date
     * @return
     */
    public static String getWeekByDate(Date date){
    	
    	if(date == null)return null;
    	String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};	
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	//SimpleDateFormat dateformat = new SimpleDateFormat("EEE");
		return week[c.get(Calendar.DAY_OF_WEEK)-1];
    	
    }
	/**
	 * 日期转换星期
	 * @param strDate
	 * @return
	 */
	public static String getDateToWeek(Date strDate) {

		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar c = Calendar.getInstance();
		c.setTime(strDate); // 当时间set 进calendar 里面
		int i = c.get(Calendar.DAY_OF_WEEK); // 取星期
		return dayNames[i - 1];
	}
    
    /**
     * 根据日期返回星期
     * @param date 格式2012－10－23
     * @return星期X
     */
    public static String getWeekByDate(String strDate){
    	
    	if(strDate == null)return null;
    	String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {
			 date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	//SimpleDateFormat dateformat = new SimpleDateFormat("EEE");
		return week[c.get(Calendar.DAY_OF_WEEK)-1];	
    }
    /**
     * 获取13位时间戳(1401433624722)
     * @return
     */
    public static final String getDateTime() {
    	Date date = new Date();
		long time = date.getTime();
		return String.valueOf(time) ;
		
	}
    /** 日期格式：yyyy */
    public static final String DY = "yyyy";

    /** 日期格式：yyyyMM */
    public static final String DM = "yyyyMM";

    /** 日期格式：yyyyMMdd */
    public static final String DS = "yyyyMMdd";

    /** 日期格式：yyyy-MM-dd */
    public static final String DW = "yyyy-MM-dd";

    /** 日期格式：yyyy-MM-dd HH:mm:ss */
    public static final String DL = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyyMMddHHmmss */
    public static final String DH = "yyyyMMddHHmmss";
    /**
     * 尝试解析时间。
     * <p/>
     * yyyyMMdd -> yyyy-MM-dd -> yyyy-MM-dd HH:mm:ss
     */
    public static final Date tryParseDate(String dateValue) {
        if (StringUtils.isEmpty(dateValue)) {
            return null;
        }

        Date value = null;
        try {
            value = toDateDS(dateValue);
            if (value == null) {
                value = toDateDW(dateValue);

                if (value == null) {
                    value = toDateDL(dateValue);
                }
            }
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 解析日期：yyyy
     */
    public static final Date toDateDY(String dateValue) {
        return toDate(dateValue, DY);
    }

    /**
     * 解析日期：yyyyMM
     */
    public static final Date toDateDM(String dateValue) {
        return toDate(dateValue, DM);
    }

    /**
     * 解析日期：yyyyMMdd
     */
    public static final Date toDateDS(String dateValue) {
        return toDate(dateValue, DS);
    }

    /**
     * 解析日期：yyyy-MM-dd
     */
    public static final Date toDateDW(String dateValue) {
        return toDate(dateValue, DW);
    }

    /**
     * 解析日期：yyyy-MM-dd HH:mm:ss
     */
    public static final Date toDateDL(String dateValue) {
        return toDate(dateValue, DL);
    }
    /**
     * 解析日期：yyyyMMddHHmmss
     */
    public static final Date toDateDH(String dateValue) {
        return toDate(dateValue, DH);
    }
    /**
     * 解析日期
     */
    public static final Date toDate(String dateValue, String format) {
        Date value = null;
        try {
            value = newDateFormat(format).parse(dateValue);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化
     */
    public static final String toString(Date date, String format) {
        String value = null;
        try {
            value = newDateFormat(format).format(date);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化：yyyy
     */
    public static final String toStringDY(Date date) {
        return toString(date, DY);
    }

    /**
     * 格式化：yyyyMM
     */
    public static final String toStringDM(Date date) {
        return toString(date, DM);
    }

    /**
     * 格式化：yyyyMMdd
     */
    public static final String toStringDS(Date date) {
        return toString(date, DS);
    }

    /**
     * 格式化：yyyy-MM-dd
     */
    public static final String toStringDW(Date date) {
        return toString(date, DW);
    }

    /**
     * 格式化：yyyy-MM-dd HH:mm:ss
     */
    public static final String toStringDL(Date date) {
        return toString(date, DL);
    }
    /**
     * 格式化：yyyyMMddHHmmss
     */
    public static final String toStringDH(Date date) {
        return toString(date, DH);
    }
    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault());
    }

    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format, Locale locale) {
        return new SimpleDateFormat(format, locale);
    }
    /**
     * 获取字符串日期截取十分钟：yyyy-MM-dd HH:m
     */
    public static final String getStringDate() {
        return toString(new Date(), "yyyy-MM-dd HH:mm").substring(0,15).trim();
    }
    public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 12; i++) {
			System.out.println(format.format(DateUtil.addDay(date,(i)*28)));
			System.out.println(i+1);
		}
	}
}
