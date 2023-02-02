package se.replyto.microservices.LiaXmlUppgift.beans;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;

@CsvRecord(separator = ",", crlf = "UNIX")
public class OutboundCsvExchange implements Serializable {

    @DataField(pos = 1, columnName = "Id")
    Integer id;

    @DataField(pos = 2, columnName = "source")
    String from;

    @DataField(pos = 3, columnName = "dest")
    String to;

    @DataField(pos = 4, columnName = "convRate")
    Integer conversionMultiple;

    public OutboundCsvExchange() {
    }

    public OutboundCsvExchange(Integer id, String from, String to, Integer conversionMultiple) {
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
        return "OutboundCsvExchange{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }



}
