package lv.uroof.exchangerateportalback.entity.currency.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "CcyNtry", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class CurrencyXMLO {
    @XmlElement(name = "Ccy", namespace = CurrenciesXMLO.NAMESPACE)
    private String code;

    @XmlElement(name = "CcyNm", namespace = CurrenciesXMLO.NAMESPACE)
    private List<CurrencyNameTranslationXMLO> nameTranslations;

    @XmlElement(name = "CcyNbr", namespace = CurrenciesXMLO.NAMESPACE)
    private Short numericCode;

    @XmlElement(name = "CcyMnrUnts", namespace = CurrenciesXMLO.NAMESPACE)
    private Short minorUnits;
}
