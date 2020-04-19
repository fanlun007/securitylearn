package com.cargosmart.ita.oauth2demo.properties;



import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;

@Data
@EqualsAndHashCode
public class OAuth2Properties {

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    /**
     * jwt的签名
     */
    private String jwtSigningKey = "oauth2";

    /**
     * socialFilter 拦截地址
     */
    private String filterProcessesUrl = "/login";
}