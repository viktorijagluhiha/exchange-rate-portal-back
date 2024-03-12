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
        Optional<CurrencyDO> currencyData = currencyRepository.findByCode(currency.getCode());
        if (currencyData.isPresent()) {
            return currencyMapper.currencyDOToCurrencyResponseDTO(currencyData.get());
        }

        CurrencyDO currencyDO = currencyMapper.currencyXMLOToCurrencyDO(currency);
        currencyRepository.save(currencyDO);

        return currencyMapper.currencyDOToCurrencyResponseDTO(currencyDO);
    }

    public CurrencyResponseDTO createCurrency(CurrencyRequestDTO currency) {
        Optional<CurrencyDO> currencyData = currencyRepository.findByCode(currency.getCode());
        if (currencyData.isPresent()) {
            return currencyMapper.currencyDOToCurrencyResponseDTO(currencyData.get());
        }

        CurrencyDO currencyDO = currencyMapper.currencyRequestDTOToCurrencyDO(currency);
        currencyRepository.save(currencyDO);

        return currencyMapper.currencyDOToCurrencyResponseDTO(currencyDO);
    }

    public CurrencyResponseDTO getCurrency(Long id) {
        Optional<CurrencyDO> currencyDO = currencyRepository.findById(id);
        return currencyDO.map(currencyMapper::currencyDOToCurrencyResponseDTO).orElse(null);
    }

    public CurrencyResponseDTO getCurrencyByCode(String code) {
        Optional<CurrencyDO> currencyDO = currencyRepository.findByCode(code);
        return currencyDO.map(currencyMapper::currencyDOToCurrencyResponseDTO).orElse(null);
    }

    public List<CurrencyResponseDTO> getCurrencies() {
        return currencyRepository
                .findAll()
                .stream()
                .map(currencyMapper::currencyDOToCurrencyResponseDTO)
                .collect(Collectors.toList());
    }

    public CurrencyResponseDTO updateCurrency(Long id, CurrencyRequestDTO currency) {
        Optional<CurrencyDO> currencyData = currencyRepository.findById(id);
        if (currencyData.isPresent()) {
            CurrencyDO currencyDO = currencyMapper.currencyRequestDTOToCurrencyDO(currency);
            currencyDO.setId(id);

            return currencyMapper.currencyDOToCurrencyResponseDTO(currencyRepository.save(currencyDO));
        } else {
            return null;
        }
    }

    public Long deleteCurrency(Long id) {
        Optional<CurrencyDO> currencyDO = currencyRepository.findById(id);
        if (currencyDO.isPresent()) {
            currencyRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
