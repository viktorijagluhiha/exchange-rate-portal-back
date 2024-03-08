package lv.uroof.exchangerateportalback.entity.currency;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "currencies",
        indexes = {
                @Index(name = "i_code", unique = true, columnList = "code"),
        }
)
public class CurrencyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "numeric_code")
    private Short numericCode;

    @Column(name = "minor_units")
    private Short minorUnits;
}
