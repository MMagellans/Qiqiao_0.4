package com.qiqiao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static String getShowTime(Date time1,Date time2) {
		if(time1 == null || time2 == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long d1 = time1.getTime();
		long d2 = time2.getTime();
		long sec = (d2-d1)/(1000);//得到秒
		long min = (d2-d1)/(1000*60);//得到分钟
		long hour = (d2-d1)/(1000*60*60);//得到小时
		long day = (d2-d1)/(1000*60*60*24);//得到天
		if(hour >= 72) {
			return sdf.format(time1);
		}else if(hour >= 24 && hour < 72) {
			return day+"天前";
		}else if(hour >=1 && hour < 24) {
			return hour+"小时前";
		}else if(hour < 1 && min >=1) {
			return min+"分钟前";
		}else if(min < 1 && sec >=1) {
			return sec+"秒前";
		}else
		return sdf1.format(time1);
	}
	
}
