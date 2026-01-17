package com.smartcart.productservice.commons;

import com.smartcart.productservice.dtos.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommon {
    private static RestTemplate restTemplate;

    public AuthCommon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static boolean validate(String tokenValue){
        UserDto userDto=restTemplate.getForObject("http://localhost:8080/users/validate/"+tokenValue,
                UserDto.class);
        if(userDto==null){
            return false;
        }
        return true;
    }
}
