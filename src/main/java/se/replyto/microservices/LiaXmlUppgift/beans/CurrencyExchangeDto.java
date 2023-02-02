package se.replyto.microservices.LiaXmlUppgift.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@CsvRecord(separator = ",", crlf = "UNIX", generateHeaderColumns = true)
@JsonIgnoreProperties(value = {"currency_exchange"})
@Table(name = "CURRENCY_EXCHANGE")
public class CurrencyExchangeDto {
    @Id
    @Column(name = "ID")
    @DataField(pos = 1, columnName = "ID")
    @JsonProperty("id")
    private int id;


    @Column(name = "SOURCE")
    @DataField(pos = 2, columnName = "Source")
    @JsonProperty("source")
    private String from;


    @Column(name = "DESTINATION")
    @DataField(pos = 3, columnName = "Destination")
    @JsonProperty("destination")
    private String to;


    @Column(name = "CONVERSION_RATE")
    @DataField(pos = 4, columnName = "Conversion_Rate")
    @JsonProperty("conversion_rate")
    private Integer conversionMultiple;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CurrencyExchangeDto() {
    }

    public CurrencyExchangeDto(int id, String from, String to, Integer conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeDto{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", conversionMultiple=" + conversionMultiple +
                '}';
    }
}
