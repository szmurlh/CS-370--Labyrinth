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
public class Tile {
    
    private String name;
    
    private boolean topPath;
    private boolean bottomPath;
    private boolean leftPath;
    private boolean rightPath;
    
    private boolean playerOnTile = false;
    private boolean fixedTile;
    private String item;
    
    private Sprite tileImage;
    
    public Tile() {
        
    }
    
    public Tile(boolean leftPath, boolean rightPath, boolean topPath, boolean bottomPath) {
        this.leftPath = leftPath;
        this.rightPath = rightPath;
        this.topPath = topPath;
        this.bottomPath = bottomPath;
    }
    
    public void rotateTileRight() {
        boolean leftPathCopy = leftPath;
        leftPath = bottomPath;
        bottomPath = rightPath;
        rightPath = topPath;
        topPath = leftPathCopy;
        
        if(tileImage != null) {
            tileImage.rotate90(true);
        }
    }
    
    public void rotateTileLeft() {
        boolean leftPathCopy = leftPath;
        leftPath = topPath;
        topPath = rightPath;
        rightPath = bottomPath;
        bottomPath = leftPathCopy;
        
        if(tileImage != null) {
            tileImage.rotate90(false);
        }
    }
    
    public boolean isTopPath() {
        return topPath;
    }

    public void setTopPath(boolean topPath) {
        this.topPath = topPath;
    }

    public boolean isBottomPath() {
        return bottomPath;
    }

    public void setBottomPath(boolean bottomPath) {
        this.bottomPath = bottomPath;
    }

    public boolean isLeftPath() {
        return leftPath;
    }

    public void setLeftPath(boolean leftPath) {
        this.leftPath = leftPath;
    }

    public boolean isRightPath() {
        return rightPath;
    }

    public void setRightPath(boolean rightPath) {
        this.rightPath = rightPath;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isFixedTile() {
        return fixedTile;
    }

    public void setFixedTile(boolean fixedTile) {
        this.fixedTile = fixedTile;
    }

    public Sprite getTileImage() {
        return tileImage;
    }

    public void setTileImage(Texture spriteSheet, int x, int y) {
        if(spriteSheet != null) {
            Sprite tileImage = new Sprite(spriteSheet, 126 * x , 126 * y, 126, 126);
            this.tileImage = tileImage;
        }
    }

    public boolean isPlayerOnTile() {
        return playerOnTile;
    }

    public void setPlayerOnTile(boolean playerOnTile) {
        this.playerOnTile = playerOnTile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
