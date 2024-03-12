package lv.uroof.exchangerateportalback.entity.exchangerate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.uroof.exchangerateportalback.entity.currency.CurrencyDO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "exchange_rates",
        indexes = {
                @Index(name = "i_date_currency_base_currency_quote", unique = true, columnList = "date, currency_base_id, currency_quote_id"),
                @Index(name = "i_currency_base_id", columnList = "currency_base_id"),
                @Index(name = "i_currency_quote_id", columnList = "currency_quote_id"),
        }
)
public class ExchangeRateDO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_base_id", nullable = false)
    private CurrencyDO currencyBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_quote_id", nullable = false)
    private CurrencyDO currencyQuote;

    @Column(name = "currency_base_amount", nullable = false, precision = 27, scale = 7)
    private BigDecimal currencyBaseAmount;

    @Column(name = "currency_quote_amount", nullable = false, precision = 27, scale = 7)
    private BigDecimal currencyQuoteAmount;
}
