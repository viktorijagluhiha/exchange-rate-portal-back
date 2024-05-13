package lv.uroof.exchangerateportalback;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyRepository;
import lv.uroof.exchangerateportalback.entity.exchangerate.*;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateAmountWrapperXMLO;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExchangeRateMapperImpl.class})
public class ExchangeRateMapperTests {
    @MockBean
    CurrencyRepository currencyRepository;

    @Autowired
    ExchangeRateMapper exchangeRateMapper;

    private static String dateString = "2023-07-01";
    private static String dateFormat = "yyyy-MM-dd";
    private static String currencyEURCode = "EUR";
    private static String currencyUSDCode = "USD";
    private static BigDecimal baseAmount = BigDecimal.valueOf(1);
    private static BigDecimal quoteAmount = BigDecimal.valueOf(1.0866);

    private static CurrencyDO currencyEUR;
    private static CurrencyDO currencyUSD;

    private static ExchangeRateXMLO exchangeRateXMLO;
    private static ExchangeRateDO exchangeRateDO;
    private static ExchangeRateRequestDTO exchangeRateRequestDTO;
    private static ExchangeRateResponseDTO exchangeRateResponseDTO;

    private static void initializeCurrencyDOUSD() {
        currencyEUR = new CurrencyDO();

        currencyEUR.setId(null);
        currencyEUR.setCode(currencyEURCode);
        currencyEUR.setNumericCode((short) 978);
        currencyEUR.setMinorUnits((short) 2);
        currencyEUR.setName("Euro");
    }

    private static void initializeCurrencyDOEUR() {
        currencyUSD = new CurrencyDO();

        currencyUSD.setId(null);
        currencyUSD.setCode(currencyUSDCode);
        currencyUSD.setNumericCode((short) 840);
        currencyUSD.setMinorUnits((short) 2);
        currencyUSD.setName("US dollar");
    }

    private static void initializeExchangeRateXMLOUSD() {
        exchangeRateXMLO = new ExchangeRateXMLO();

        List<ExchangeRateAmountWrapperXMLO> amounts = new ArrayList<>();

        ExchangeRateAmountWrapperXMLO amountBase = new ExchangeRateAmountWrapperXMLO();
        amountBase.setCurrencyCode(currencyEURCode);
        amountBase.setAmount(baseAmount);

        ExchangeRateAmountWrapperXMLO amountQuote = new ExchangeRateAmountWrapperXMLO();
        amountQuote.setCurrencyCode(currencyUSDCode);
        amountQuote.setAmount(quoteAmount);

        amounts.add(amountBase);
        amounts.add(amountQuote);

        exchangeRateXMLO.setType("LT");
        exchangeRateXMLO.setDate(dateString);
        exchangeRateXMLO.setAmounts(amounts);
    }

    private static void initializeExchangeRateDOUSD() {
        exchangeRateDO = new ExchangeRateDO();

        exchangeRateDO.setId(null);
        exchangeRateDO.setDate(LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat)));
        exchangeRateDO.setCurrencyBase(currencyEUR);
        exchangeRateDO.setCurrencyBaseAmount(baseAmount);
        exchangeRateDO.setCurrencyQuote(currencyUSD);
        exchangeRateDO.setCurrencyQuoteAmount(quoteAmount);
    }

    private static void initializeExchangeRateRequestDTO() {
        exchangeRateRequestDTO = new ExchangeRateRequestDTO();

        exchangeRateRequestDTO.setDate(LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat)));
        exchangeRateRequestDTO.setCurrencyBaseCode(currencyEURCode);
        exchangeRateRequestDTO.setCurrencyBaseAmount(baseAmount);
        exchangeRateRequestDTO.setCurrencyQuoteCode(currencyUSDCode);
        exchangeRateRequestDTO.setCurrencyQuoteAmount(quoteAmount);
    }

    private static void initializeExchangeRateResponseDTO() {
        exchangeRateResponseDTO = new ExchangeRateResponseDTO();

        exchangeRateResponseDTO.setId(null);
        exchangeRateResponseDTO.setDate(LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat)));
        exchangeRateResponseDTO.setCurrencyBaseCode(currencyEURCode);
        exchangeRateResponseDTO.setCurrencyBaseAmount(baseAmount);
        exchangeRateResponseDTO.setCurrencyQuoteCode(currencyUSDCode);
        exchangeRateResponseDTO.setCurrencyQuoteAmount(quoteAmount);
    }

    @BeforeAll
    static void initialize() {
        initializeCurrencyDOUSD();
        initializeCurrencyDOEUR();
        initializeExchangeRateXMLOUSD();
        initializeExchangeRateDOUSD();
        initializeExchangeRateRequestDTO();
        initializeExchangeRateResponseDTO();
    }

    @BeforeEach
    void setMocksValues() {
        Mockito.when(currencyRepository.findByCode(currencyEURCode)).thenReturn(Optional.ofNullable(currencyEUR));
        Mockito.when(currencyRepository.findByCode(currencyUSDCode)).thenReturn(Optional.ofNullable(currencyUSD));
    }

    @Test
    void exchangeRateXMLOToExchangeRateDOTest() {
        ExchangeRateDO result = exchangeRateMapper.exchangeRateXMLOToExchangeRateDO(exchangeRateXMLO);
        Assertions.assertEquals(exchangeRateDO, result);
    }

    @Test
    void exchangeRateRequestDTOToExchangeRateDOTest() {
        ExchangeRateDO result = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRateRequestDTO);
        Assertions.assertEquals(exchangeRateDO, result);
    }

    @Test
    void exchangeRateDOToExchangeRateResponseDTOTest() {
        ExchangeRateResponseDTO result = exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateDO);
        Assertions.assertEquals(exchangeRateResponseDTO, result);
    }

    @Test
    void exchangeRateXMLOToExchangeRateDONullTest() {
        ExchangeRateDO result = exchangeRateMapper.exchangeRateXMLOToExchangeRateDO(null);
        Assertions.assertNull(result);
    }

    @Test
    void exchangeRateRequestDTOToExchangeRateDONullTest() {
        ExchangeRateDO result = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(null);
        Assertions.assertNull(result);
    }

    @Test
    void exchangeRateDOToExchangeRateResponseDTONullTest() {
        ExchangeRateResponseDTO result = exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(null);
        Assertions.assertNull(result);
    }
}
