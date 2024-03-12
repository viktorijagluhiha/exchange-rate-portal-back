package lv.uroof.exchangerateportalback.logic.service;

import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRatesXMLO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CentralBankAPIService {
    private final WebClient webClient;
    private final CentralBankAPIProperties centralBankAPIProperties;

    public enum ExchangeRateType {
        EU, LT
    }

    public CentralBankAPIService(WebClient webClient, CentralBankAPIProperties centralBankAPIProperties) {
        this.webClient = webClient;
        this.centralBankAPIProperties = centralBankAPIProperties;
    }

    public List<CurrencyXMLO> getCurrencies() {
        return webClient
                .get()
                .uri(centralBankAPIProperties.getEndpoints().getGetCurrencies())
                .retrieve()
                .bodyToMono(CurrenciesXMLO.class)
                .single()
                .map(CurrenciesXMLO::getCurrencies)
                .block();
    }

    public List<ExchangeRateXMLO> getExchangeRatesForCurrencyBetweenDates(String currencyCode, String dateFrom, String dateTo) {
        return webClient
                .get()
                .uri(builder -> builder
                        .path(centralBankAPIProperties.getEndpoints().getGetExchangeRateForCurrencyDateRange())
                        .queryParam("tp", ExchangeRateType.LT.name())
                        .queryParam("ccy", currencyCode)
                        .queryParam("dtFrom", dateFrom)
                        .queryParam("dtTo", dateTo)
                        .build()
                )
                .retrieve()
                .bodyToMono(ExchangeRatesXMLO.class)
                .single()
                .map(ExchangeRatesXMLO::getExchangeRates)
                .block();
    }
}
