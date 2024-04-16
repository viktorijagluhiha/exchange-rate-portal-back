package lv.uroof.exchangerateportalback.entity.currency;

import lombok.Data;

@Data
public class CurrencyResponseDTO {
    private Long id;

    private String code;
    private String name;
    private Short numericCode;
    private Short minorUnits;
}
