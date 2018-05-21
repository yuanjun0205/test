package cn.cloudbae.qrcode;

public class ECDSAAlgorihmExecutor extends AbstractAlgorihamExecutor {
  private Algoriham algoriham;
  private String privateKey;
  private String publicKey;

  public void setAlgoriham(Algoriham algoriham) {
    this.algoriham = algoriham;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  protected Algoriham getDefaultAlgoriham() {
    return algoriham;
  }

  @Override
  protected String getSignPrivateKey() {
    return privateKey;
  }

  @Override
  protected String getVerifyPublicKey() {
    return publicKey;
  }

  public ECDSAAlgorihmExecutor(Algoriham alg) {
    this.algoriham = alg;
  }
}
