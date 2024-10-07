package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
public class Sites {
	private List<Site> list;

    @XmlElement(name = "row")
    public List<Site> getBusinessList() {
        return list;
    }

    public void setBusinessList(List<Site> list) {
        this.list = list;
    }
}
