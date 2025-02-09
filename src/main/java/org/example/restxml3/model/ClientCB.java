package org.example.restxml3.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Data
public class ClientCB {
    private RestTemplate restTemplate = new RestTemplate();

    public ValCurs getValCurs() {
        return restTemplate.getForObject(
                generateUrlWithDate(), ValCurs.class);
    }


    private String generateUrlWithDate() {

        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + data;
    }

    public ValCurs getValCursByDate(LocalDate date) {
        String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateString;
        ValCurs valCurs = restTemplate.getForObject(url, ValCurs.class);
        valCurs.setDate(date.toString());
        return valCurs;
    }

}


//    private ValCurs parseXML (String xml) throws JAXBException {
//        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        return (ValCurs) unmarshaller.unmarshal(new StringReader(xml));
//    }

