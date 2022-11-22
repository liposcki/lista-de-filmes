package com.imdb.top250filmes;

public class Movie {

	private String title;
	private String urlImage;
	private String rating;
	private String year;
	
	public Movie (String title, String urlImage, String rating, String year) {
		this.title = title;
		this.urlImage = urlImage;
		this.rating = rating;
		this.year = year;
	}
	
	public String getTitle() {	
		return this.title;
	}
	
	public void setTitle(String title) {
		
		if(title != null) {
			System.out.println("O título não pode ser nulo/vazio.");
			return;
		}
		
		this.title = title;
	}
	
	public String getUrlImage() {
		return this.urlImage;
	}
	
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public String getRating() {
		return this.rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

}
