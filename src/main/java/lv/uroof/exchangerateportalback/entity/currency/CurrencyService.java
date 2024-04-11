package lv.uroof.exchangerateportalback.entity.currency;

import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    public CurrencyResponseDTO createCurrency(CurrencyXMLO currency) {
        CurrencyDO currencyData = currencyRepository
                .findByCode(currency.getCode())
                .orElseGet(() -> currencyRepository.save(
                        currencyMapper.currencyXMLOToCurrencyDO(currency)
                ));
        return currencyMapper.currencyDOToCurrencyResponseDTO(currencyData);
    }

    public CurrencyResponseDTO createCurrency(CurrencyRequestDTO currency) {
        CurrencyDO currencyData = currencyRepository
                .findByCode(currency.getCode())
                .orElseGet(() -> currencyRepository.save(
                        currencyMapper.currencyRequestDTOToCurrencyDO(currency)
                ));
        return currencyMapper.currencyDOToCurrencyResponseDTO(currencyData);
    }

    public Optional<CurrencyResponseDTO> getCurrency(Long id) {
        return currencyRepository
                .findById(id)
                .map(currencyMapper::currencyDOToCurrencyResponseDTO);
    }

    public Optional<CurrencyResponseDTO> getCurrencyByCode(String code) {
        return currencyRepository
                .findByCode(code)
                .map(currencyMapper::currencyDOToCurrencyResponseDTO);
    }

    public List<CurrencyResponseDTO> getCurrencies() {
        return currencyRepository
                .findAll()
                .stream()
                .map(currencyMapper::currencyDOToCurrencyResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CurrencyResponseDTO> updateCurrency(Long id, CurrencyRequestDTO currency) {
        return currencyRepository
                .findById(id)
                .map(currencyData -> {
                    CurrencyDO currencyDO = currencyMapper.currencyRequestDTOToCurrencyDO(currency);
                    currencyDO.setId(id);

                    return currencyRepository.save(currencyDO);
                })
                .map(currencyMapper::currencyDOToCurrencyResponseDTO);
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
