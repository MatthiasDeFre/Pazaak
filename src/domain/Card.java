package domain;

public class Card {
	private String _type;
	private int _value;

	public Card(String aType, int aValue) {
		throw new UnsupportedOperationException();
	}

	public void setType(String aType) {
		this._type = aType;
	}

	public String getType() {
		return this._type;
	}

	public void setValue(int aValue) {
		this._value = aValue;
	}

	public int getValue() {
		return this._value;
	}
}