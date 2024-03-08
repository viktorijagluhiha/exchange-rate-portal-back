package lv.uroof.exchangerateportalback.logic.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external.api.central-bank")
@Getter
@Setter
public class CentralBankAPIProperties {
    @Data
    public static class Endpoints {
        private String getCurrencies;
        private String getExchangeRatesForDate;
        private String getExchangeRateForCurrencyDateRange;
    }

    private String url;

    private final Endpoints endpoints = new Endpoints();
}
