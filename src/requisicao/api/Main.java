package requisicao.api;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {

	public static void main(String[] args) throws URISyntaxException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
			.newBuilder()
			.uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("ApiKeyIMDB")))
			.GET()
			.build();
		
		client.sendAsync(request, BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenAccept(System.out::println)
			.join();
	
	}
}