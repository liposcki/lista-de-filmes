package com.imdb.top250filmes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class Api {

	public static String RequestTop250MoviesIMDB(String apiKey) {

		//apiKey deve ser salva como variável de ambiente.
		
	String key = System.getenv(apiKey);
	URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + key);
	
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();
	
	String json = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
	
	return json;
	
	}
	
}
