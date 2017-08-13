package cn.cloudbae;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class RandomTest {
	
	public static void main(String[] args) {
		System.out.println(randomString(20));
		System.out.println(currentTimeSeconds());
		System.out.println(toDate(1502442136 * 1000L));
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
