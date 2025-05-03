package kz.eubank.govtech.gbdul_affil_face_kdp_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Getter
@Setter
public class AppConfigurator {
    private String shepServiceId;
    private String shepSignKeyPath;
}
