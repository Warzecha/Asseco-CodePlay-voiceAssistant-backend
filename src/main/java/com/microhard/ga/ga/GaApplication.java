package com.microhard.ga.ga;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microhard.ga.BasicDB;
import com.microhard.ga.models.*;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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
		System.out.println("DUpa");

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		System.out.println(assistantPayment.getAmount());
		System.out.println(assistantPayment.getCurrency());
		System.out.println(assistantPayment.getDescription());


		Payment payment = new Payment(assistantPayment);
		try {
			new ObjectMapper().writeValue(new File("src/main/resources/file33.json"), payment);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpEntity<?> httpEntity = new HttpEntity<Object>(payment, httpHeaders);
//		restTemplate.postForEntity("https://team14.asseco.pl/retail-banking-swagger/api/payments/create_domestic_transfer", payment, Object.class);

		System.out.println(restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/payments/create_domestic_transfer", HttpMethod.POST,httpEntity,String.class).getBody());

		return payment;

	}

	@GetMapping("/balance")
	public Balance getBalance() {

		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		String abc = "";
		abc = restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/account?customerId=" + me.getCustomerId() + "&accessProfileId=" + me.getAccessProfileId(),HttpMethod.GET,httpEntity, String.class).getBody();

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject = new JSONObject(abc);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONArray jsonArray = new JSONArray();

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

//		return jsonArray.toString();
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
//		abc = restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/transaction?accountId=750879&dateFrom=2018-09-23T18%3A25%3A43.511Z&dateTo=2018-11-28T18%3A25%3A43.511Z",HttpMethod.GET,httpEntity, String.class).getBody();

		abc = restTemplate.exchange("https://team14.asseco.pl/retail-banking-swagger/api/transaction?accountId=" + me.getAccountID() + "&dateFrom=2018-06-17T10:52:43.589Z&dateTo=2018-11-17T10:52:43.589Z",HttpMethod.GET,httpEntity, String.class).getBody();


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

		JSONArray jsonArray = new JSONArray();

		try {
			jsonObject = (JSONObject) jsonObject.getJSONArray("content").get(0);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			toWhom = jsonObject.getJSONArray("beneficiary").get(0).toString();
//			toWhom = jsonObject.get("beneficiary").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			name = jsonObject.getJSONArray("description").get(0).toString();
//			name = jsonObject.get("description").toString();
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

		return new LastTransaction(name,toWhom,cost,currency);

//		return jsonArray.toString();
//		return null;
//		return abc;
	}


//		httpHeaders.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsicmViLWN1c3RvbWVyLXNlcnZpY2UiXSwidXNlcl9uYW1lIjoiTUIxQk1PNVIiLCJpZGVudGl0eSI6eyJvcmdfdW5pdCI6Im91PTk0NixvPVNHQi1CQU5LLGRjPUFDUCxkYz11ZmUsZGM9Y29tIiwicGVyc29uX2lkIjoiMzI4MDcyIiwicm9sZSI6IkNVU1RPTUVSIiwiYWNjZXNzX3Byb2ZpbGVfaWQiOiIyMzA3In0sInNjb3BlIjpbIndyaXRlIiwicmVhZCJdLCJpc3MiOiJpc3N1ZXIiLCJqdGkiOiJlYmY0ZGZmYS00ZWU2LTQ4NGUtODdiMy1hYzc5MDk3ZWRmZjUiLCJjbGllbnRfaWQiOiJyZWJSZXRhaWwiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXX0.cQRnONmhv4WR8p3tTSjXZXYo45Xt46R_OptL-2Nj2Mg");

//		String[] description = {"DUPA"};
//		String[] address = {"ul. 12, Krakow"};
//		String[] name = {"Jakis typ"};
//
//		Payment payment = new Payment(12,
//				"PLN",
//				description,
//				"80249000059984455911456320",
//				address,
//				name,
//				"750879");


}
