package com.cs370.labyrinth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyGdxGame extends ApplicationAdapter {

    private Stage stage;
    private SpriteBatch batch;
    private Texture img;
    private Sprite sprite;
    private Camera cam;
    private Board gameBoard;
    private CardDeck cardDeck;
    private ArrayList<Button> buttons;
    private Player player;
    private Socket socket;
    private int playerCount;
    private HashMap<String, Player> allPlayers = new HashMap();
    private Sprite playerSprite;
    private String myPlayer;
	
    @Override
    public void create () {
        
        playerSprite = new Sprite(new Texture("player.png"));
        
        Gdx.graphics.setResizable(false);
        Gdx.graphics.setWindowedMode(1000, 640);

        Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        img = new Texture("spritesheet_small.png"); 
        buttons = new ArrayList<>();

        
        //Create three buttons for shifting tiles up
        for(int i = 0; i < 3; i++) {

            Button button = new TextButton("Push Tile", skin, "small");
            button.setSize(100, 50);
            button.setPosition((i * 132) + 173, 64);
            button.setTransform(true);
            button.setScale(0.5f);
            buttons.add(button);
        }

        Button button1 = buttons.get(0);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileUp(1);
                updateGameState();
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
                updateGameState();
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
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        
        //Create three buttons for shifting tiles down
        for(int i = 1; i < 4; i++) {

            Button button = new TextButton("Push Tile", skin, "small");
            button.setSize(100, 50);
            button.setPosition((i * 132) + 40, 572);
            button.setTransform(true);
            button.setScale(0.5f);
            buttons.add(button);
        } 


        Button button4 = buttons.get(3);
        button4.addListener(new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileDown(1);
                updateGameState();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button5 = buttons.get(4);
        button5.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileDown(3);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button6 = buttons.get(5);
        button6.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileDown(5);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        
        //Create three buttons for shifting tiles to the right
        for(int i = 1; i < 4; i++) {

            Button button = new TextButton("Push Tile", skin, "small");
            button.setSize(100, 50);
            button.setPosition(40, (i * 132) +52);
            button.setTransform(true);
            button.setScale(0.5f);
            buttons.add(button);
        } 


        Button button7 = buttons.get(6);
        button7.addListener(new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileRight(1);
                updateGameState();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button8 = buttons.get(7);
        button8.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileRight(3);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button9 = buttons.get(8);
        button9.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileRight(5);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        
        //Create three buttons for shifting tiles to the left
        for(int i = 1; i < 4; i++) {

            Button button = new TextButton("Push Tile", skin, "small");
            button.setSize(100, 50);
            button.setPosition(570, (i * 132) + 52);
            button.setTransform(true);
            button.setScale(0.5f);
            buttons.add(button);

        } 

        Button button10 = buttons.get(9);
        button10.addListener(new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileLeft(1);
                updateGameState();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button11 = buttons.get(10);
        button11.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileLeft(3);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Button button12 = buttons.get(11);
        button12.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.pushTileLeft(5);
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        
        //Create button for next tile rotation
        Button rotateNextTileButton = new TextButton("Rotate Tile", skin, "small");
        rotateNextTileButton.setSize(200, 50);
        rotateNextTileButton.setPosition(725, 150);
        rotateNextTileButton.setTransform(true);
        rotateNextTileButton.setScale(0.5f);
        rotateNextTileButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                gameBoard.getNextTile().rotateTileRight();
                updateGameState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        
        //Create batch and setup stage
        cam = new OrthographicCamera(1000, 640);
        batch = new SpriteBatch();
        ScreenViewport view = new ScreenViewport(cam);
        stage = new Stage(view, batch);
 
        
        //Add the buttons to the stage
        for(int i = 0; i < 12; i++) {
            stage.addActor(buttons.get(i));
        }
        
        //stage.addActor(rotateBoardButton);
        stage.addActor(rotateNextTileButton);
        cam = new OrthographicCamera(1000, 640);
        
        //Initialize the game board and card deck
        gameBoard = new Board("spritesheet_small.png");
        cardDeck = new CardDeck();
        
        //Initialize the player
        player = new Player(new Sprite(new Texture("player.png")));
        
        Gdx.input.setInputProcessor(stage);
        
        //gameBoard.printPaths();
        
        connectSocket();
        configureSocketEvents();
                                       
    }
    
    public void connectSocket() {
            try {
                socket = IO.socket("http://localhost:8080");
                socket.connect();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    
    public void configureSocketEvents() {
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Gdx.app.log("SocketIO", "Connected");
                }
            }).on("addPlayer", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    try {
                        JSONObject data = (JSONObject) os[0];
                        playerCount = data.getInt("playerCount");
                        
                        switch(playerCount){
                            case 0: 
                                
                                JSONObject jsonDataP1 = new JSONObject();
                                jsonDataP1.put("name", "player1");
                                jsonDataP1.put("turn", true);
                                myPlayer = "player1";
                                
                                socket.emit("joinSession", jsonDataP1);
                                break;
                                
                            case 1:
                                
                                JSONObject jsonDataP2 = new JSONObject();
                                jsonDataP2.put("name", "player2");
                                jsonDataP2.put("turn", false);
                                myPlayer = "player2";

                                socket.emit("joinSession", jsonDataP2);
                                break;
                                
                            case 2:
                                
                                break;
                                
                            case 3:
                                
                                break;
                        }
                        
                        Gdx.app.log("SocketIO", "My ID: " + playerCount);
                    } catch(JSONException e) {
                        Gdx.app.log("SocketIO", "Error getting ID");
                    }
                }
            }).on("currentPlayers", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    try {
                        JSONArray data = (JSONArray) os[0];
                        HashMap<String, Player> updatedPlayers = new HashMap();
                        
                        for(int i = 0; i < data.length(); i++) {
                            JSONObject player = data.getJSONObject(i);
                            String playerName = player.getString("name");
                            boolean playerTurn = player.getBoolean("turn");
                            String playerID = player.getString("id");
                            
                            updatedPlayers.put(playerID, new Player(playerSprite, playerName, playerTurn, playerID));
                        }
                        
                        allPlayers = updatedPlayers;
                        
                        for(int i = 0; i < allPlayers.size(); i++) {
                            switch(i) {
                                case 1:
                                    Tile blueTile = (Tile) gameBoard.getTiles().get("blueTile");
                                    blueTile.setPlayerOnTile("player1");
                                    
                                case 2:
                                    Tile greenTile = (Tile) gameBoard.getTiles().get("greenTile");
                                    greenTile.setPlayerOnTile("player2");
                                 
                                case 3:
                                    Tile redTile = (Tile) gameBoard.getTiles().get("redTile");
                                    redTile.setPlayerOnTile("player3");
                                    
                                case 4:
                                    Tile yellowTile = (Tile) gameBoard.getTiles().get("yellowTile");
                                    yellowTile.setPlayerOnTile("player4");
                            }
                        }
                        
                        if(myPlayer.equals("player1")) {   
                            
                            updateGameState();
                        }
                        System.out.println(allPlayers);
                    } catch(JSONException e) {
                        Gdx.app.log("SocketIO", "Error Getting New Player ID");
                    }
                }
            }).on("playerDisconnected", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    try {
                        JSONObject data = (JSONObject) os[0];
                        String id = data.getString("id");
                        allPlayers.remove(id);
                        System.out.println(allPlayers);
                    } catch(JSONException e) {
                        Gdx.app.log("SocketIO", "Error Getting New Player ID");
                    }
                }
            }).on("updatePlayerCount", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    try {
                        JSONObject data = (JSONObject) os[0];
                        int count = data.getInt("playerCount");
                        playerCount = count;
                    } catch(JSONException e) {
                        Gdx.app.log("SocketIO", "Error Getting New Player ID");
                    }
                }
            }).on("updateBoardState", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    try {
                        JSONArray gameDataArray = (JSONArray) os[0];
                        JSONArray boardDataArray = gameDataArray.getJSONArray(0);
                        JSONObject nextTile = gameDataArray.getJSONObject(1);
                        System.out.println("Update Board: " + nextTile);
                        
                        for(int index = 0; index < boardDataArray.length(); index++) {
                            JSONObject data = (JSONObject) boardDataArray.get(index);
                            Tile tile = (Tile) gameBoard.getTiles().get(data.getString("name"));
                            
                            tile.updateSpriteRotation(data.getInt("rotation"));
                            tile.setPlayerOnTile(data.getString("playerOnTile"));
                            
                            gameBoard.updateTileLocations(data.getInt("col"), data.getInt("row"), tile);
                        }     
                        
                        gameBoard.setNextTile(nextTile.getString("name"), nextTile.getInt("rotation"), nextTile.getString("playerOnTile"));
                        
                    } catch(JSONException e) {
                        Gdx.app.log("SocketIO", "Error Updating Board State");
                    }
                }
            }).on("updatePlayerPosition", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
//                    try {
                        
//                    } catch(JSONException e) {
//                        Gdx.app.log("SocketIO", "Error Updating Board State");
//                    }
                }
            });
        }
    
    public void updateGameState() {
        
        try{
            JSONArray gameData = new JSONArray();
            JSONArray boardData = new JSONArray();
            JSONObject nextTile = new JSONObject();
            
            for(int i = 0; i < 7; i++) {
                for(int j = 0; j < 7; j++) {
                  
                    Tile tile = gameBoard.getTile(i, j);
                    
                    JSONObject tileData = new JSONObject();
                    tileData.put("row", i );
                    tileData.put("col", j);
                    tileData.put("name", tile.getName());
                    tileData.put("playerOnTile", tile.isPlayerOnTile());
                    tileData.put("rotation", tile.getRotation());
                    
                    boardData.put(tileData);
                }
            }
            nextTile.put("name", gameBoard.getNextTile().getName());
            nextTile.put("rotation", gameBoard.getNextTile().getRotation());
            nextTile.put("playerOnTile", gameBoard.getNextTile().isPlayerOnTile());
            
            gameData.put(boardData);
            gameData.put(nextTile);
            
            socket.emit("updateBoardState", gameData);
            
        } catch(JSONException e) {
            Gdx.app.log("SocketIO", "Error Getting Updating the Game State");
        }
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
                sprite.setBounds((66 * i) - 400, (66 * j) - 220, 64, 64);
                sprite.draw(batch);

            }
        }

        Tile nextTile = gameBoard.getNextTile();
        sprite = nextTile.getTileImage();
        sprite.setBounds(210, -140, 128, 128);
        sprite.draw(batch);
        
        Card nextCard = cardDeck.getCardDeck().peek();
        sprite = nextCard.getCardImage();
        sprite.setBounds(210, 60, 128, 200);
        sprite.draw(batch);
        
        //Discard current card and displays the next card when a player lands on
        //  a tile that has the matching item 
//        if(gameBoard.getPlayerLocation().getItem() != null && 
//                gameBoard.getPlayerLocation().getItem().equals(nextCard.getCardItem())) {
//            cardDeck.getCardDeck().pop();
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
//            gameBoard.canPlayerMoveLeft(gameBoard.getPlayerLocation(), true);
//		
//        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
//            gameBoard.canPlayerMoveRight(gameBoard.getPlayerLocation(), true);
//		
//        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
//            gameBoard.canPlayerMoveUp(gameBoard.getPlayerLocation(), true);
//		
//        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
//            gameBoard.canPlayerMoveDown(gameBoard.getPlayerLocation(), true);
//        
//        Sprite currentTileImage = gameBoard.getPlayerLocation().getTileImage();
        
//        player.setBounds(currentTileImage.getX() + 21, currentTileImage.getY() + 25, 20, 20);

        player.setBounds(player.getX(),player.getY(), 20, 20);
        
        player.draw(batch);
        
        batch.end();
        stage.draw();
    } 
	
    @Override
    public void dispose () {
        batch.dispose();
	img.dispose();
        player.getTexture().dispose();
    }
}