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

    @Data
    public static class Dates {
        private String format;
        private String start;
    }

    private String url;

    private final Endpoints endpoints = new Endpoints();
    private final Dates dates = new Dates();
}
