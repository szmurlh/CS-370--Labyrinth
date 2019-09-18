package com.cs370.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

                  private Stage stage;
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
                    
                    Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
                    
                    img = new Texture("spritesheet_small.png"); 
                    
                    buttons = new ArrayList<Button>();
            
                    for(int i = 0; i < 3; i++) {
            
                        Button button = new TextButton("Push Tile", skin, "small");
                        button.setSize(100, 50);
                        button.setPosition((i * 132) + 274, 100);
                        button.setTransform(true);
                        button.setScale(0.5f);
                        buttons.add(button);
                    }
                    
                    Button button1 = buttons.get(0);
                    button1.addListener(new InputListener(){
                        @Override
                        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        
                            gameBoard.pushTileUp(1);
                        }
                        @Override
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                        }
                    });
                    
                    Button button2 = buttons.get(1);
                    button2.addListener(new InputListener(){
                        @Override
                        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        
                            gameBoard.pushTileUp(3);
                        }
                        @Override
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                        }
                    });
                    
                    Button button3 = buttons.get(2);
                    button3.addListener(new InputListener(){
                        @Override
                        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        
                            gameBoard.pushTileUp(5);
                        }
                        @Override
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                        }
                    });
                    

                    Button rotateBoardButton = new TextButton("Rotate Board", skin, "small");
                    rotateBoardButton.setSize(300, 100);
                    rotateBoardButton.setPosition(356, 25);
                    rotateBoardButton.setTransform(true);
                    rotateBoardButton.setScale(0.5f);
                    rotateBoardButton.addListener(new InputListener(){
                        @Override
                        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        
                            gameBoard.rotateBoard();
                        }
                        @Override
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                        }
                    });
                    
                    Button rotateNextTileButton = new TextButton("Rotate Tile", skin, "small");
                    rotateNextTileButton.setSize(200, 50);
                    rotateNextTileButton.setPosition(765, 190);
                    rotateNextTileButton.setTransform(true);
                    rotateNextTileButton.setScale(0.5f);
                    rotateNextTileButton.addListener(new InputListener(){
                        @Override
                        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        
                            gameBoard.getNextTile().rotateTileRight();
                        }
                        @Override
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                        }
                    });
                    
                    cam = new OrthographicCamera(1000, 640);
                    batch = new SpriteBatch();
                    ScreenViewport view = new ScreenViewport(cam);
                    stage = new Stage(view, batch);
                    stage.addActor(buttons.get(0));
                    stage.addActor(buttons.get(1));
                    stage.addActor(buttons.get(2));
                    stage.addActor(rotateBoardButton);
                    stage.addActor(rotateNextTileButton);
           
                    cam = new OrthographicCamera(1000, 640);

                    
                    gameBoard = new Board();

                    Gdx.input.setInputProcessor(stage);
                                       
	}

	@Override
	public void render () {
                
                    Gdx.gl.glClearColor(0, 0, 0, 0);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                                    
                    batch.setProjectionMatrix(cam.combined);
                    stage.act();
                    batch.begin();

                    
                        
                    for(int i = 0; i < 7; i++) {
                        for(int j = 0; j < 7; j++) {
  
                            Tile tile = gameBoard.getTile(i, j);
                                            
                            sprite = tile.getTileImage();
                            sprite.setBounds((66 * i) - 300, (66 * j) - 184, 64, 64);
                            sprite.draw(batch);
                            
                        }
                    }
                    
                    Tile nextTile = gameBoard.getNextTile();
                    sprite = nextTile.getTileImage();
                    sprite.setBounds(250, -100, 128, 128);
                    sprite.draw(batch);
                    
                    batch.end();
                    stage.draw();
                  } 
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}