package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
public class Site {
	private String bplcNm;       // 사업장명
    private String trdStateNm;   // 영업상태명
    private String rdnWhlAddr;   // 도로명전체주소
    private String x;            // 좌표정보(X)
    private String y;            // 좌표정보(Y)

    @XmlElement(name = "bplcNm")
    public String getBplcNm() {
        return bplcNm;
    }

    public void setBplcNm(String bplcNm) {
        this.bplcNm = bplcNm;
    }

    @XmlElement(name = "trdStateNm")
    public String getTrdStateNm() {
        return trdStateNm;
    }

    public void setTrdStateNm(String trdStateNm) {
        this.trdStateNm = trdStateNm;
    }

    @XmlElement(name = "rdnWhlAddr")
    public String getRdnWhlAddr() {
        return rdnWhlAddr;
    }

    public void setRdnWhlAddr(String rdnWhlAddr) {
        this.rdnWhlAddr = rdnWhlAddr;
    }

    @XmlElement(name = "x")
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @XmlElement(name = "y")
    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Business{" +
                "bplcNm='" + bplcNm + '\'' +
                ", trdStateNm='" + trdStateNm + '\'' +
                ", rdnWhlAddr='" + rdnWhlAddr + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
