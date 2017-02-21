import java.time.LocalDateTime;
import java.util.ArrayList;

public class Player {
	private LocalDateTime dateOfBirth;
	private String name;
	private int credits;
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Player(LocalDateTime dateOfBirth, String name) {
		throw new UnsupportedOperationException();
	}

	public void makeStartdeck() {
		throw new UnsupportedOperationException();
	}

	public Card[] getDeck() {
		throw new UnsupportedOperationException();
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCredits() {
		return this.credits;
	}
}