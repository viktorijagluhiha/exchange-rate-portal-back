package lv.uroof.exchangerateportalback.entity.exchangerate.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;

import java.util.List;

@XmlRootElement(name = "FxRates", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRatesXMLO {
    @XmlElement(name = "FxRate", namespace = CurrenciesXMLO.NAMESPACE)
    private List<ExchangeRateXMLO> exchangeRates;

    @XmlElement(name = "OprlErr", namespace = CurrenciesXMLO.NAMESPACE)
    private ExchangeRatesErrorWrapperXMLO error;
}
