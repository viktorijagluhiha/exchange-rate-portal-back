package lv.uroof.exchangerateportalback.logic.service;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyService;
import lv.uroof.exchangerateportalback.entity.exchangerate.ExchangeRateDO;
import lv.uroof.exchangerateportalback.entity.exchangerate.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataSyncService {
    private final CentralBankAPIService centralBankAPIService;

    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    private List<CurrencyDO> currencies;

    public DataSyncService(CentralBankAPIService centralBankAPIService, CurrencyService currencyService, ExchangeRateService exchangeRateService) {
        this.centralBankAPIService = centralBankAPIService;
        this.currencyService = currencyService;
        this.exchangeRateService = exchangeRateService;
    }

    public void synchronizeAllData() {
        this.currencies
                .parallelStream()
                .forEach((currencyDO) -> {
                    Optional<ExchangeRateDO> exchangeRateLast = exchangeRateService.getExchangeRateLastByCurrency(
                            LocalDate.now(),
                            currencyDO
                    );
                    centralBankAPIService
                            .getExchangeRatesForCurrencyBetweenDates(
                                    currencyDO,
                                    exchangeRateLast.map(ExchangeRateDO::getDate),
                                    LocalDate.now()
                            )
                            .parallelStream()
                            .forEach(exchangeRateService::createExchangeRate);
                });
    }

    public void synchronizeCurrencies() {
        this.currencies = centralBankAPIService
                .getCurrencies()
                .parallelStream()
                .map(currencyService::createCurrency)
                .collect(Collectors.toList());
    }
}
