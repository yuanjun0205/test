package cn.cloudbae.qrcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ToByteArray {
	public static void main(String[] args) { 
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(baos); 
	    try { 
	      dos.writeByte(4); 
	      dos.writeByte(1); 
	      dos.writeByte(1); 
	      dos.writeShort(217); 
	     } catch (IOException e) { 
		    e.printStackTrace(); 
		 } 
	  
		  byte[] aa = baos.toByteArray(); 
		  ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray()); 
		  DataInputStream dis = new DataInputStream(bais); 
		  
		  try { 
		    System.out.println(dis.readByte()); 
		    System.out.println(dis.readByte()); 
		    System.out.println(dis.readByte()); 
		    System.out.println(dis.readShort()); 
		  } catch (IOException e) { 
		    e.printStackTrace(); 
		  } 
		  try { 
		    dos.close(); 
		    dis.close(); 
		  } catch (IOException e) { 
		    e.printStackTrace(); 
		  } 
	} 
}
