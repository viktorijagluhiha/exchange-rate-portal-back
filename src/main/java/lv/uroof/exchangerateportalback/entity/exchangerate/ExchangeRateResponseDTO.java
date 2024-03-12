package lv.uroof.exchangerateportalback.entity.exchangerate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExchangeRateResponseDTO {
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate date;
    private String currencyBaseCode;
    private String currencyQuoteCode;
    private BigDecimal currencyBaseAmount;
    private BigDecimal currencyQuoteAmount;
}
