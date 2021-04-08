package dto;

import org.springframework.web.multipart.MultipartFile;

public class ConfirmDoctorDTO {

	private String firstName;

	private String lastName;

	private String email;

	private String username;

	private String password;

	private String confirmPassword;

	private String nrIdentifikues;

	private int nrAnetarit;

	private String nrLicences;

	private MultipartFile file;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getNrIdentifikues() {
		return nrIdentifikues;
	}

	public void setNrIdentifikues(String nrIdentifikues) {
		this.nrIdentifikues = nrIdentifikues;
	}

	public int getNrAnetarit() {
		return nrAnetarit;
	}

	public void setNrAnetarit(int nrAnetarit) {
		this.nrAnetarit = nrAnetarit;
	}

	public String getNrLicences() {
		return nrLicences;
	}

	public void setNrLicences(String nrLicences) {
		this.nrLicences = nrLicences;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
