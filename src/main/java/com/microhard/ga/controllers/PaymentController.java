package com.microhard.ga.controllers;

import com.microhard.ga.models.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller("/payments")
public class PaymentController {

    private RestTemplate restTemplate;

    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public void makePayment() {
        restTemplate.postForEntity("https://team14.asseco.pl/retail-banking-swagger/api/payments/create_domestic_transfer",
                new Payment(),
                HttpStatus.OK);
    }

}
