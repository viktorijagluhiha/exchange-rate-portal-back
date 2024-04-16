package lv.uroof.exchangerateportalback.entity.exchangerate;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExchangeRateResponseDTO {
    private Long id;

    private LocalDate date;
    private String currencyBaseCode;
    private String currencyQuoteCode;
    private BigDecimal currencyBaseAmount;
    private BigDecimal currencyQuoteAmount;
}
