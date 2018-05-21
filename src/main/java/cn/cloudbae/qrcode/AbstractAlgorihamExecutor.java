package cn.cloudbae.qrcode;

import java.io.UnsupportedEncodingException;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.binary.Base64;

public abstract class AbstractAlgorihamExecutor implements EncryptionAlgorihmExecutor {
  private static final Logger logger = LoggerFactory.getLogger(AbstractAlgorihamExecutor.class);

  //private static final String DEFAULT_CHARSET = "UTF-8";
  private static final String DEFAULT_CHARSET = "ISO-8859-1";
  
  protected abstract Algoriham getDefaultAlgoriham();
  protected abstract String getSignPrivateKey();
  protected abstract String getVerifyPublicKey();
  
  /**
   * 方便实现类在生成KeyPair时加入一些校验逻辑
   * @return
   */
  protected boolean validateKeyPairArgs() {
    return true;
  }

  @Override
  public Map<String, String> generateKeyPair() {
    if(!validateKeyPairArgs()) {
      logger.info("EncryptionAlgorihmExecutor.generateKeyPair validate failure.");
      return null;
    }
    KeyPairGenerator keyPairGenerator;
    try {
      keyPairGenerator = KeyPairGenerator.getInstance(getDefaultAlgoriham().getName());
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getMessage(), e);
      return null;
    }  
    keyPairGenerator.initialize(getDefaultAlgoriham().getLength());  
    KeyPair keyPair = keyPairGenerator.generateKeyPair();  
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    
    String strPubKey = Base64.encodeBase64String(publicKey.getEncoded());
    String strPriKey = Base64.encodeBase64String(privateKey.getEncoded());
    
    Map<String, String> map = new HashMap<>();
    map.put(KEYPAIR_PUBLICKEY, strPubKey);
    map.put(KEYPAIR_PRIVATEKEY, strPriKey);
    
    return map;
  }

  @Override
  public String generateSignInfo(String target) {
	PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(getSignPrivateKey()));  
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(getDefaultAlgoriham().getName());
      PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
      Signature signature = Signature.getInstance(getDefaultAlgoriham().getSignName());
      signature.initSign(priKey);  
      signature.update(target.getBytes(DEFAULT_CHARSET));
      byte[] res = signature.sign();  
      return Base64.encodeBase64String(res);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | KeyException | SignatureException | UnsupportedEncodingException e) {
      logger.error(e.getMessage(), e);
      return null;
    }  
  }
  
  @Override
  public boolean verifySignInfo(String target, String sign) {
	byte[] pubBytes = Base64.decodeBase64(getVerifyPublicKey());
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubBytes);
    boolean flag = false;
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(getDefaultAlgoriham().getName());
      PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
      Signature signature = Signature.getInstance(getDefaultAlgoriham().getSignName());
      signature.initVerify(publicKey);

      // 更新 要 验证的 数据
      signature.update(target.getBytes(DEFAULT_CHARSET));
      byte[] orgSignByte = Base64.decodeBase64(sign);
      // 验证-公钥签名
      flag = signature.verify(orgSignByte);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | KeyException | SignatureException | UnsupportedEncodingException e) {
      logger.error(e.getMessage(), e);
    }
    
    return flag;
  }

  
}
