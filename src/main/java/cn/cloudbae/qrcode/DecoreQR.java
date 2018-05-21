package cn.cloudbae.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class DecoreQR {

	public static void main(String[] args) {
        String filePath = "/Users/yuanjun/downloads/img.png";
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "ISO-8859-1");
            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
            String retStr = result.getText();
            
            String[] temp = retStr.split("\f",-1);
            
            byte[] contents = temp[0].getBytes("ISO-8859-1");
            String sign = temp[1];
            
            ECDSAAlgorihmExecutor executor = new ECDSAAlgorihmExecutor(Algoriham.EC_192);
            executor.setPublicKey("MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEALlT34LeMv49b7i1dAGt/txX1QN9rfEGzC6XI7jYVJkZypYtavSvdOqgPxX3D2qq");
            boolean isSignPass = executor.verifySignInfo(temp[0], sign);
            System.out.println(isSignPass);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(contents); 
  		  	DataInputStream dis = new DataInputStream(bais); 
  		  	System.out.println(dis.readByte()); 
		    System.out.println(dis.readByte()); 
		    System.out.println(dis.readByte()); 
		    System.out.println(dis.readShort()); 
		    System.out.println(dis.readInt());
		    System.out.println(dis.readLong());
		    byte length = dis.readByte();
		    byte[] bytes = new byte[length];
		    System.out.println(dis.read(bytes));
		    System.out.println(new String(bytes,"ISO-8859-1"));
		    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
