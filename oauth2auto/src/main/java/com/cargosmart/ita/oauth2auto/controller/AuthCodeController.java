package com.cargosmart.ita.oauth2auto.controller;

import com.cargosmart.ita.oauth2auto.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.Objects;

@RestController
public class AuthCodeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getCode")
    public String getCode(@RequestParam("code") String code) throws UnsupportedEncodingException {
        System.out.println("authorization code: " + code);

        RequestEntity httpEntity = new RequestEntity<>(getHttpBody(code), getHttpHeaders(), HttpMethod.POST, URI.create("http://localhost:7000/oauth/token"));
        ResponseEntity<TokenDto> exchange = restTemplate.exchange(httpEntity, TokenDto.class);

        if (exchange.getStatusCode().is2xxSuccessful()) {
            System.err.println(exchange.getBody());
            return Objects.requireNonNull(exchange.getBody()).getAccessToken();
        }
        throw new RuntimeException("请求令牌失败！");

//        return "authorization code: " + code;
    }

    @GetMapping("/resource")
    public HttpEntity<?> resource(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    private MultiValueMap<String, String> getHttpBody(String code) throws UnsupportedEncodingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:7000/getCode");
        params.add("scope", "all");
        return params;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("oauth", "oauth");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
