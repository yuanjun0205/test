package cn.cloudbae.htmlemail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cn.cloudbae.model.mail.HtmlMailMessage;


public class SendHtmlMail {

	public static void main(String[] args) {
		HtmlMailMessage r = new HtmlMailMessage();
		r.setSenderId("abc123");
		r.setSecretKey("secretKey");
		r.setSubject("测试带内联图片与附件的邮件");
		r.setHtmlText("<html><head></head><body><h1>此邮件由消息中心统一发送</h1><img src=\"cid:inner1\"/></img></body></html>");
		ArrayList<String> receiptToList = new ArrayList<String>();
		receiptToList.add("yuanjun4618@dingtalk.com");
		r.setReceiptToList(receiptToList);
		
		ArrayList<String> receiptCCList = new ArrayList<String>();
		receiptCCList.add("gwvalas@dingtalk.com");
		r.setReceiptCCList(receiptCCList);
		
		Map<String,File> inner = new HashMap<String,File>();
		File f1 = new File("/Users/yuanjun/Documents/1添加应用.png");
		inner.put("inner1", f1);
		r.setInlineMap(inner);
		
		HashMap<String,File> attach = new HashMap<String,File>();
		File a1 = new File("/Users/yuanjun/Documents/cloudbae.sql");
		attach.put("脚本cloudbae.sql", a1);
		r.setAttachmentMap(attach);
		
	    java.net.HttpURLConnection conn = null;
        int rCode = 0;
        try {
        		conn = Utility.createPostConnection("http://localhost:6060//api/v1/email/htmlmail", r);
        		// trigger the request
            rCode = conn.getResponseCode();
            System.out.println(rCode);
            String body = getResponseBody(conn.getInputStream());
            System.out.println(body);
            
        }catch(Exception e) {
        		e.printStackTrace();
        }
	}
	
	private static String getResponseBody(InputStream responseStream) throws IOException {
        //\A is the beginning of the stream boundary
    		Scanner scanner = new Scanner(responseStream, "UTF-8");
    		scanner.useDelimiter("\\\\A");
        String rBody = scanner.next(); 
        responseStream.close();
        scanner.close();
        return rBody;
    }

}
