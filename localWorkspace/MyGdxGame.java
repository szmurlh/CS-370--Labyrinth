package com.cs370.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

                
                  private SpriteBatch batch;
                  private Texture img;
                  private Sprite sprite;
                  private Camera cam;
                  private Board gameBoard;
                  private ArrayList<Button> buttons;
	
	@Override
	public void create () {
                    Gdx.graphics.setResizable(false);
                    Gdx.graphics.setWindowedMode(1000, 640);
                    
                   
                    cam = new OrthographicCamera(1000, 640);
                    batch = new SpriteBatch();
                    ScreenViewport view = new ScreenViewport(cam);
                  
                    
           
                    cam = new OrthographicCamera(1000, 640);

                    
                    gameBoard = new Board();

                    
                                       ;
	}
        
        

	@Override
	public void render () {
                
                    Gdx.gl.glClearColor(0, 0, 0, 0);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                    
                    batch.setProjectionMatrix(cam.combined);
                    
                    batch.begin();

                    
                        
                    for(int i = 0; i < 7; i++) {
                        for(int j = 0; j < 7; j++) {
  
                            Tile tile = gameBoard.getTile(i, j);
                                            
                            sprite = tile.getTileImage();
                            sprite.setBounds((66 * i) - 300, (66 * j) - 184, 64, 64);
                            sprite.draw(batch);
                            
                        }
                    }
                    
                   
                    
                    batch.end();
                  
                  } 
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
