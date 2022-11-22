package com.imdb.top250filmes;

import java.util.List;


public class Main {

	public static void main(String[] args) {
		
		// ApiKeyIMDB é uma variável de ambiente, criada para omitir a chave real.
		
		String json = Api.RequestTop250MoviesIMDB("ApiKeyIMDB");
	
		List<Movie> movies = Treatment.unityMovies(json);
			
		for(int i=0; i < movies.size(); i++) {
			System.out.println("Nota: " + movies.get(i).getRating() + " Título: " + movies.get(i).getTitle() + " Lançamento: " + movies.get(i).getYear() + " Url Imagem: " + movies.get(i).getUrlImage());
		}

	}

}