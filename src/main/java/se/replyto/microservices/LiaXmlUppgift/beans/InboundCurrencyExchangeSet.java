package se.replyto.microservices.LiaXmlUppgift.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class InboundCurrencyExchangeSet implements Serializable {

    @XmlElement(name = "line")
    List<InboundCurrencyExchange> inboundCurrencyExchangeList;

    public InboundCurrencyExchangeSet() {
    }

    public List<InboundCurrencyExchange> getInboundCurrencyExchangeList() {
        return inboundCurrencyExchangeList;
    }

    public void setInboundCurrencyExchangeList(List<InboundCurrencyExchange> inboundCurrencyExchangeList) {
        this.inboundCurrencyExchangeList = inboundCurrencyExchangeList;
    }

    public InboundCurrencyExchangeSet(List<InboundCurrencyExchange> inboundCurrencyExchangeList) {
        this.inboundCurrencyExchangeList = inboundCurrencyExchangeList;
    }

    @Override
    public String toString() {
        return "InboundCurrencyExchangeSet{" +
                "inboundCurrencyExchangeList=" + inboundCurrencyExchangeList +
                '}';
    }
}
