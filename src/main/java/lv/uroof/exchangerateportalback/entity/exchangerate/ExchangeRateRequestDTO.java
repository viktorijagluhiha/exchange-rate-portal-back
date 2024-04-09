package lv.uroof.exchangerateportalback.entity.exchangerate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExchangeRateRequestDTO {
    @EqualsAndHashCode.Include
    @NotBlank
    private String code;

    private LocalDate date;
    private String currencyBaseCode;
    private String currencyQuoteCode;
    private BigDecimal currencyBaseAmount;
    private BigDecimal currencyQuoteAmount;
}
