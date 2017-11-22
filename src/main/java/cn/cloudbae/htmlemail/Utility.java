package cn.cloudbae.htmlemail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;

public class Utility {
	private static SecureRandom random = new SecureRandom();
	
	/**
     * URLEncoder charset
     */
    public static final String CHARSET = "UTF-8";

    private static final String REQUEST_TIME_KEY = "cloudbae-Request-Timestamp";

    public static int CONNECT_TIMEOUT = 30;
    public static int READ_TIMEOUT = 80;

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }
    
    /**
     * @param url
     * @param query
     * @param apiKey
     * @return
     * @throws UnsupportedEncodingException 
     * @throws IOException
     * @throws APIConnectionException
     */
    public static java.net.HttpURLConnection createPostConnection(String url, String eventStr, String sign) throws UnsupportedEncodingException, IOException {
        java.net.HttpURLConnection conn = createCloudbaeConnection(url);

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", String.format("application/json;charset=%s", CHARSET));

        if (sign != null) {
            conn.setRequestProperty("X-Cloudbae-Signature", sign);
        }

        OutputStream output = null;
        try {
            output = conn.getOutputStream();
            output.write(eventStr.getBytes(CHARSET));
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return conn;
    }
    
    /**
     * @param url
     * @param query
     * @param apiKey
     * @return
     * @throws UnsupportedEncodingException 
     * @throws IOException
     * @throws APIConnectionException
     */
    public static java.net.HttpURLConnection createPostConnection(String url, Object object) throws UnsupportedEncodingException, IOException {
        java.net.HttpURLConnection conn = createCloudbaeConnection(url);

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/octet-stream");

        OutputStream output = null;
        try {
            output = conn.getOutputStream();
            output.write(ObjectToByte(object));
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return conn;
    }
    
    
    /**
     * @param url
     * @param apiKey
     * @return
     * @throws IOException
     */
    private static java.net.HttpURLConnection createCloudbaeConnection(String url) throws IOException {
        URL cloudbaeURL = new URL(url);
        HttpURLConnection conn;
        if (cloudbaeURL.getProtocol().equals("https")) {
            conn = (HttpsURLConnection) cloudbaeURL.openConnection();
        } else {
            conn = (HttpURLConnection) cloudbaeURL.openConnection();
        }
        conn.setRequestProperty(REQUEST_TIME_KEY, currentTimeString());

        conn.setConnectTimeout(CONNECT_TIMEOUT * 1000);
        conn.setReadTimeout(READ_TIMEOUT * 1000);
        conn.setUseCaches(false);

        return conn;
    }
    
    private static String currentTimeString() {
        Integer requestTime = (int) (System.currentTimeMillis() / 1000);
        return requestTime.toString();
    }
    

    //From HBase Addressing.Java
    @SuppressWarnings("unused")
	public static String getIpAddress(){
        // Before we connect somewhere, we cannot be sure about what we'd be bound to; however,
        // we only connect when the message where client ID is, is long constructed. Thus,
        // just use whichever IP address we can find.
    		String ipAddr = "unknow";
    		try {
    			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
    	        while (interfaces.hasMoreElements()) {
    	          NetworkInterface current = interfaces.nextElement();
    	          if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue;
    	          Enumeration<InetAddress> addresses = current.getInetAddresses();
    	          while (addresses.hasMoreElements()) {
    	            InetAddress addr = addresses.nextElement();
    	            if (addr.isLoopbackAddress()) continue;
    	            ipAddr = addr.getHostName()+":"+addr.getHostAddress();
    	          }
    	        }
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		return ipAddr;
      }

    /**
     * 对象转byte
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {  
        byte[] bytes = null;  
        try {  
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
      
            bytes = bo.toByteArray();  
      
            bo.close();  
            oo.close();  
        } catch (Exception e) {  
            System.out.println("translation" + e.getMessage());  
            e.printStackTrace();  
        }  
        return bytes;  
    } 
    
    /**
     * byte转对象
     * @param bytes
     * @return
     */
    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }
}
