package com.example.restClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddComment {

	public String addComment() throws IOException {
		
		/*
		 * URL of the REST API
		 * Replace yourHost and yourport with your hostname and port number
		 * Similary, for other user defined parameters such as MyService, rs, myService, myMethod
		 */
		String newUrl = "http://yourHost:yourPort/MyService/rs/myService/myMethod";
		
		URL url = new URL(newUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);

		// Method type of the REST API
		conn.setRequestMethod("POST");

		// Content type of the request/response
		conn.setRequestProperty("Content-Type", "application/json");

		// Request JSON to be sent to the REST API
		String input = "{\"body\": \"This is a dummy post comment. \",\"permalink\": \"nice_post\",\"author\": \"Simer\",\"email\": \"sahni.simer@gmail.com\"}";

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		Scanner scanner1;
		String response;
		
		// Check what is the response code after executing the REST API
		System.out.println("Response code is: " + conn.getResponseCode());

		// Check if your REST service executed successfully
		if (conn.getResponseCode() != 200) {
			scanner1 = new Scanner(conn.getErrorStream());
			response = "Error From Server \n\n";
		} else {
			scanner1 = new Scanner(conn.getInputStream());
			response = scanner1.next();

		}

		scanner1.useDelimiter("\\Z");
		response = scanner1.next();
		scanner1.close();
		conn.disconnect();

		// Response after successful execution of the REST API
		System.out.println(response);
		return response;
	}

	public static void main(String[] args) throws IOException {
		AddComment obj = new AddComment();
		System.out.println(obj.addComment());
	}

}
