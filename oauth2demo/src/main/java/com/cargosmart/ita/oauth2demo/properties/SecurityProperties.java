package com.cargosmart.ita.oauth2demo.properties;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhc
 * @date 2019/8/14
 */

@Configuration
@Data
public class SecurityProperties {

    private LoginProperties login = new LoginProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();

}
