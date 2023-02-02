package se.replyto.microservices.LiaXmlUppgift.processor;

import org.apache.camel.Exchange;
import se.replyto.microservices.LiaXmlUppgift.beans.CurrencyExchangeDto;
import se.replyto.microservices.LiaXmlUppgift.beans.InboundCurrencyExchange;
import se.replyto.microservices.LiaXmlUppgift.beans.InboundCurrencyExchangeSet;

import java.util.ArrayList;
import java.util.List;

public class ConvertToDtoProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        InboundCurrencyExchangeSet inboundCurrencyExchangeSet = exchange.getIn().getBody(InboundCurrencyExchangeSet.class);
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<>();
        if(inboundCurrencyExchangeSet != null){
            for (InboundCurrencyExchange curExchange: inboundCurrencyExchangeSet.getInboundCurrencyExchangeList()){
                CurrencyExchangeDto currencyExchangeDto = new CurrencyExchangeDto(curExchange.getId(),curExchange.getFrom()
                        ,curExchange.getTo(),curExchange.getConversionMultiple());
                currencyExchangeDtoList.add(currencyExchangeDto);
            }
        }
        exchange.getIn().setBody(currencyExchangeDtoList);
    }
}
