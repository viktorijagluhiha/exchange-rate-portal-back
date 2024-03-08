package lv.uroof.exchangerateportalback.entity.exchangerate.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;

@XmlRootElement(name = "Err", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRateErrorCodeXMLO {
    @XmlElement(name = "Prtry", namespace = CurrenciesXMLO.NAMESPACE)
    private String code;
}
