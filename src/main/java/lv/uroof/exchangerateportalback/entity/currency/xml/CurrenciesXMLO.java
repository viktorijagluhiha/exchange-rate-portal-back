package lv.uroof.exchangerateportalback.entity.currency.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "CcyTbl", namespace = CurrenciesXMLO.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class CurrenciesXMLO {
    public static final String NAMESPACE = "http://www.lb.lt/WebServices/FxRates";

    @XmlElement(name = "CcyNtry", namespace = CurrenciesXMLO.NAMESPACE)
    private List<CurrencyXMLO> currencies = new ArrayList<>();
}
