package com.cargosmart.ita.demo2.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class SecurityProperties {

    private LoginProperties login = new LoginProperties();

}
