package lv.uroof.exchangerateportalback.entity.currency.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "CcyMn", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class CurrencyNameTranslationXMLO {
    @XmlAttribute(name = "lang")
    private String languageCode;

    @XmlValue
    private String name;
}
