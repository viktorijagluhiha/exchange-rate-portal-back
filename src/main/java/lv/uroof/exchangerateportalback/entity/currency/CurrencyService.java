package lv.uroof.exchangerateportalback.entity.currency;

import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    public CurrencyDO createCurrency(CurrencyXMLO currency) {
        return currencyRepository
                .findByCode(currency.getCode())
                .orElseGet(() -> currencyRepository.save(
                        currencyMapper.currencyXMLOToCurrencyDO(currency)
                ));
    }

    public CurrencyDO createCurrency(CurrencyRequestDTO currency) {
        return currencyRepository
                .findByCode(currency.getCode())
                .orElseGet(() -> currencyRepository.save(
                        currencyMapper.currencyRequestDTOToCurrencyDO(currency)
                ));
    }

    public Optional<CurrencyDO> getCurrency(Long id) {
        return currencyRepository
                .findById(id);
    }

    public Optional<CurrencyDO> getCurrencyByCode(String code) {
        return currencyRepository
                .findByCode(code);
    }

    public List<CurrencyDO> getCurrencies() {
        return new ArrayList<>(currencyRepository
                .findAll());
    }

    public Optional<CurrencyDO> updateCurrency(Long id, CurrencyRequestDTO currency) {
        return currencyRepository
                .findById(id)
                .map(currencyData -> {
                    CurrencyDO currencyDO = currencyMapper.currencyRequestDTOToCurrencyDO(currency);
                    currencyDO.setId(id);

                    return currencyRepository.save(currencyDO);
                });
    }

    public Optional<Long> deleteCurrency(Long id) {
        return currencyRepository
                .findById(id)
                .map(currencyDO -> {
                    currencyRepository.delete(currencyDO);
                    return id;
                });
    }
}
