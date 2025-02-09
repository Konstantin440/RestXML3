package org.example.restxml3.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Valute {

    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "NumCode")
    private String numCode;
    @XmlElement(name = "CharCode")
    private String charCode;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Value")
    private String value;

}


//<Valute ID="R01010">
//<NumCode>036</NumCode>
//<CharCode>AUD</CharCode>
//<Nominal>1</Nominal>
//<Name>Австралийский доллар</Name>
//<Value>22,1214</Value>
//<VunitRate>22,1214</VunitRate>
//</Valute>