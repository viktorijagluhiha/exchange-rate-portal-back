package lv.uroof.exchangerateportalback;

import lv.uroof.exchangerateportalback.logic.service.CentralBankAPIProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ExchangeRatePortalBackApplication {
    private final CentralBankAPIProperties centralBankAPIProperties;

    public ExchangeRatePortalBackApplication(CentralBankAPIProperties centralBankAPIProperties) {
        this.centralBankAPIProperties = centralBankAPIProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatePortalBackApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(centralBankAPIProperties.getUrl())
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(configurer ->
                                        configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder()))
                                .build()
                )
                .build();
    }
}
