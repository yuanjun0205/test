package cn.cloudbae.qrcode;

import java.util.Map;

public class ECDSATools {

	public static void main(String[] args) {
		ECDSAAlgorihmExecutor executor = new ECDSAAlgorihmExecutor(Algoriham.EC_192);
		Map<String, String> keyPairs = executor.generateKeyPair();
		System.out.println(keyPairs);
		
		//Algoriham.privateKey=MDkCAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQEEHzAdAgEBBBigN+ZA+9nTmu+eHcCkAH0sLB4tWKopZJ4=
		//Algoriham.publicKey=MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEKutBaDmDVGeKY+h/8v69VAjoQ13y7a71tmWGTtcl2k5vZ+LhN7bd+2izVBjrcus/

		executor.setPublicKey(keyPairs.get("Algoriham.publicKey"));
		executor.setPrivateKey(keyPairs.get("Algoriham.privateKey"));
		String target = "MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==MDUCGEZTpDlyYczQioX7/bw1Gu/xXFlLfx6IMQIZAIlihVUlCB2agAGrG0KRzHiR7AfZ//LpmA==";
		String sign = executor.generateSignInfo(target);
		System.out.println(sign);
		boolean result = executor.verifySignInfo(target, sign);
		System.out.println(result);
		
	}

}
