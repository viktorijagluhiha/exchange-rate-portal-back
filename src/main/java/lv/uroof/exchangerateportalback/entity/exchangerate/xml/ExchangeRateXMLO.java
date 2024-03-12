package lv.uroof.exchangerateportalback.entity.exchangerate.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;

import java.util.List;

@XmlRootElement(name = "FxRate", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ExchangeRateXMLO {
    @XmlElement(name = "Tp", namespace = CurrenciesXMLO.NAMESPACE)
    private String type;

    @XmlElement(name = "Dt", namespace = CurrenciesXMLO.NAMESPACE)
    private String date;

    @XmlElement(name = "CcyAmt", namespace = CurrenciesXMLO.NAMESPACE)
    private List<ExchangeRateAmountWrapperXMLO> amounts;
}
