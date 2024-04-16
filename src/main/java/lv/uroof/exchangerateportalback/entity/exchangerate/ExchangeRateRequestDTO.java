package lv.uroof.exchangerateportalback.entity.exchangerate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExchangeRateRequestDTO {
    @NotBlank
    private String code;

    private LocalDate date;
    private String currencyBaseCode;
    private String currencyQuoteCode;
    private BigDecimal currencyBaseAmount;
    private BigDecimal currencyQuoteAmount;
}
