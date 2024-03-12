package lv.uroof.exchangerateportalback.entity.exchangerate;

import lv.uroof.exchangerateportalback.entity.exchangerate.xml.ExchangeRateXMLO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
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
}
