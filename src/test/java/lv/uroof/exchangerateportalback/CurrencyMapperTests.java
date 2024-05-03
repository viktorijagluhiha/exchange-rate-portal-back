package lv.uroof.exchangerateportalback;

import lv.uroof.exchangerateportalback.entity.currency.*;
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
    private static CurrencyResponseDTO currencyResponseDTO;
    private static CurrencyRequestDTO currencyRequestDTO;

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

    private static void initializeCurrencyResponseDTOUSD() {
        currencyResponseDTO = new CurrencyResponseDTO();

        currencyResponseDTO.setId(null);
        currencyResponseDTO.setCode("USD");
        currencyResponseDTO.setNumericCode((short) 840);
        currencyResponseDTO.setMinorUnits((short) 2);
        currencyResponseDTO.setName("US dollar");
    }

    private static void initializeCurrencyRequestDTOUSD() {
        currencyRequestDTO = new CurrencyRequestDTO();

        currencyRequestDTO.setCode("USD");
        currencyRequestDTO.setNumericCode((short) 840);
        currencyRequestDTO.setMinorUnits((short) 2);
        currencyRequestDTO.setName("US dollar");
    }

    @BeforeAll
    static void initialize() {
        initializeCurrencyXMLOUSD();
        initializeCurrencyDOUSD();
        initializeCurrencyResponseDTOUSD();
        initializeCurrencyRequestDTOUSD();
    }

    @Test
    void currencyXMLOToCurrencyDOTest() {
        CurrencyDO result = currencyMapper.currencyXMLOToCurrencyDO(currencyXMLO);
        Assertions.assertEquals(currencyDO, result);
    }

    @Test
    void currencyDOToCurrencyResponseDTOTest() {
        CurrencyResponseDTO result = currencyMapper.currencyDOToCurrencyResponseDTO(currencyDO);
        Assertions.assertEquals(currencyResponseDTO, result);
    }

    @Test
    void currencyRequestDTOToCurrencyDOTest() {
        CurrencyDO result = currencyMapper.currencyRequestDTOToCurrencyDO(currencyRequestDTO);
        Assertions.assertEquals(currencyDO, result);
    }

    @Test
    void currencyXMLOToCurrencyDONullTest() {
        CurrencyDO result = currencyMapper.currencyXMLOToCurrencyDO(null);
        Assertions.assertNull(result);
    }

    @Test
    void currencyDOToCurrencyResponseDTONullTest() {
        CurrencyResponseDTO result = currencyMapper.currencyDOToCurrencyResponseDTO(null);
        Assertions.assertNull(result);
    }

    @Test
    void currencyRequestDTOToCurrencyDONullTest() {
        CurrencyDO result = currencyMapper.currencyRequestDTOToCurrencyDO(null);
        Assertions.assertNull(result);
    }
}
