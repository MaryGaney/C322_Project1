package model;

public class Card {
    private final String emoji;
    private boolean faceUp;
    private boolean matched;

    public Card(String emoji) {
        this.emoji = emoji;
        this.faceUp = false;
        this.matched = false;
    }

    public String getEmoji() {
        return emoji;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isMatched() {
        return matched;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public void setMatched() {
        matched = true;
        faceUp = true;
    }
}

