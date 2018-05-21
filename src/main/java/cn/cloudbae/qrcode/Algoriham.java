package cn.cloudbae.qrcode;

public enum Algoriham {
  EC_192("EC", "SHA256withECDSA", 192);
  
  private Integer length;
  private String name;
  private String signName;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSignName() {
    return signName;
  }

  public void setSignName(String signName) {
    this.signName = signName;
  }
  
  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  private Algoriham(String name, String signName, Integer length) {
    this.name = name;
    this.signName = signName;
    this.length = length;
  }
}
