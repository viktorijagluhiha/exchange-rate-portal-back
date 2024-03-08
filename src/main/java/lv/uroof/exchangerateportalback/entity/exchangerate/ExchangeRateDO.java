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
                @Index(name = "i_date_currency_from_currency_to", unique = true, columnList = "date, currency_from_id, currency_to_id"),
                @Index(name = "i_currency_from_id", columnList = "currency_from_id"),
                @Index(name = "i_currency_to_id", columnList = "currency_to_id"),
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
    @JoinColumn(name = "currency_from_id", nullable = false)
    private CurrencyDO currencyFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_to_id", nullable = false)
    private CurrencyDO currencyTo;

    @Column(name = "currency_from_amount", nullable = false, precision = 27, scale = 7)
    private BigDecimal currencyFromAmount;

    @Column(name = "currency_to_amount", nullable = false, precision = 27, scale = 7)
    private BigDecimal currencyToAmount;
}
