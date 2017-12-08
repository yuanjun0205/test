package cn.cloudbae;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpClientFileUpload {

	public static void main(String[] args) {
		HttpClientFileUpload u = new HttpClientFileUpload();
		u.upload();
	}
	
	public void upload() {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
		    httpclient = HttpClients.createDefault();
		    HttpPost post = new HttpPost("https://nnapp.cloudbae.cn:38080/zuul/storage/api/v1/uploadFile");
		//  HttpEntity dataEntity = getMultiFileEntity();//File文件格式上传
		    HttpEntity dataEntity = getMultiDefaultFileEntity();//File文件格式上传（缺省）
		//  HttpEntity dataEntity = getMultiArrayEntity();//byte数组格式上传
		//  HttpEntity dataEntity = getMultiDefaultArrayEntity();//byte数组格式上传（缺省）
		//  HttpEntity dataEntity = getMultiStreamEntity();//文件流格式上传
		//  HttpEntity dataEntity = getMultiDefaultStreamEntity();//文件流格式上传（缺省）
		    post.setEntity(dataEntity);
		    response = httpclient.execute(post);
		    HttpEntity entity = response.getEntity();
		    System.out.println(EntityUtils.toString(entity, "UTF-8"));
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    CommonUtils.closeResponse(response);
		    CommonUtils.closeHttpClient(httpclient);
		}
	}
	
	

	/**
	 * File文件格式上传
	*/
	public HttpEntity getMultiFileEntity() {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    File file = new File("F:/PIC/house.jpeg");;
	    builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, "photo.jpg");
	    return builder.build();
	}

	/**
	 * File文件格式上传（缺省）
	*/
	public HttpEntity getMultiDefaultFileEntity() {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    //File file = new File("/Users/yuanjun/Documents/wo.jpg");
	    File file = new File("/Users/yuanjun/Downloads/a.pdf");
	    builder.addBinaryBody("file", file);
	    return builder.build();
	}

	/**
	 * byte数组格式上传
	*/
	public HttpEntity getMultiArrayEntity() {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    byte[] byteArr = getFileArr();
	    builder.addBinaryBody("file", byteArr, ContentType.DEFAULT_BINARY, "photoArr.jpg");
	    return builder.build();
	}

	/**
	 * byte数组格式上传（缺省）
	*/
	public HttpEntity getMultiDefaultArrayEntity() {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    byte[] byteArr = getFileArr();
	    builder.addBinaryBody("file", byteArr);
	    return builder.build();
	}
	    
	public byte[] getFileArr() {
	    try {
	        File file = new File("F:/PIC/house.jpeg");
	        FileInputStream fis = new FileInputStream(file);
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	        byte[] b = new byte[1000];  
	        int n;  
	        while ((n = fis.read(b)) != -1) {  
	            bos.write(b, 0, n);  
	        }  
	        fis.close();  
	        bos.close();  
	        return bos.toByteArray(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new byte[0];
	    }
	}

	/**
	 * 文件流格式上传
	*/
	 public HttpEntity getMultiStreamEntity() throws FileNotFoundException {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    File file = new File("F:/PIC/house.jpeg");
	    InputStream stream = new FileInputStream(file);
	    builder.addBinaryBody("file", stream, ContentType.DEFAULT_BINARY, "photoStream.jpg");
	    return builder.build();
	}

	/**
	 * 文件流格式上传（缺省）
	*/
	public HttpEntity getMultiDefaultStreamEntity() throws FileNotFoundException {
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    File file = new File("F:/PIC/house.jpeg");
	    InputStream stream = new FileInputStream(file);
	    builder.addBinaryBody("file", stream);
	    return builder.build();
	}

}

class CommonUtils {
	public static void closeResponse(CloseableHttpResponse response) {
	    try {
	        if (response != null) {
	            response.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static void closeHttpClient(CloseableHttpClient httpclient) {
	    try {
	        if (httpclient != null) {
	            httpclient.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}