package cn.cloudbae;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

public class SignatureTest {
	public static String privateKeyString = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIICXgIBAAKBgQDfDCBE4HnvJN8IpKW/LcFmJ5vv47iEbcg50yZGvDpJr6fED0zW\n" + 
			"fcn6S8mjfnlqgbAC+htdIyB8KeTBSjW056nTBE4aP3zZUpQb8zGFqLafMmfHDnIu\n" + 
			"c37ZoB8+LqKf2Ahc4vVdj3cXmldOQG86b8BoAaKEkSh9uD9Wxt4DIiEQZwIDAQAB\n" + 
			"AoGBAIKDSe6Dm1W/sXIRRZsUXvIzgx9flHyI8HxFfUQup7yuN/CVM1TezezTo+0y\n" + 
			"+EDGGgPj7VcWUs/Ug7JGhh6Q3+EuBZgOzZXK6HDb5o7Qbdb8Z7Fs4nwbuTQvOcNV\n" + 
			"4Nc5PQ9juNzB/7QmNGA05sNttc7/HI84x7h8FQIo9A0NHl+BAkEA9cUArZGPiRsa\n" + 
			"xgIxVxO9YyuietEYdOTWH45rYCEht+9k85paStHWSGUHL0y4NseKLN8NscvTgJOn\n" + 
			"j6aC2cOC1wJBAOhU/SD7W/662vZlArjI8AAcJAMWYoKVvelqKtkouaHRZpYWFu3U\n" + 
			"4O/WMmPyd9T9Dd0EhgfgkYTHR7qo+YhLvPECQD6t0XBysRKccQWSxZaZmDFUjYmZ\n" + 
			"Dg2x1tIRdlz71ieczDg8Y061mVq5OU3c2RZPaXPsJwfzHauq/I7sPm7fJ90CQQCB\n" + 
			"7uYSCAGPGJlUq3E2PspahWJuh5pYRb6MR9/myyvmH+IrlfcE08Vo3HYaB4SVDWEv\n" + 
			"dlQvG5zGkVzAO7gyTYlBAkEA06jBQhofdfo5xifpTRBsVsqzVEzXHLrbCSRFJs3u\n" + 
			"19rB8qqFKhCIUJ3BmFwjl+HH1fu6dqOK9pgrxv99TRhlIA==\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static String publicKeyString = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfDCBE4HnvJN8IpKW/LcFmJ5vv\n" + 
			"47iEbcg50yZGvDpJr6fED0zWfcn6S8mjfnlqgbAC+htdIyB8KeTBSjW056nTBE4a\n" + 
			"P3zZUpQb8zGFqLafMmfHDnIuc37ZoB8+LqKf2Ahc4vVdj3cXmldOQG86b8BoAaKE\n" + 
			"kSh9uD9Wxt4DIiEQZwIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		///////////////////////加签
		PrivateKey privateKey = getPrivateKeyFromPEM(privateKeyString);
		String data = "1234567890";
		
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] signBytes = signature.sign();

        String sign = Base64.encodeBase64String(signBytes).replaceAll("\n|\r", "");
        System.out.println(sign);

		///////////////////////验签
		
		PublicKey publicKey = getPubKey();
		byte[] signatureBytes = Base64.decodeBase64(sign);
		
		Signature signature2 = Signature.getInstance("SHA256withRSA");
        signature2.initVerify(publicKey);
        signature2.update(data.getBytes("UTF-8"));
        boolean isPass = signature2.verify(signatureBytes);
        System.out.print(isPass);
	}
	
	
	/**
     * 获得公钥
     * @return
     * @throws Exception
     */
    public static PublicKey getPubKey() throws Exception {
        String pubKeyString = publicKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        byte[] keyBytes = Base64.decodeBase64(pubKeyString);

        // generate public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }
	
	public static PrivateKey getPrivateKeyFromPEM(String PEMEncodedPrivateKey) {
        PEMEncodedPrivateKey = PEMEncodedPrivateKey
                .replaceAll("(-+BEGIN (RSA )?PRIVATE KEY-+\\r?\\n|-+END (RSA )?PRIVATE KEY-+\\r?\\n?)", "");
        byte[] privateKeyBytes = Base64.decodeBase64(PEMEncodedPrivateKey);

        try {
            return generatePrivateKeyWithPKCS8(privateKeyBytes);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return generatePrivateKeyWithPKCS1(privateKeyBytes);
        }
    }

    public static PrivateKey generatePrivateKeyWithPKCS8(byte[] privateKeyBytes)
            throws InvalidKeySpecException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PrivateKey generatePrivateKeyWithPKCS1(byte[] privateKeyBytes) {
        try {
            DerInputStream derReader = new DerInputStream(privateKeyBytes);
            DerValue[] seq = derReader.getSequence(0);
            if (seq.length < 9) {
                System.out.println("Could not parse a PKCS1 private key.");
                return null;
            }
            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();
            RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(
                    modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(spec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

}
