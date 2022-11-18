package requisicao.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {

	public static void main(String[] args) {
		
		String apiKeyIMDB = System.getenv("ApiKeyIMDB");
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKeyIMDB);
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
			.newBuilder()
			.uri(apiIMDB)
			.build();
		
		String json = client.sendAsync(request, BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.join();
	
		System.out.println(json);
	}
}