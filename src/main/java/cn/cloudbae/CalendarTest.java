package cn.cloudbae;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {

	/*
	public static void main(String[] args) {
		//获取前一个月第一天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        //String firstDay = sdf.format(calendar1.getTime());
        //获取前一个月最后一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        //String lastDay = sdf.format(calendar2.getTime());
        
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH) + 1;
        
        System.out.println(year);
        System.out.println(month);
        
        List<Date> lDate = new ArrayList<Date>();  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        calBegin.setTime(calendar1.getTime());  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        calEnd.setTime(calendar2.getTime());  
        // 测试此日期是否在指定日期之后  
        while (calendar2.getTime().after(calBegin.getTime()))  
        {  
	         // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
        		lDate.add(calBegin.getTime());
        		calBegin.add(Calendar.DAY_OF_MONTH, 1);  
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //文件后缀
        List<String> fileSuffixs = new ArrayList<String>();
        for(Date d:lDate) {
        		fileSuffixs.add(sdf.format(d.getTime()));
        }
        //文件后缀
        for(String sFileSuffix:fileSuffixs) {
        		System.out.println(sFileSuffix);
        }
	}
	*/
	
	public static void main(String[] args) throws InterruptedException {
		//Calendar calendar = new GregorianCalendar();
		Calendar calendar = Calendar.getInstance();
		System.out.printf("%1$tF %<tT%n", calendar);
		byte[] bytes = calendar2Bytes(calendar);
		System.out.println("bytes.length = " + bytes.length);
		calendar = bytes2Calendar(bytes);
		System.out.printf("%1$tF %<tT%n", calendar);
	}

	public static byte[] calendar2Bytes(Calendar calendar) {
		int time = (int)(calendar.getTimeInMillis()/1000);
		byte[] bytes = new byte[4];
		for(int i = bytes.length - 1; i >= 0; i--) {
			bytes[i] = (byte)(time & 0xFF);
			time >>= 8;
		}
		return bytes;
	}

	public static Calendar bytes2Calendar(byte[] bytes) {
		int time = (bytes[0] << 24) & 0xFF000000 | (bytes[1] << 16) & 0xFF0000 | (bytes[2] << 8) & 0xFF00 | (bytes[3]) & 0xFF;
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(time * 1000L);
		return calendar;
	}

}
