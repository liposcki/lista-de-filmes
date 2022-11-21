package requisicao.api;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main {

	public static void main(String[] args) {
		
		String apiKeyIMDB = System.getenv("ApiKeyIMDB");
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKeyIMDB);
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();
		
		String json = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
			
		String[] moviesArray = parseJsonMovies(json);
		System.out.println(moviesArray[0]);
		
		List<String> titles =  parseTitles(moviesArray);
		titles.forEach(System.out::println);
		
		List<String> images =  parseUrlImages(moviesArray);
		images.forEach(System.out::println);
		
		List<String> year =  parseYear(moviesArray);
		year.forEach(System.out::println);
		
		List<String> rating =  parseRating(moviesArray);
		rating.forEach(System.out::println);		

	}
	
	private static String[] parseJsonMovies (String json) {
		
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		
		if(!matcher.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}
		
		String[] moviesArray = matcher.group(1).split("\\},\\{");
		moviesArray[0] = moviesArray[0].substring(1);
		int last = moviesArray.length - 1;
		String lastString = moviesArray[last];
		moviesArray[last] = lastString.substring(0, lastString.length() - 1);
		return moviesArray;
				
	}
	
	private static List<String> parseAttribute (String[] moviesArray, int pos) {
		return Stream.of(moviesArray)
				.map(e -> e.split("\",\"")[pos])
				.map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"", ""))
				.collect(Collectors.toList());
	}
	
	
	private static List<String> parseTitles(String[] moviesArray){
		return parseAttribute(moviesArray, 2);
	}
	
	private static List<String> parseUrlImages(String[] moviesArray){
		return parseAttribute(moviesArray, 5);
	}
	
	private static List<String> parseYear(String[] moviesArray){
		return parseAttribute(moviesArray, 4);
	}
	
	private static List<String> parseRating(String[] moviesArray){
		return parseAttribute(moviesArray, 7);
	}
}