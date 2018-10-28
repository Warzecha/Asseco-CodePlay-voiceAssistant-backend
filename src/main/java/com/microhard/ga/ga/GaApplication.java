package com.microhard.ga.ga;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microhard.ga.BasicDB;
import com.microhard.ga.models.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static com.microhard.ga.BasicDB.me;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class GaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaApplication.class, args);
    }

    public GaApplication() {
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Authorization", me.getAuth());
    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    BasicDB basicDB = new BasicDB();


    @PostMapping("/payments")
    public Payment makePayment(@RequestBody AssistantPayment assistantPayment) {

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Payment payment = new Payment(assistantPayment);
        try {
            new ObjectMapper().writeValue(new File("src/main/resources/file33.json"), payment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity<?> httpEntity = new HttpEntity<Object>(payment, httpHeaders);

        System.out.println(restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/payments/create_domestic_transfer", HttpMethod.POST, httpEntity, String.class).getBody());

        return payment;

    }

    @GetMapping("/balance")
    public Balance getBalance() {

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        String abc = "";
        abc = restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/account?customerId=" + me.getCustomerId() + "&accessProfileId=" + me.getAccessProfileId(), HttpMethod.GET, httpEntity, String.class).getBody();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = new JSONObject(abc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = (JSONObject) jsonObject.getJSONArray("content").get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return new Balance(jsonObject.get("accessibleAssets").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    @GetMapping("/stock")
    public Stock getStock() {
        return new Stock("47,30 PLN");
    }

    @GetMapping("/last")
    public LastTransaction getLastTransaction() {

        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String abc = "";

        abc = restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/transaction?accountId=" + me.getAccountID() + "&dateFrom=2018-06-17T10:52:43.589Z&dateTo=2018-11-17T10:52:43.589Z", HttpMethod.GET, httpEntity, String.class).getBody();


        JSONObject jsonObject = new JSONObject();

        String toWhom = "";
        String name = "";
        String cost = "";
        String currency = "";

        try {
            jsonObject = new JSONObject(abc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = (JSONObject) jsonObject.getJSONArray("content").get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            toWhom = jsonObject.getJSONArray("beneficiary").get(0).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            name = jsonObject.getJSONArray("description").get(0).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            cost = jsonObject.get("amount").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            currency = jsonObject.get("currency").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new LastTransaction(name, toWhom, cost, currency);

    }

}
