package lv.uroof.exchangerateportalback.entity.currency;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CurrencyRequestDTO {
    @EqualsAndHashCode.Include
    @NotBlank
    private String code;

    private String name;
    private Short numericCode;
    private Short minorUnits;
}
