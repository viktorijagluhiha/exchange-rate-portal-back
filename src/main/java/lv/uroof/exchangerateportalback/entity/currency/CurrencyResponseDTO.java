package lv.uroof.exchangerateportalback.entity.currency;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CurrencyResponseDTO {
    @EqualsAndHashCode.Include
    private Long id;

    private String code;
    private String name;
    private Short numericCode;
    private Short minorUnits;
}
