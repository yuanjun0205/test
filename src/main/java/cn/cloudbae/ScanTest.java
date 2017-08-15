package cn.cloudbae;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class ScanTest {
	
	public static void main(String[] arge) throws Exception{
		//Scanner s = new Scanner("123 asda bf 12 123 nh l,sf.fl ...adafafa    lda");
		//s.useDelimiter(" |,|\\.");
		InputStream f = new FileInputStream("/Users/yuanjun/test.txt");
		Scanner s = new Scanner(f,"UTF-8");
		s.useDelimiter("\\\\A");
		while (s.hasNext()) {   
	       System.out.println(s.next());   
		}
		s.close();
		
	}
	
}
