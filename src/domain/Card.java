public class Card {
	private String type;
	private int value;

	public Card(String type, int value) {
		throw new UnsupportedOperationException();
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}