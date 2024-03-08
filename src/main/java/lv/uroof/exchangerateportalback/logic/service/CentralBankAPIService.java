package lv.uroof.exchangerateportalback.logic.service;

import lv.uroof.exchangerateportalback.entity.currency.xml.CurrenciesXMLO;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CentralBankAPIService {
    private final WebClient webClient;
    private final CentralBankAPIProperties centralBankAPIProperties;

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
}
