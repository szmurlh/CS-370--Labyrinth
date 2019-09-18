/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs370.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Nick
 */

public class Board {
    
    private Texture spriteSheet;
    private HashMap tiles;
    private Tile [][] grid;
    private Tile nextTile;
  
    public Board() {
        
        spriteSheet = new Texture("spritesheet_small.png");
        
        nextTile = new Tile(true, true, true, true);
        nextTile.setTileImage(spriteSheet, 0, 0);
        
        tiles = new HashMap();
        grid = new Tile [7][7];

        generateAllTiles();
        populateFixedTileGrid();
        populateMovableTileGrid();
    }
    
    
    public Tile getTile(int row, int col) {

        Tile tile = grid[col][row];
        
       return tile;
    }
    
    public void pushTileUp(int col) {
        Tile poppedTile = grid[6][col];
        
        grid[6][col] = grid[5][col];
        
        grid[5][col] = grid[4][col];
        
        grid[4][col] = grid[3][col];
        
        grid[3][col] = grid[2][col];
        
        grid[2][col] = grid[1][col];
        
        grid[1][col] = grid[0][col];
        
        grid[0][col] = nextTile;
        
        nextTile = poppedTile;
        
    }
    
    public void rotateBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = i; j < (6 - i); j++) {
                 Tile copy = grid[i][j];
                 
                 grid[i][j] = grid[j][6 - i];
                 grid[i][j].rotateTileRight();
                 
                 grid[j][6-i] = grid[6 - i][6 - j];
                 grid[j][6-i].rotateTileRight();
                 
                 grid[6-i][6-j] = grid[6-j][i];
                 grid[6-i][6-j].rotateTileRight();

                 grid[6-j][i] = copy;
                 grid[6-j][i].rotateTileRight();
            }
        }
        grid[3][3].rotateTileRight();
    }

    private void populateMovableTileGrid() {
 
        Collection<Tile> tileHashMapValues = tiles.values();
        ArrayList<Tile> tileArrayList = new ArrayList<Tile>(tileHashMapValues);
        
        ArrayList<Tile> movableItemTiles = new ArrayList<Tile>();
        
        //assign movableItemTilesArray the movable item tiles in the tileArrayList
        for(Tile tile : tileArrayList) {
            if(!tile.isFixedTile() && tile.getItem() != null) {
                movableItemTiles.add(tile);
            }
        }
        
        //counters to determine tiles remaining
        int numOfLTiles = 9;
        int numOfITiles = 11;
        int numOfItemTiles = movableItemTiles.size();

        //randomly assign movable tiles to the grid
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(i == 1 || i == 3 || i == 5 || j == 1 || j == 3 || j == 5) {

                    Tile tile = new Tile();   
                    int randomTileNumber = (int) (Math.random() * 10) % 3;
                    int randomRotationNumber = (int) (Math.random() * 10) % 4;
                    
                    switch(randomTileNumber) {
                        
                        case 0 : {
                            if(numOfLTiles >= 0) {
                                tile = (Tile) tiles.get("lTile" + numOfLTiles);
                                numOfLTiles--;
                            }    
                            else if(numOfITiles >= 0) {
                                tile = (Tile) tiles.get("iTile" + numOfITiles);
                                numOfITiles--;
                            }
                            else if(numOfItemTiles > 0) {
                                tile = (Tile) movableItemTiles.get(0);
                                movableItemTiles.remove(0);
                                movableItemTiles.trimToSize();
                                numOfItemTiles = movableItemTiles.size();
                            }
                            break;
                        }
                        
                        case 1 : {
                            if(numOfItemTiles > 0) {
                                tile = (Tile) movableItemTiles.get(0);
                                movableItemTiles.remove(0);
                                movableItemTiles.trimToSize();
                                numOfItemTiles = movableItemTiles.size();
                            }
                            else if(numOfLTiles > 0) {
                                tile = (Tile) tiles.get("lTile" + numOfLTiles);
                                numOfLTiles--;
                            }
                            else if(numOfITiles > 0) {
                                tile = (Tile) tiles.get("iTile" + numOfITiles);
                                numOfITiles--;
                            }
                            
                            break;
                        }
                        
                        case 2 : {
                            if(numOfITiles > 0) {
                                tile = (Tile) tiles.get("iTile" + numOfITiles);
                                numOfITiles--;
                            }
                            else if(numOfItemTiles > 0) {
                                tile = (Tile) movableItemTiles.get(0);
                                movableItemTiles.remove(0);
                                movableItemTiles.trimToSize();
                                numOfItemTiles = movableItemTiles.size();
                            }
                            else if(numOfLTiles > 0) {
                               tile = (Tile) tiles.get("lTile" + numOfLTiles);
                               numOfLTiles--;
                            }
                            break;
                        }
                    }

                    //random tile rotation
                    while(randomRotationNumber > 0) {
                        tile.rotateTileRight();
                        randomRotationNumber--;
                    }
                    
                    grid[i][j] = tile;
                }
            }
        }   
     }    
    
    private void populateFixedTileGrid() {
        
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Tile tile = new Tile(); 
        
                if(i == 6 && j == 0) {
                    tile = (Tile) tiles.get("yellowTile");
                }
                
                else if(i == 0 && j == 0) {
                    tile = (Tile) tiles.get("greenTile");
                }
                
                else if(i == 6 && j == 6) {
                    tile = (Tile) tiles.get("redTile");
                }
                
                else if(i == 0 && j == 6) {
                    tile = (Tile) tiles.get("blueTile");
                }
                
                else if(i == 0 && j == 2) {
                    tile = (Tile) tiles.get("candelTile");
                }
                
                else if(i == 0 && j == 4) {
                    tile = (Tile) tiles.get("knightTile");
                }
                
                else if(i == 2 && j == 0) {
                    tile = (Tile) tiles.get("ringTile");
                    tile.rotateTileRight();
                }
                
                else if(i == 4 && j == 0) {
                    tile = (Tile) tiles.get("mapTile");
                    tile.rotateTileRight();
                }
                
                else if(i == 6 && j == 2) {
                    tile = (Tile) tiles.get("bookTile");
                    tile.rotateTileRight();
                    tile.rotateTileRight();
                }
                
                else if(i == 6 && j == 4) {
                    tile = (Tile) tiles.get("moneyTile");
                    tile.rotateTileRight();
                    tile.rotateTileRight();
                }
                
                else if(i == 4 && j == 6) {
                    tile = (Tile) tiles.get("skullTile");
                    tile.rotateTileLeft();
                }
                
                else if(i == 2 && j == 6) {
                    tile = (Tile) tiles.get("swordTile");
                    tile.rotateTileLeft();
                }
                
                else if(i == 2 && j ==2) {
                    tile = (Tile) tiles.get("chestTile");
                }
                else if(i == 4 && j ==2) {
                    tile = (Tile) tiles.get("crownTile");
                    tile.rotateTileRight();
                }
                else if(i == 4 && j ==4) {
                    tile = (Tile) tiles.get("keyTile");
                    tile.rotateTileRight();
                    tile.rotateTileRight();
                }
                else if(i == 2 && j ==4) {
                    tile = (Tile) tiles.get("gemTile");
                    tile.rotateTileLeft();
                }
            
                grid[i][j] = tile;
            }       
        }       
    }
    
    private void generateAllTiles() {

        //blue tile creation
        Tile blueTile = new Tile(true, false, true, true);
        blueTile.setFixedTile(true);
        blueTile.setTileImage(spriteSheet, 0, 0);
        

        tiles.put("blueTile", blueTile);
        
        //green tile creation
        Tile greenTile = new Tile(false, true, true, false);
        greenTile.setFixedTile(true);
        greenTile.setTileImage(spriteSheet, 1, 0);

        tiles.put("greenTile", greenTile);
        
        //red tile creation
        Tile redTile = new Tile(true, false, false, true);
        redTile.setFixedTile(true);
        redTile.setTileImage(spriteSheet, 2, 0);

        tiles.put("redTile", redTile);
        
        //yellow tile creation
        Tile yellowTile = new Tile(false, true, false, true);
        yellowTile.setFixedTile(true);
        yellowTile.setTileImage(spriteSheet, 3, 0);

        tiles.put("yellowTile", yellowTile);
        
        //bat tile creation
        Tile batTile = new Tile(true, true, true, false);
        batTile.setFixedTile(false);
        batTile.setItem("bat");
        batTile.setTileImage(spriteSheet, 4, 0);
        
        tiles.put("batTile", batTile);
        
        //bug tile creation
        Tile bugTile = new Tile(false, true, true, false);
        bugTile.setFixedTile(false);
        bugTile.setItem("bug");
        bugTile.setTileImage(spriteSheet, 5, 0);
        
        tiles.put("bugTile", bugTile);
        
        //book tile creation
        Tile bookTile = new Tile(true, true, true, false);
        bookTile.setFixedTile(true);
        bookTile.setItem("book");
        bookTile.setTileImage(spriteSheet, 6, 0);
        
        tiles.put("bookTile", bookTile);
        
        //ring tile creation
        Tile ringTile = new Tile(true, true, true, false);
        ringTile.setFixedTile(true);
        ringTile.setItem("ring");
        ringTile.setTileImage(spriteSheet, 7, 0);
        
        tiles.put("ringTile", ringTile);
        
        //skull tile creation
        Tile skullTile = new Tile(true, true, true, false);
        skullTile.setFixedTile(true);
        skullTile.setItem("skull");
        skullTile.setTileImage(spriteSheet, 8, 0);
        
        tiles.put("skullTile", skullTile);
        
        //spider tile creation
        Tile spiderTile = new Tile(false, true, true, false);
        spiderTile.setFixedTile(false);
        spiderTile.setItem("spider");
        spiderTile.setTileImage(spriteSheet, 9, 0);
        
        tiles.put("spiderTile", spiderTile);
      
        //candel tile creation
        Tile candelTile = new Tile(true, true, true, false);
        candelTile.setFixedTile(true);
        candelTile.setItem("candel");
        candelTile.setTileImage(spriteSheet, 0, 1);
        
        tiles.put("candelTile", candelTile);
        
        //crown tile creation
        Tile crownTile = new Tile(true, true, true, false);
        crownTile.setFixedTile(true);
        crownTile.setItem("crown");
        crownTile.setTileImage(spriteSheet, 1, 1);
        
        tiles.put("crownTile", crownTile);
        
        //dragon tile creation
        Tile dragonTile = new Tile(true, true, true, false);
        dragonTile.setFixedTile(false);
        dragonTile.setItem("dragon");
        dragonTile.setTileImage(spriteSheet, 2, 1);
        
        tiles.put("dragonTile", dragonTile);
        
        //lamp tile creation
        Tile lampTile = new Tile(true, true, true, false);
        lampTile.setFixedTile(false);
        lampTile.setItem("lamp");
        lampTile.setTileImage(spriteSheet, 3, 1);
        
        tiles.put("lampTile", lampTile);
        
        //ghost tile creation
        Tile ghostTile = new Tile(true, true, true, false);
        ghostTile.setFixedTile(false);
        ghostTile.setItem("ghost");
        ghostTile.setTileImage(spriteSheet, 4, 1);
        
        tiles.put("ghostTile", ghostTile);
        
        //knight tile creation
        Tile knightTile = new Tile(true, true, true, false);
        knightTile.setFixedTile(true);
        knightTile.setItem("knight");
        knightTile.setTileImage(spriteSheet, 5, 1);
        
        tiles.put("knightTile", knightTile);
        
        //gem tile creation
        Tile gemTile = new Tile(true, true, true, false);
        gemTile.setFixedTile(true);
        gemTile.setItem("gem");
        gemTile.setTileImage(spriteSheet, 6, 1);
        
        tiles.put("gemTile", gemTile);
        
        //sword tile creation
        Tile swordTile = new Tile(true, true, true, false);
        swordTile.setFixedTile(true);
        swordTile.setItem("sword");
        swordTile.setTileImage(spriteSheet, 7, 1);
       
        tiles.put("swordTile", swordTile);
        
        //chest tile creation
        Tile chestTile = new Tile(true, true, true, false);
        chestTile.setFixedTile(true);
        chestTile.setItem("chest");
        chestTile.setTileImage(spriteSheet, 8, 1);
        
        tiles.put("chestTile", chestTile);
        
        //orc tile creation
        Tile orcTile = new Tile(true, true, true, false);
        orcTile.setFixedTile(false);
        orcTile.setItem("orc");
        orcTile.setTileImage(spriteSheet, 9, 1);
        
        tiles.put("orcTile", orcTile);
        
        //key tile creation
        Tile keyTile = new Tile(true, true, true, false);
        keyTile.setFixedTile(true);
        keyTile.setItem("key");
        keyTile.setTileImage(spriteSheet, 0, 2);
        
        tiles.put("keyTile", keyTile);
        
        //lizard tile creation
        Tile lizardTile = new Tile(false, true, true, false);
        lizardTile.setFixedTile(false);
        lizardTile.setItem("lizard");
        lizardTile.setTileImage(spriteSheet, 1, 2);
        
        tiles.put("lizardTile", lizardTile);
        
        //map tile creation
        Tile mapTile = new Tile(true, true, true, false);
        mapTile.setFixedTile(true);
        mapTile.setItem("map");
        mapTile.setTileImage(spriteSheet, 2, 2);
        
        tiles.put("mapTile", mapTile);
        
        //money tile creation
        Tile moneyTile = new Tile(true, true, true, false);
        moneyTile.setFixedTile(true);
        moneyTile.setItem("money");
        moneyTile.setTileImage(spriteSheet, 3, 2);
        
        tiles.put("moneyTile", moneyTile);
        
        //moth tile creation
        Tile mothTile = new Tile(false, true, true, false);
        mothTile.setFixedTile(false);
        mothTile.setItem("moth");
        mothTile.setTileImage(spriteSheet, 4, 2);
        
        tiles.put("mothTile", mothTile);
        
        //owl tile creation
        Tile owlTile = new Tile(false, true, true, false);
        owlTile.setFixedTile(false);
        owlTile.setItem("owl");
        owlTile.setTileImage(spriteSheet, 5, 2);
        
        tiles.put("owlTile", owlTile);
        
        //mouse tile creation
        Tile mouseTile = new Tile(false, true, true, false);
        mouseTile.setFixedTile(false);
        mouseTile.setItem("mouse");
        mouseTile.setTileImage(spriteSheet, 6, 2);
        
        tiles.put("mouseTile", mouseTile);
        
        //wizard tile creation
        Tile wizardTile = new Tile(true, true, true, false);
        wizardTile.setFixedTile(false);
        wizardTile.setItem("wizard");
        wizardTile.setTileImage(spriteSheet, 7, 2);
        
        tiles.put("wizardTile", wizardTile);
        
        //i tile creation X 12
        for(int i = 0; i < 12; i++) {
            Tile iTile = new Tile(false, false, true, true);
            iTile.setFixedTile(false);
            iTile.setTileImage(spriteSheet, 8, 2);
        
            tiles.put("iTile" + i, iTile);
        }
        
        //L tile creation X 10
        for(int i = 0; i < 10; i++) {
            Tile lTile = new Tile(false, true, true, false);
            lTile.setFixedTile(false);
            lTile.setTileImage(spriteSheet, 9, 2);
        
            tiles.put("lTile" + i, lTile);
        }    
    } 
    
    public Tile getNextTile() {
        return nextTile;
    }
}