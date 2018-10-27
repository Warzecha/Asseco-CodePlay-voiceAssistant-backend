package com.microhard.ga.controllers;

import com.microhard.ga.BasicDB;
import com.microhard.ga.models.AssistantPayment;
import com.microhard.ga.models.Payment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController("/payments")
public class PaymentController {

    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    public PaymentController() {

    }



}
