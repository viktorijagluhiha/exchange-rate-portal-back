package lv.uroof.exchangerateportalback.logic.service;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyResponseDTO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyService;
import lv.uroof.exchangerateportalback.entity.exchangerate.ExchangeRateResponseDTO;
import lv.uroof.exchangerateportalback.entity.exchangerate.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataSyncService {
    private final CentralBankAPIService centralBankAPIService;

    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DataSyncService(CentralBankAPIService centralBankAPIService, CurrencyService currencyService, ExchangeRateService exchangeRateService) {
        this.centralBankAPIService = centralBankAPIService;
        this.currencyService = currencyService;
        this.exchangeRateService = exchangeRateService;
    }

    public List<CurrencyResponseDTO> synchronizeCurrencies() {
        return centralBankAPIService
                .getCurrencies()
                .stream()
                .map(currencyService::createCurrency)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateResponseDTO> loadExchangeRatesForCurrencyBetweenDates(String currencyCode, String dateFrom, String dateTo) {
        return centralBankAPIService
                .getExchangeRatesForCurrencyBetweenDates(currencyCode, dateFrom, dateTo)
                .stream()
                .map(exchangeRateService::createExchangeRate)
                .collect(Collectors.toList());
    }
}
