package lv.uroof.exchangerateportalback.entity.exchangerate;

import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateDO, Long> {
    Optional<ExchangeRateDO> findByDateAndCurrencyTo(LocalDate date, CurrencyDO currencyTo);
    List<ExchangeRateDO> findAllByCurrencyTo(CurrencyDO currencyTo);
    List<ExchangeRateDO> findAllByCurrencyFrom(CurrencyDO currencyFrom);
}