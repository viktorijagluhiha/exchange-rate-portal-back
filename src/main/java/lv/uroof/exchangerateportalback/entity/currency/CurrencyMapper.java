package lv.uroof.exchangerateportalback.entity.currency;

import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyNameTranslationXMLO;
import lv.uroof.exchangerateportalback.entity.currency.xml.CurrencyXMLO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CurrencyMapper {
    @Mapping(target = "name", expression = "java(nameTranslationsToString(currencyXMLO.getNameTranslations()))")
    public abstract CurrencyDO currencyXMLOToCurrencyDO(CurrencyXMLO currencyXMLO);

    public String nameTranslationsToString(List<CurrencyNameTranslationXMLO> nameTranslations) {
        return nameTranslations
                .stream()
                .filter(translation -> translation.getLanguageCode().equals("EN"))
                .findFirst()
                .map(CurrencyNameTranslationXMLO::getName)
                .orElse(null);
    }
}
