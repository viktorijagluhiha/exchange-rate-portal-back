package lv.uroof.exchangerateportalback.entity.exchangerate;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyRepository;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateAmountWrapperXMLO;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class ExchangeRateMapper {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "currencyBase", ignore = true)
    @Mapping(target = "currencyQuote", ignore = true)
    @Mapping(target = "currencyBaseAmount", ignore = true)
    @Mapping(target = "currencyQuoteAmount", ignore = true)
    public abstract ExchangeRateDO exchangeRateXMLOToExchangeRateDO(ExchangeRateXMLO exchangeRateXMLO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currencyBase", ignore = true)
    @Mapping(target = "currencyQuote", ignore = true)
    public abstract ExchangeRateDO exchangeRateRequestDTOToExchangeRateDO(ExchangeRateRequestDTO exchangeRate);

    @Mapping(target = "currencyBaseCode", source = "currencyBase.code")
    @Mapping(target = "currencyQuoteCode", source = "currencyQuote.code")
    public abstract ExchangeRateResponseDTO exchangeRateDOToExchangeRateResponseDTO(ExchangeRateDO exchangeRateDO);

    @AfterMapping
    public void setExchangeRateCurrenciesAmounts(@MappingTarget ExchangeRateDO exchangeRateDO, ExchangeRateXMLO exchangeRateXMLO) {
        if (exchangeRateXMLO.getAmounts().size() != 2) {
            throw new RuntimeException("Invalid currencies count in exchange rate");
        }

        ExchangeRateAmountWrapperXMLO exchangeRateBaseAmount = exchangeRateXMLO
                .getAmounts()
                .stream()
                .filter(amount -> Objects.equals(amount.getAmount(), new BigDecimal(1)))
                .findFirst()
                .orElse(exchangeRateXMLO.getAmounts().get(0));

        exchangeRateDO.setCurrencyBase(currencyRepository.findByCode(
                exchangeRateBaseAmount.getCurrencyCode()
        ).orElseThrow(() -> new RuntimeException("Currency with code " + exchangeRateBaseAmount.getCurrencyCode() + " not found")));
        exchangeRateDO.setCurrencyBaseAmount(exchangeRateBaseAmount.getAmount());

        ExchangeRateAmountWrapperXMLO exchangeRateQuoteAmount = exchangeRateXMLO
                .getAmounts()
                .stream()
                .filter(amount -> !amount.getCurrencyCode().equals(exchangeRateBaseAmount.getCurrencyCode()))
                .findFirst()
                .orElse(exchangeRateXMLO.getAmounts().get(1));

        exchangeRateDO.setCurrencyQuote(currencyRepository.findByCode(
                exchangeRateQuoteAmount.getCurrencyCode()
        ).orElseThrow(() -> new RuntimeException("Currency with code " + exchangeRateQuoteAmount.getCurrencyCode() + " not found")));
        exchangeRateDO.setCurrencyQuoteAmount(exchangeRateQuoteAmount.getAmount());
    }

    @AfterMapping
    public void setExchangeRateCurrencies(@MappingTarget ExchangeRateDO exchangeRateDO, ExchangeRateRequestDTO exchangeRate) {
        exchangeRateDO.setCurrencyBase(currencyRepository.findByCode(
            exchangeRate.getCurrencyBaseCode()
        ).orElseThrow(() -> new RuntimeException("Currency with code " + exchangeRate.getCurrencyBaseCode() + " not found")));
        exchangeRateDO.setCurrencyQuote(currencyRepository.findByCode(
            exchangeRate.getCurrencyQuoteCode()
        ).orElseThrow(() -> new RuntimeException("Currency with code " + exchangeRate.getCurrencyQuoteCode() + " not found")));
    }
}
