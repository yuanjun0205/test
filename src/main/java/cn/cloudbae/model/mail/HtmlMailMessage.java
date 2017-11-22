package cn.cloudbae.model.mail;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * HTML邮件消息
 */
public class HtmlMailMessage implements Serializable{
	
	private static final long serialVersionUID = 6458917007235782838L;
	private String               messageId;
	private String 				senderId;		//发送方标识
	private String               secretKey;      //SecretKey
	private List<String> 		receiptToList; 	//接受者列表
	private List<String> 		receiptCCList; 	//抄送者列表
	private String 				subject;		//主题
	private String 				htmlText;		//HTML内容
	private Map<String,File>	inlineMap;		//关联图片
	private Map<String,File>	attachmentMap;	//附件
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public List<String> getReceiptToList() {
		return receiptToList;
	}
	public void setReceiptToList(List<String> receiptToList) {
		this.receiptToList = receiptToList;
	}
	public List<String> getReceiptCCList() {
		return receiptCCList;
	}
	public void setReceiptCCList(List<String> receiptCCList) {
		this.receiptCCList = receiptCCList;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getHtmlText() {
		return htmlText;
	}
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public Map<String,File> getInlineMap() {
		return inlineMap;
	}
	public void setInlineMap(Map<String,File> inlineMap) {
		this.inlineMap = inlineMap;
	}
	public Map<String,File> getAttachmentMap() {
		return attachmentMap;
	}
	public void setAttachmentMap(Map<String,File> attachmentMap) {
		this.attachmentMap = attachmentMap;
	}
	

}
