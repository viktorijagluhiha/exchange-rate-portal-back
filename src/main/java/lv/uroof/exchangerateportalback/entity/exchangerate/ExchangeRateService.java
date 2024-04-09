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

        Optional<ExchangeRateDO> optionalExchangeRateDO = exchangeRateRepository.findByDateAndCurrencyBaseAndCurrencyQuote(
                exchangeRateDO.getDate(),
                exchangeRateDO.getCurrencyBase(),
                exchangeRateDO.getCurrencyQuote()
        );
        if (optionalExchangeRateDO.isPresent()) {
            return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(optionalExchangeRateDO.get());
        }

        exchangeRateRepository.save(exchangeRateDO);

        return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateDO);
    }

    public ExchangeRateResponseDTO createExchangeRate(ExchangeRateRequestDTO exchangeRate) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);

        Optional<ExchangeRateDO> optionalExchangeRateDO = exchangeRateRepository.findByDateAndCurrencyBaseAndCurrencyQuote(
                exchangeRateDO.getDate(),
                exchangeRateDO.getCurrencyBase(),
                exchangeRateDO.getCurrencyQuote()
        );
        if (optionalExchangeRateDO.isPresent()) {
            return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(optionalExchangeRateDO.get());
        }

        exchangeRateRepository.save(exchangeRateDO);

        return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateDO);
    }

    public ExchangeRateResponseDTO getExchangeRate(Long id) {
        Optional<ExchangeRateDO> exchangeRateDO = exchangeRateRepository.findById(id);
        return exchangeRateDO.map(exchangeRateMapper::exchangeRateDOToExchangeRateResponseDTO).orElse(null);
    }

    public ExchangeRateResponseDTO getExchangeRateLastByCurrencyBaseAndCurrencyQuote(
            LocalDate date,
            String currencyBaseCode,
            String currencyQuoteCode
    ) {
        Optional<CurrencyDO> currencyBaseDO = currencyRepository.findByCode(currencyBaseCode);
        Optional<CurrencyDO> currencyQuoteDO = currencyRepository.findByCode(currencyQuoteCode);

        if (currencyBaseDO.isPresent() && currencyQuoteDO.isPresent()) {
            Optional<ExchangeRateDO> exchangeRateDO = exchangeRateRepository.findFirstByDateAfterAndCurrencyBaseAndCurrencyQuoteOrderByDateDesc(
                    date,
                    currencyBaseDO.get(),
                    currencyQuoteDO.get()
            );
            return exchangeRateDO.map(exchangeRateMapper::exchangeRateDOToExchangeRateResponseDTO).orElse(null);
        }
        return null;
    }

    public ExchangeRateResponseDTO updateExchangeRate(Long id, ExchangeRateRequestDTO exchangeRate) {
        Optional<ExchangeRateDO> exchangeRateData = exchangeRateRepository.findById(id);
        if (exchangeRateData.isPresent()) {
            ExchangeRateDO exchangeRateDO = exchangeRateMapper.exchangeRateRequestDTOToExchangeRateDO(exchangeRate);
            exchangeRateDO.setId(id);

            return exchangeRateMapper.exchangeRateDOToExchangeRateResponseDTO(exchangeRateRepository.save(exchangeRateDO));
        }
        return null;
    }

    public Long deleteExchangeRate(Long id) {
        Optional<ExchangeRateDO> exchangeRateDO = exchangeRateRepository.findById(id);
        if (exchangeRateDO.isPresent()) {
            exchangeRateRepository.delete(exchangeRateDO.get());
            return id;
        }
        return null;
    }
}
