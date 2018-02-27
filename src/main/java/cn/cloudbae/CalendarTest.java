package cn.cloudbae;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarTest {

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

}
