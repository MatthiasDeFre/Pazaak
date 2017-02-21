package domain;

import java.util.Vector;

public class Player {
	private DateTime _dateOfBirth;
	private String _name;
	private int _credits;
	private Vector<Card> _deck = new Vector<Card>();

	public Player(DateTime aDateOfBirth, String aName) {
		throw new UnsupportedOperationException();
	}

	public void makeStartdeck() {
		throw new UnsupportedOperationException();
	}

	public Card[] getDeck() {
		throw new UnsupportedOperationException();
	}

	public void setDateOfBirth(DateTime aDateOfBirth) {
		this._dateOfBirth = aDateOfBirth;
	}

	public DateTime getDateOfBirth() {
		return this._dateOfBirth;
	}

	public void setName(String aName) {
		this._name = aName;
	}

	public String getName() {
		return this._name;
	}

	public void setCredits(int aCredits) {
		this._credits = aCredits;
	}

	public int getCredits() {
		return this._credits;
	}
}