/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs370.labyrinth;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Nick
 */
public class BoardTest {
    
    private Board board;
    
    @Test
    public void testGameboard() {
      for(int counter = 0; counter < 1000; counter++) {  
        
        board = new Board();
        Assert.assertNotNull(board);
        
        String [] fixedTiles = new String [] {"blueTile", "redTile", "greenTile", "yellowTile", "bookTile",
            "moneyTile", "crownTile", "keyTile", "mapTile", "skullTile", "ringTile", "chestTile", "gemTile", 
            "swordTile", "candelTile", "knightTile"};
        
        String [] movableTiles = new String [] {"mothTile", "orcTile", "dragonTile", "bugTile", "wizardTile", "batTile", 
            "spiderTile", "ghostTile", "lizardTile", "owlTile", "mouseTile", "lampTile", "lTile0", "lTile1",
            "lTile2", "lTile3", "lTile4", "lTile5", "lTile6", "lTile7", "lTile8", "lTile9", "iTile0", "iTile1",
            "iTile2", "iTile3", "iTile4", "iTile5", "iTile6", "iTile7", "iTile8", "iTile9", "iTile10", "iTile11"};
        
        for(String tile: fixedTiles) {
            Assert.assertTrue(board.getTiles().containsKey(tile));
            Assert.assertNotNull(board.getTiles().get(tile));
        }
        
        for(String tile: movableTiles) {
            Assert.assertTrue(board.getTiles().containsKey(tile));
            Assert.assertNotNull(board.getTiles().get(tile));
        }
        
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                switch (i) {
                    case 0:
                        switch (j) {
                            case 0:
                                Assert.assertTrue(board.getTile(0, 0).equals(board.getTiles().get("greenTile")));
                                break;
                            case 2:
                                Assert.assertTrue(board.getTile(2, 0).equals(board.getTiles().get("candelTile")));
                                break;
                            case 4:
                                Assert.assertTrue(board.getTile(4, 0).equals(board.getTiles().get("knightTile")));
                                break;
                            case 6:
                                Assert.assertTrue(board.getTile(6, 0).equals(board.getTiles().get("blueTile")));
                                break;
                            default:
                                boolean tileIsMovable = false;
                                for(String tile: movableTiles) {
                                    if(board.getTile(j, i).equals(board.getTiles().get(tile))) {
                                        tileIsMovable = true;
                                        break;
                                    }
                                }
                                Assert.assertTrue(tileIsMovable);
                                break;
                        }   break;
                    case 2:
                        switch(j){
                            case 0:
                                Assert.assertTrue(board.getTile(0, 2).equals(board.getTiles().get("ringTile")));
                                break;
                            case 2:
                                Assert.assertTrue(board.getTile(2, 2).equals(board.getTiles().get("chestTile")));
                                break;
                            case 4:
                                Assert.assertTrue(board.getTile(4, 2).equals(board.getTiles().get("gemTile")));
                                break;
                            case 6:
                                Assert.assertTrue(board.getTile(6, 2).equals(board.getTiles().get("swordTile")));
                                break;
                            default:
                                boolean tileIsMovable = false;
                                for(String tile: movableTiles) {
                                    if(board.getTile(j, i).equals(board.getTiles().get(tile))) {
                                        tileIsMovable = true;
                                        break;
                                    }
                                }
                                Assert.assertTrue(tileIsMovable);
                                break;
                        }   break;
                    case 4:
                        switch(j){
                            case 0:
                                Assert.assertTrue(board.getTile(0, 4).equals(board.getTiles().get("mapTile")));
                                break;
                            case 2:
                                Assert.assertTrue(board.getTile(2, 4).equals(board.getTiles().get("crownTile")));
                                break;
                            case 4:
                                Assert.assertTrue(board.getTile(4, 4).equals(board.getTiles().get("keyTile")));
                                break;
                            case 6:
                                Assert.assertTrue(board.getTile(6, 4).equals(board.getTiles().get("skullTile")));
                                break;
                            default:
                                boolean tileIsMovable = false;
                                for(String tile: movableTiles) {
                                    if(board.getTile(j, i).equals(board.getTiles().get(tile))) {
                                        tileIsMovable = true;
                                        break;
                                    }
                                }
                                Assert.assertTrue(tileIsMovable);
                                break;    
                        }   break;
                    case 6:
                        switch(j){
                            case 0:
                                Assert.assertTrue(board.getTile(0, 6).equals(board.getTiles().get("yellowTile")));
                                break;
                            case 2:
                                Assert.assertTrue(board.getTile(2, 6).equals(board.getTiles().get("bookTile")));
                                break;
                            case 4:
                                Assert.assertTrue(board.getTile(4, 6).equals(board.getTiles().get("moneyTile")));
                                break;
                            case 6:
                                Assert.assertTrue(board.getTile(6, 6).equals(board.getTiles().get("redTile")));
                                break;
                            default:
//                                boolean tileIsMovable = false;
//                                for(String tile: movableTiles) {
//                                    System.out.println(tile);
//                                    System.out.println("i: " + i + "j: " + j);
//                                    if(board.getTile(j, i).equals(board.getTiles().get(tile))) {
//                                        tileIsMovable = true;
//                                        break;
//                                    }
//                                }
//                                Assert.assertTrue(tileIsMovable);
                                break;
                        }   break;
                    default: 
                        break;
                    }   
                
                }
            }
        }
    }
}
