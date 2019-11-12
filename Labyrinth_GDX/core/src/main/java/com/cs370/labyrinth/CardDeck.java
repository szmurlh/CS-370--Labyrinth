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
    
    Stack<Card> cardDeck;
    
    public CardDeck() {
        
        generateCardDeck();
        shuffleDeck();
    }
    
    public void generateCardDeck() {
        
        cardDeck = new Stack();
        
        Card batCard = new Card("bat");
        batCard.setCardImage("batCard.png");
        cardDeck.push(batCard);
        
        Card bugCard = new Card("bug");
        bugCard.setCardImage("beetleCard.png");
        cardDeck.push(bugCard);
        
        Card bookCard = new Card("book");
        bookCard.setCardImage("bookCard.png");
        cardDeck.push(bookCard);
        
        Card ringCard = new Card("ring");
        ringCard.setCardImage("ringCard.png");
        cardDeck.push(ringCard);
        
        Card skullCard = new Card("skull");
        skullCard.setCardImage("skullCard.png");
        cardDeck.push(skullCard);
        
        Card spiderCard = new Card("spider");
        spiderCard.setCardImage("spiderCard.png");
        cardDeck.push(spiderCard);
        
        Card candleCard = new Card("candle");
        candleCard.setCardImage("candleCard.png");
        cardDeck.push(candleCard);
        
        Card crownCard = new Card("crown");
        crownCard.setCardImage("crownCard.png");
        cardDeck.push(crownCard);
        
        Card dragonCard = new Card("dragon");
        dragonCard.setCardImage("dragonCard.png");
        cardDeck.push(dragonCard);
        
        Card lampCard = new Card("lamp");
        lampCard.setCardImage("lampCard.png");
        cardDeck.push(lampCard);
        
        Card ghostCard = new Card("ghost");
        ghostCard.setCardImage("ghostCard.png");
        cardDeck.push(ghostCard);
        
        Card knightCard = new Card("knight");
        knightCard.setCardImage("helmetCard.png");
        cardDeck.push(knightCard);
        
        Card gemCard = new Card("gem");
        gemCard.setCardImage("jewelCard.png");
        cardDeck.push(gemCard);
        
        Card swordCard = new Card("sword");
        swordCard.setCardImage("swordCard.png");
        cardDeck.push(swordCard);
        
        Card chestCard = new Card("chest");
        chestCard.setCardImage("treasureCard.png");
        cardDeck.push(chestCard);
        
        Card orcCard = new Card("orc");
        orcCard.setCardImage("trollCard.png");
        cardDeck.push(orcCard);
        
        Card keyCard = new Card("key");
        keyCard.setCardImage("dragonCard.png");
        cardDeck.push(keyCard);
        
        Card lizardCard = new Card("lizard");
        lizardCard.setCardImage("dragonCard.png");
        cardDeck.push(lizardCard);
        
        Card mapCard = new Card("map");
        mapCard.setCardImage("mapCard.png");
        cardDeck.push(mapCard);
        
        Card moneyCard = new Card("money");
        moneyCard.setCardImage("moneyCard.png");
        cardDeck.push(moneyCard);
        
        Card mothCard = new Card("moth");
        mothCard.setCardImage("mothCard.png");
        cardDeck.push(mothCard);
        
        Card owlCard = new Card("owl");
        owlCard.setCardImage("owlCard.png");
        cardDeck.push(owlCard);
        
        Card mouseCard = new Card("mouse");
        mouseCard.setCardImage("mouseCard.png");
        cardDeck.push(mouseCard);
        
        Card wizardCard = new Card("wizard");
        wizardCard.setCardImage("wizardCard.png");
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
    

