/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs370.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Nick
 */
public class Card {
    
    Texture spriteSheet;
    Sprite cardImage;
    String cardItem;
    
    boolean cardInDeck;
    boolean cardFlipped;
    boolean cardPlacedDown;
    
    public Card(String cardItem) {
        
        this.cardItem = cardItem;
        this.cardInDeck = true;
        this.cardFlipped = false;
        this.cardPlacedDown = false;
    }

    public Sprite getCardImage() {
        return cardImage;
    }

    public void setCardImage(String spriteSheetFile) {

        spriteSheet = new Texture(spriteSheetFile);
        
        if(spriteSheet != null) {
            Sprite cardImage = new Sprite(spriteSheet, 2, 0, 232, 398);
            this.cardImage = cardImage;
        }
    }

    public String getCardItem() {
        return cardItem;
    }

    public void setCardItem(String cardItem) {
        this.cardItem = cardItem;
    }

    public boolean isCardInDeck() {
        return cardInDeck;
    }

    public void setCardInDeck(boolean cardInDeck) {
        this.cardInDeck = cardInDeck;
    }

    public boolean isCardFlipped() {
        return cardFlipped;
    }

    public void setCardFlipped(boolean cardFlipped) {
        this.cardFlipped = cardFlipped;
    }

    public boolean isCardPlacedDown() {
        return cardPlacedDown;
    }

    public void setCardPlacedDown(boolean cardPlacedDown) {
        this.cardPlacedDown = cardPlacedDown;
    }
    
}
