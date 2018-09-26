package model;

public enum Gender {
	M("M"),
	F("F");
	String genre;
	
	private Gender(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}


