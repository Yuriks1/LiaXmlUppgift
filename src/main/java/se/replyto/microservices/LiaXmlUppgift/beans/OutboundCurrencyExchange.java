package se.replyto.microservices.LiaXmlUppgift.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(value = {"ID"})
public class OutboundCurrencyExchange implements Serializable {

    @JsonProperty("ID")
    Integer id;

    @JsonProperty("source")
    String from;

    @JsonProperty("dest")
    String to;

    @JsonProperty("convRate")
    Integer conversionMultiple;

    public OutboundCurrencyExchange() {
    }

    public OutboundCurrencyExchange(Integer id, String from, String to, Integer conversionMultiple) {
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
        return "OutboundCurrencyExchange{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }
}
