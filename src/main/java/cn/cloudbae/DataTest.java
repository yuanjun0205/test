package cn.cloudbae;

import java.util.Date;

public class DataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now1 = new Date();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date now2 = new Date();
		long l = now2.getTime() - now1.getTime();
		System.out.println(l);
	}

}
