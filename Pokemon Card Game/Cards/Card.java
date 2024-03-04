package Cards;

public class Card {
	
	private String name;	
	private String cardImageURL;
	
	public Card(String name, String cardImageURL) {
		this.name = name; 
		this.cardImageURL = cardImageURL; 
	}
	
	public String getName() {
        return name;
    }

    public String getCardImage() {
        return cardImageURL;
    }
}
