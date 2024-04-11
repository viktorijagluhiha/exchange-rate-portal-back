package lv.uroof.exchangerateportalback.entity.exchangerate;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyRepository;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyService;
import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;
    private final CurrencyRepository currencyRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper, CurrencyService currencyService, CurrencyRepository currencyRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
        this.currencyRepository = currencyRepository;
    }

    public ExchangeRateResponseDTO createExchangeRate(ExchangeRateXMLO exchangeRate) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateXMLOToExchangeRateDO(exchangeRate);

        ExchangeRateDO exchangeRateData = exchangeRateRepository
                .findByDateAndCurrencyBaseAndCurrencyQuote(
                        exchangeRateDO.getDate(),
                        exchangeRateDO.getCurrencyBase(),
                        exchangeRateDO.getCurrencyQuote()
                )
                .orElseGet(() -> exchangeRateRepository.save(exchangeRateDO));
        return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateData);
    }

    public ExchangeRateResponseDTO createExchangeRate(ExchangeRateRequestDTO exchangeRate) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);

        ExchangeRateDO exchangeRateData = exchangeRateRepository
                .findByDateAndCurrencyBaseAndCurrencyQuote(
                        exchangeRateDO.getDate(),
                        exchangeRateDO.getCurrencyBase(),
                        exchangeRateDO.getCurrencyQuote()
                )
                .orElseGet(() -> exchangeRateRepository.save(exchangeRateDO));
        return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateData);
    }

    public Optional<ExchangeRateResponseDTO> getExchangeRate(Long id) {
        return exchangeRateRepository
                .findById(id)
                .map(exchangeRateMapper::exchangeRateDOToExchangeRateResponseDTO);
    }

    public Optional<ExchangeRateResponseDTO> getExchangeRateLastByCurrencyBaseAndCurrencyQuote(
            LocalDate date,
            String currencyBaseCode,
            String currencyQuoteCode
    ) {
        return currencyRepository
                .findByCode(currencyBaseCode)
                .flatMap(currencyBaseDO -> currencyRepository
                        .findByCode(currencyQuoteCode)
                        .flatMap(currencyQuoteDO -> exchangeRateRepository
                                .findFirstByDateBeforeAndCurrencyBaseAndCurrencyQuoteOrderByDateDesc(
                                        date,
                                        currencyBaseDO,
                                        currencyQuoteDO
                                ))
                )
               .map(exchangeRateMapper::exchangeRateDOToExchangeRateResponseDTO);
    }

    public Optional<ExchangeRateResponseDTO> updateExchangeRate(Long id, ExchangeRateRequestDTO exchangeRate) {
        return exchangeRateRepository
                .findById(id)
                .map(exchangeRateData -> {
                    ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);
                    exchangeRateDO.setId(id);

                    return exchangeRateRepository.save(exchangeRateDO);
                })
                .map(exchangeRateMapper::exchangeRateDOToExchangeRateResponseDTO);
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
