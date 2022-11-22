package com.imdb.top250filmes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Treatment {

	static List<Movie> unityMovies(String json) {
		
		String[] moviesArray = parseJsonMovies(json);
		
		List<String> titles =  parseTitles(moviesArray);
		List<String> images =  parseUrlImages(moviesArray);
		List<String> rating =  parseRating(moviesArray);
		List<String> year =  parseYear(moviesArray);


		List<Movie> movies = new ArrayList<>(titles.size());
		
		for(int i=0; i < titles.size(); i++) {
			movies.add(new Movie(titles.get(i), images.get(i), rating.get(i), year.get(i)));
		}
		
		return movies;
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
