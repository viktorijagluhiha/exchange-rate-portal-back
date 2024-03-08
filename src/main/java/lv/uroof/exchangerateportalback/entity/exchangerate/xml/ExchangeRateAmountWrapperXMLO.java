package lv.uroof.exchangerateportalback.entity.exchangerate.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;

import java.math.BigDecimal;

@XmlRootElement(name = "CcyAmt", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRateAmountWrapperXMLO {
    @XmlElement(name = "Ccy", namespace = CurrenciesXMLO.NAMESPACE)
    private String currencyCode;

    @XmlElement(name = "Amt", namespace = CurrenciesXMLO.NAMESPACE)
    private BigDecimal amount;
}
