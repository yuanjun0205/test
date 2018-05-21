package cn.cloudbae.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreatQRCode {

	public static void main(String[] args) throws Exception {
		//定义二维码的样式  
        int width = 300;  
        int height = 300;  
        String format = "png";  
        
        //String contents = "http://blog.csdn.net/qq_30507287";//扫描二维码时产生的连接  
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(baos); 
		dos.writeByte(4); 
		dos.writeByte(1); 
		dos.writeByte(1); 
		dos.writeShort(217); 
		dos.writeInt(66);
		dos.writeLong(8888);
		String info = "G0KRzHiR7AfZ//LpmA==";//用户ID
		byte[] infoBytes = info.getBytes("ISO-8859-1");
		dos.writeByte(infoBytes.length);//先保存长度
		dos.write(infoBytes);//再保存内容
		String contents = new String(baos.toByteArray(),"ISO-8859-1");
		
		String privateKey = "MDkCAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQEEHzAdAgEBBBjCqD1x2w+1wY/FJS2HNz9QBGdjVKTWuxU=";
		ECDSAAlgorihmExecutor executor = new ECDSAAlgorihmExecutor(Algoriham.EC_192);
		executor.setPrivateKey(privateKey);
		String sign = executor.generateSignInfo(contents);
		contents = contents.concat("\f").concat(sign);//用分隔符不安全，换方案
//		byte[] signBytes = sign.getBytes("ISO-8859-1");
//		dos.writeByte(signBytes.length);//先保存长度
//		dos.write(signBytes);//再保存内容
          
        //定义二维码的参数  
        HashMap<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>();  
        //hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        hints.put(EncodeHintType.CHARACTER_SET, "ISO-8859-1");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);//设置二维码的容错等级  
        hints.put(EncodeHintType.MARGIN, 2);//边距  
          
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        		Path file = new File("/Users/yuanjun/downloads/img.png").toPath();//保存的路径  
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);  	
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}


}
