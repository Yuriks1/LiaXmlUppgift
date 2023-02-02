package se.replyto.microservices.LiaXmlUppgift.beans;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "line")
@XmlAccessorType(XmlAccessType.FIELD)
public class InboundCurrencyExchange implements Serializable {

    @XmlElement
    @Id
    @Column(name = "id")
    Integer id;

    @XmlElement
    @Column(name = "from")
    String from;

    @XmlElement
    @Column(name = "to")
    String to;

    @XmlElement
    @Column(name = "conversion")
    Integer conversionMultiple;

    public InboundCurrencyExchange() {
    }

    public InboundCurrencyExchange(Integer id, String from, String to, Integer conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(Integer conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    @Override
    public String toString() {
        return "InboundCurrencyExchange{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }
}
