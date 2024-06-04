package lv.uroof.exchangerateportalback.entity.exchangerate;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyService;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper, CurrencyService currencyService) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    public ExchangeRateDO createExchangeRate(ExchangeRateXMLO exchangeRate) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateXMLOToExchangeRateDO(exchangeRate);

        return exchangeRateRepository
                .findByDateAndCurrencyBaseAndCurrencyQuote(
                        exchangeRateDO.getDate(),
                        exchangeRateDO.getCurrencyBase(),
                        exchangeRateDO.getCurrencyQuote()
                )
                .orElseGet(() -> exchangeRateRepository.save(exchangeRateDO));
    }

    public ExchangeRateDO createExchangeRate(ExchangeRateRequestDTO exchangeRate) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);

        return exchangeRateRepository
                .findByDateAndCurrencyBaseAndCurrencyQuote(
                        exchangeRateDO.getDate(),
                        exchangeRateDO.getCurrencyBase(),
                        exchangeRateDO.getCurrencyQuote()
                )
                .orElseGet(() -> exchangeRateRepository.save(exchangeRateDO));
    }

    public Optional<ExchangeRateDO> getExchangeRate(Long id) {
        return exchangeRateRepository
                .findById(id);
    }

    public Optional<ExchangeRateDO> getExchangeRateLastByCurrencyBaseAndCurrencyQuote(
            LocalDate date,
            CurrencyDO currencyBase,
            CurrencyDO currencyQuote
    ) {
        return exchangeRateRepository
                .findFirstByDateBeforeAndCurrencyBaseAndCurrencyQuoteOrderByDateDesc(
                        date,
                        currencyBase,
                        currencyQuote
                );
    }

    public Optional<ExchangeRateDO> getExchangeRateLastByCurrency(
            LocalDate date,
            CurrencyDO currency
    ) {
        return exchangeRateRepository
                .findFirstByDateBeforeAndCurrencyBaseOrCurrencyQuoteOrderByDateDesc(
                        date,
                        currency,
                        currency
                );
    }

    public Optional<ExchangeRateDO> updateExchangeRate(Long id, ExchangeRateRequestDTO exchangeRate) {
        return exchangeRateRepository
                .findById(id)
                .map(exchangeRateData -> {
                    ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);
                    exchangeRateDO.setId(id);

                    return exchangeRateRepository.save(exchangeRateDO);
                });
    }

    public Optional<Long> deleteExchangeRate(Long id) {
        return exchangeRateRepository
                .findById(id)
                .map(exchangeRateDO -> {
                    exchangeRateRepository.delete(exchangeRateDO);
                    return id;
                });
    }
}
