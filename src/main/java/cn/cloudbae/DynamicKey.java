package cn.cloudbae;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DynamicKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String r = generateCheckKey();
		System.out.println(r);
		System.out.println(tmpPageCheckKey(r));
	}
	
	/**
		1、以日期和小时为基础，进行加密运算，取值范围：小时为00~23点，日期为01~31。
		2、KEY的格式为{s1}{o1}{s2}{o2}{s3}{o3}{s4}{o4}，共8位字符。
		3、关于s1~s4：
		    日期为01-10号时，为a,b,c,d.
		    日期为11-20号时，为e,f,g,h.
		    日期为21-31号时，为i,j,k,l.
		4、o1为小时的个位数
		5、o2为小时的十位数的数字键+shift键打出的特殊字符。
		4、o3为日期的个位数
		5、o4为日期的十位数的数字键+shift键打出的特殊字符。
		
		例子：如现在是23日15点，那么KEY就是：i5j!k3l@
		
		再过给一个小时后，KEY变成i6j!k3l@
		
		i2j!k2l@
     */
    public static boolean tmpPageCheckKey(String str){
	    	Date now=new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd-HH");
	    	String[] strTime=sdf.format(now).split("-");
	    	int day=Integer.parseInt(strTime[0]);
	    	String ss;
	    	if(day>=1&&day<=10){
	    		ss="a,b,c,d";
	    	}else if(day>=11&&day<=20){
	    		ss="e,f,g,h";
	    	}else{
	    		ss="i,j,k,l";
	    	}
	    	String[] s=ss.split(",");
	    	char o1=strTime[1].charAt(1);
	    	char o2=strTime[1].charAt(0);
	    	char o3=strTime[0].charAt(1);
	    	char o4=strTime[0].charAt(0);
	    	switch(o2){
	    		case '0':o2=')';break;
	    		case '1':o2='!';break;
	    		case '2':o2='@';break;
	    	}
	    	switch(o4){
			case '0':o4=')';break;
			case '1':o4='!';break;
			case '2':o4='@';break;
			case '3':o4='#';break;
	    	}
	    	StringBuffer finalKey=new StringBuffer();
	    	finalKey.append(s[0]).append(o1).append(s[1]).append(o2)
	    		.append(s[2]).append(o3).append(s[3]).append(o4);
	    	if(finalKey.toString().equals(str)){
	    		return true;
	    	}
	    	return false;
    }

    public static String generateCheckKey(){
	    	Date now=new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd-HH");
	    	String[] strTime=sdf.format(now).split("-");
	    	int day=Integer.parseInt(strTime[0]);
	    	String ss;
	    	if(day>=1&&day<=10){
	    		ss="a,b,c,d";
	    	}else if(day>=11&&day<=20){
	    		ss="e,f,g,h";
	    	}else{
	    		ss="i,j,k,l";
	    	}
	    	String[] s=ss.split(",");
	    	char o1=strTime[1].charAt(1);
	    	char o2=strTime[1].charAt(0);
	    	char o3=strTime[0].charAt(1);
	    	char o4=strTime[0].charAt(0);
	    	switch(o2){
	    		case '0':o2=')';break;
	    		case '1':o2='!';break;
	    		case '2':o2='@';break;
	    	}
	    	switch(o4){
			case '0':o4=')';break;
			case '1':o4='!';break;
			case '2':o4='@';break;
			case '3':o4='#';break;
	    	}
	    	StringBuffer finalKey=new StringBuffer();
	    	finalKey.append(s[0]).append(o1).append(s[1]).append(o2)
	    		.append(s[2]).append(o3).append(s[3]).append(o4);
	    	return finalKey.toString();
	}
}
