package lv.uroof.exchangerateportalback.entity.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyDO, Long> {
    Optional<CurrencyDO> findByCode(String code);
}
