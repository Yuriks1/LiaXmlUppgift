package se.replyto.microservices.LiaXmlUppgift.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import se.replyto.microservices.LiaXmlUppgift.beans.CurrencyExchangeDto;
import se.replyto.microservices.LiaXmlUppgift.beans.InboundCurrencyExchangeSet;
import se.replyto.microservices.LiaXmlUppgift.beans.OutboundCurrencyExchange;
import se.replyto.microservices.LiaXmlUppgift.processor.ConvertToDtoProcessor;

import javax.xml.bind.JAXBContext;

@Component
public class CurrencyExchangeRouter extends RouteBuilder {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();


    @Override
    public void configure() throws Exception {


        JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
        JAXBContext jaxbContext = JAXBContext.newInstance(InboundCurrencyExchangeSet.class);
        xmlDataFormat.setContext(jaxbContext);
        xmlDataFormat.setContentTypeHeader(true);
        xmlDataFormat.setEncoding("UTF-8");
        xmlDataFormat.setPrettyPrint(true);
        xmlDataFormat.setSchemaLocation("src/main/resources/InboundCurrencyExchange.xsd");


        validator()
                .type("xml")
                .withUri("validator:InboundCurrencyExchange.xsd");


        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(OutboundCurrencyExchange.class);
        jsonDataFormat.setPrettyPrint(true);
        jsonDataFormat.useList();


        from("file:files/xml")
                .routeId("currencyExchangeRouteId")
                .log(LoggingLevel.INFO, "Original body : ${body}")

                .unmarshal(xmlDataFormat)
                .log(LoggingLevel.INFO, "Unmarshalled body : ${body}")

                .process(new ConvertToDtoProcessor())
                .log(LoggingLevel.INFO, "After converted to POJO : ${body}")

                .multicast()
                .to("direct:csv")
                .to("direct:json")
                .to("direct:database")

                .end();


        from("direct:csv")
                .doTry()
                .marshal()
                .bindy(BindyType.Csv, CurrencyExchangeDto.class)
                .log(LoggingLevel.INFO, "New CSV body : ${body}")
                .to("file:files/output?fileName=1000.csv")
                .doCatch(Exception.class)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    System.out.println(cause);
                })
                .end();


        from("direct:json")
                .doTry()
                .marshal(jsonDataFormat)
                .log(LoggingLevel.INFO, "New JSON body : ${body}")
                .to("activemq:jsonOut")
                .doCatch(Exception.class)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    System.out.println(cause);
                })
                .end();


        from("direct:database")
                .log(LoggingLevel.INFO, "Before split(body)) : ${body}")
                .split(body())
                .log(LoggingLevel.INFO, "After split(body)) : ${body}")
                .doTry()
                .to("jpa:se.replyto.microservices.xmluppgift.beans.CurrencyExchangeDto")
                .doCatch(Exception.class)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    System.out.println(cause);
                })
                .end();


        restConfiguration().component("jetty").host("0.0.0.0").port(8080).bindingMode(RestBindingMode.json).enableCORS(true);

        rest("replyto")
                .produces("application/json")
                .post("currency_exchange")
                .type(CurrencyExchangeDto.class)
                .route().routeId("RestRouteId")
                .log(LoggingLevel.INFO, "New body Rest : ${body}")
                .to("jpa:se.replyto.microservices.xmluppgift.beans.CurrencyExchangeDto")
                .endRest();


    }
}













