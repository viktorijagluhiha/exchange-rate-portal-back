package lv.uroof.exchangerateportalback.entity.currency;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CurrencyRequestDTO {
    @NotBlank
    private String code;

    private String name;
    private Short numericCode;
    private Short minorUnits;
}
