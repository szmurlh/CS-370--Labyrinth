/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs370.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Nick
 */
public class CardDeck {
    
    String spriteSheetFile;
    Stack<Card> cardDeck;
    
    public CardDeck(String spriteSheetFile) {
        
        this.spriteSheetFile = spriteSheetFile;
        
        generateCardDeck();
    }
    
    public void generateCardDeck() {
        
        cardDeck = new Stack();
        
        Card batCard = new Card("bat");
        batCard.setCardImage(spriteSheetFile, 4, 0);
        cardDeck.push(batCard);
        
        Card bugCard = new Card("bug");
        bugCard.setCardImage(spriteSheetFile, 5, 0);
        cardDeck.push(bugCard);
        
        Card bookCard = new Card("book");
        bookCard.setCardImage(spriteSheetFile, 6, 0);
        cardDeck.push(bookCard);
        
        Card ringCard = new Card("ring");
        ringCard.setCardImage(spriteSheetFile, 7, 0);
        cardDeck.push(ringCard);
        
        Card skullCard = new Card("skull");
        skullCard.setCardImage(spriteSheetFile, 8, 0);
        cardDeck.push(skullCard);
        
        Card spiderCard = new Card("spider");
        spiderCard.setCardImage(spriteSheetFile, 9, 0);
        cardDeck.push(spiderCard);
        
        Card candleCard = new Card("candle");
        candleCard.setCardImage(spriteSheetFile, 0, 1);
        cardDeck.push(candleCard);
        
        Card crownCard = new Card("crown");
        crownCard.setCardImage(spriteSheetFile, 1, 1);
        cardDeck.push(crownCard);
        
        Card dragonCard = new Card("dragon");
        dragonCard.setCardImage(spriteSheetFile, 2, 1);
        cardDeck.push(dragonCard);
        
        Card lampCard = new Card("lamp");
        lampCard.setCardImage(spriteSheetFile, 3, 1);
        cardDeck.push(lampCard);
        
        Card ghostCard = new Card("ghost");
        ghostCard.setCardImage(spriteSheetFile, 4, 1);
        cardDeck.push(ghostCard);
        
        Card knightCard = new Card("knight");
        knightCard.setCardImage(spriteSheetFile, 5, 1);
        cardDeck.push(knightCard);
        
        Card gemCard = new Card("gem");
        gemCard.setCardImage(spriteSheetFile, 6, 1);
        cardDeck.push(gemCard);
        
        Card swordCard = new Card("sword");
        swordCard.setCardImage(spriteSheetFile, 7, 1);
        cardDeck.push(swordCard);
        
        Card chestCard = new Card("chest");
        chestCard.setCardImage(spriteSheetFile, 8, 1);
        cardDeck.push(chestCard);
        
        Card orcCard = new Card("orc");
        orcCard.setCardImage(spriteSheetFile, 9, 1);
        cardDeck.push(orcCard);
        
        Card keyCard = new Card("key");
        keyCard.setCardImage(spriteSheetFile, 0, 2);
        cardDeck.push(keyCard);
        
        Card lizardCard = new Card("lizard");
        lizardCard.setCardImage(spriteSheetFile, 1, 2);
        cardDeck.push(lizardCard);
        
        Card mapCard = new Card("map");
        mapCard.setCardImage(spriteSheetFile, 2, 2);
        cardDeck.push(mapCard);
        
        Card moneyCard = new Card("money");
        moneyCard.setCardImage(spriteSheetFile, 3, 2);
        cardDeck.push(moneyCard);
        
        Card mothCard = new Card("moth");
        mothCard.setCardImage(spriteSheetFile, 4, 2);
        cardDeck.push(mothCard);
        
        Card owlCard = new Card("owl");
        owlCard.setCardImage(spriteSheetFile, 5, 2);
        cardDeck.push(owlCard);
        
        Card mouseCard = new Card("mouse");
        mouseCard.setCardImage(spriteSheetFile, 6, 2);
        cardDeck.push(mouseCard);
        
        Card wizardCard = new Card("wizard");
        wizardCard.setCardImage(spriteSheetFile, 7, 2);
        cardDeck.push(wizardCard);
        
    }
    
    public void shuffleDeck() {
        
        Collections.shuffle(cardDeck);
    }

    public Stack<Card> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(Stack<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }
   
}
    

