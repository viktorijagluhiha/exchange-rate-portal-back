package lv.uroof.exchangerateportalback;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyMapper;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyMapperImpl;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyNameTranslationXMLO;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CurrencyMapperImpl.class})
public class CurrencyMapperTests {
    @Autowired
    CurrencyMapper currencyMapper;

    private static CurrencyXMLO currencyXMLO;
    private static CurrencyDO currencyDO;

    private static void initializeCurrencyXMLOUSD() {
        currencyXMLO = new CurrencyXMLO();

        currencyXMLO.setCode("USD");
        currencyXMLO.setNumericCode((short) 840);
        currencyXMLO.setMinorUnits((short) 2);

        CurrencyNameTranslationXMLO translationEn = new CurrencyNameTranslationXMLO();
        translationEn.setLanguageCode("EN");
        translationEn.setName("US dollar");

        CurrencyNameTranslationXMLO translationLt = new CurrencyNameTranslationXMLO();
        translationLt.setLanguageCode("LT");
        translationLt.setName("JAV doleris");

        ArrayList<CurrencyNameTranslationXMLO> nameTranslations = new ArrayList<>();
        nameTranslations.add(translationEn);
        nameTranslations.add(translationLt);

        currencyXMLO.setNameTranslations(nameTranslations);
    }

    private static void initializeCurrencyDOUSD() {
        currencyDO = new CurrencyDO();

        currencyDO.setId(null);
        currencyDO.setCode("USD");
        currencyDO.setNumericCode((short) 840);
        currencyDO.setMinorUnits((short) 2);
        currencyDO.setName("US dollar");
    }

    @BeforeAll
    static void initialize() {
        initializeCurrencyXMLOUSD();
        initializeCurrencyDOUSD();
    }

    @Test
    void currencyXMLOToCurrencyDOTest() {
        CurrencyDO result = currencyMapper.currencyXMLOToCurrencyDO(currencyXMLO);
        Assertions.assertEquals(currencyDO, result);
    }
}
