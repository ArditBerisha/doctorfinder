package dto;

import org.springframework.web.multipart.MultipartFile;

public class UsersPhoto {
	
	private MultipartFile file;
	
	private String biography;
	
	public UsersPhoto() {
		
	}

	public UsersPhoto(MultipartFile file, String biography) {
		this.file = file;
		this.biography = biography;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
}
