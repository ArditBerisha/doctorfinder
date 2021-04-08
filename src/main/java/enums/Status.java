package enums;

public enum Status {

	ACTIVE(true), INACTIVE(false);

	private final boolean value;

	Status(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

}
