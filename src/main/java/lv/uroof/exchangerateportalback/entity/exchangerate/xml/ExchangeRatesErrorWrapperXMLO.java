package lv.uroof.exchangerateportalback.entity.exchangerate.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;

@XmlRootElement(name = "OprlErr", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRatesErrorWrapperXMLO {
    @XmlElement(name = "Err", namespace = CurrenciesXMLO.NAMESPACE)
    private ExchangeRateErrorCodeXMLO code;

    @XmlElement(name = "Desc", namespace = CurrenciesXMLO.NAMESPACE)
    private String description;
}
