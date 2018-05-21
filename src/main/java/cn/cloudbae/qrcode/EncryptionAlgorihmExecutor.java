package cn.cloudbae.qrcode;

import java.util.Map;

public interface EncryptionAlgorihmExecutor {
  String KEYPAIR_PUBLICKEY = "Algoriham.publicKey";
  String KEYPAIR_PRIVATEKEY = "Algoriham.privateKey";
  
  /**
   * 生成公私钥对，并以map放回
   * 
   * @return
   */
  Map<String, String> generateKeyPair();
  
  /**
   * 生成sign信息，并Base64加密返回
   * 
   * @param target
   * @return
   */
  String generateSignInfo(String target);
  
  /**
   * 根据签名校验信息
   * 
   * @param target 待校验对象
   * @param sign Base64加密的sign信息
   * @return
   */
  boolean verifySignInfo(String target, String sign);
}
