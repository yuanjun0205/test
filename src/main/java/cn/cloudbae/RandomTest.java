package cn.cloudbae;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class RandomTest {
	
	public static void main(String[] args) {
		System.out.println(randomString(24));
//		System.out.println(currentTimeSeconds());
//		System.out.println(toDate(1502442136 * 1000L));
//		System.out.println(randomInt());
	}
	
	
	private static String randomInt() {
		Integer x = random.nextInt(9999-1000+1) + 1000;//为变量赋随机值1000-9999
		return String.valueOf(x);
	}
	
	private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int)(System.currentTimeMillis() / 1000);
    }
    
    public static Date toDate(long seconds) {
    		return new Date(seconds);
    }
	
}
