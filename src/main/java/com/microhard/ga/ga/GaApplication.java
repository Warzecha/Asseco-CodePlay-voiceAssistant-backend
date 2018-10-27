package com.microhard.ga.ga;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class GaApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GaApplication.class, args);

		String jsonString = "";

		try {
			jsonString = readFile("src/main/resources/file.json", StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject output;
		try {
			output = new JSONObject(jsonString);


			JSONArray docs = output.getJSONArray("content");


			File file = new File("src/main/resources/fromJSON.csv");
			String csv = CDL.toString(docs);
			FileUtils.writeStringToFile(file, csv);
//			System.out.println(csv);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static String readFile(String path, Charset encoding) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
