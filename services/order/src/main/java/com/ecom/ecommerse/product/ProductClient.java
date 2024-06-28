package com.ecom.ecommerse.product;

import com.ecom.ecommerse.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> requestBody){
        log.info("--{}--",productUrl);
        HttpHeaders headers= new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestEntity= new HttpEntity<>(requestBody,headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<List<PurchaseResponse>>() {};

        log.info("response type --{}--",responseType);
        ResponseEntity<List<PurchaseResponse>> responseEntity=restTemplate.exchange(
                productUrl+"/purchase",
                POST,
                requestEntity,
                responseType
        );

        if(responseEntity.getStatusCode().isError()){
            throw  new BusinessException("An error occured while purchaee product "+responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
