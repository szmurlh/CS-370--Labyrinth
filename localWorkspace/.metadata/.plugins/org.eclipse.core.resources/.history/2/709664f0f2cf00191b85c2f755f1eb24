import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	// Basic variables for the frame.
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH * 9 / 12;
	public static final int SCALE = 2;
	public final String TITLE = "CS 370 - Labyrinth";
	
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage tileSS = null;
	
	private Board board;
	
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			tileSS = loader.loadImage("res\\spritesheet_small.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		
		// A little math to place the board in the center of the screen.
		board = new Board(3*(SCALE * WIDTH - (126*(Board.SIZE+2)))/7, (SCALE * HEIGHT - (126*Board.SIZE))/2, this);
		
	}
	
	// This function will start the game loop.
	// This should only be called once, and if it
	// is ever accidentally called again, it will return.
	private synchronized void start() {
		if (running) {
			return;
		} else {
			running = true;
			init();
			run();
		}
	}
	
	// This is one of the last functions to be called. When this
	// is called, the game will terminate and the game loop will
	// end. We join the threads to make sure everything runs smooth.
	private synchronized void stop() {
		if (!running) {
			return;
		} else {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
	
	public void run() {
		
		// These variables are to be able to count ticks
		// and frames per second. This is important to
		// ensure the game runs at the same speed every time.
		long lastTime = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		// ================================= //
		// THIS IS THE GAME LOOP.            //
		// ALL GAME LOGIC SHOULD HAPPEN HERE //
		// ================================= //
		while (running) {
			// Tick and FPS logic.
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
			// End tick and FPS logic.
			
			
		}
		stop();
	}
	
	private void tick() {
		board.tick();
	}
	
	private void render() {
		
		// This block of code will create a Buffer on the screen
		// if one has not already been created. Otherwise, it will
		// get the BufferStrategy from the Canvas.
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		board.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	// These two functions handle all keyboard input.
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		// If 'r' is pressed, rotate the spare tile.
		if (key == KeyEvent.VK_R) {
			board.rotateSpare();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// This block of if statements will take the x,y position
		// of the mouse on the screen and check to see which of
		// the 12 triangle buttons is pressed.
		if (mouseY < 3*board.vd) {
			if (mouseX > 3*board.hd + 4*board.td && mouseX < 3*board.hd + 8*board.td) { 
				System.out.println("Button 1 pressed");
			} else if (mouseX > 3*board.hd + 12*board.td && mouseX < 3*board.hd + 16*board.td) {
				System.out.println("Button 2 pressed");
			} else if (mouseX > 3*board.hd + 20*board.td && mouseX < 3*board.hd + 24*board.td) {
				System.out.println("Button 3 pressed");
			}
		} else if (mouseY > 3*board.vd + 28*board.td) {
			if (mouseX > 3*board.hd + 4*board.td && mouseX < 3*board.hd + 8*board.td) { 
				System.out.println("Button 4 pressed");
			} else if (mouseX > 3*board.hd + 12*board.td && mouseX < 3*board.hd + 16*board.td) {
				System.out.println("Button 5 pressed");
			} else if (mouseX > 3*board.hd + 20*board.td && mouseX < 3*board.hd + 24*board.td) {
				System.out.println("Button 6 pressed");
			}
		} else if (mouseX < 3*board.hd) {
			if (mouseY > 3*board.vd + 4*board.td && mouseY < 3*board.vd + 8*board.td) {
				System.out.println("Button 7 pressed");
			} else if (mouseY > 3*board.vd + 12*board.td && mouseY < 3*board.vd + 16*board.td) {
				System.out.println("Button 8 pressed");
			} else if (mouseY > 3*board.vd + 20*board.td && mouseY < 3*board.vd + 24*board.td) {
				System.out.println("Button 9 pressed");
			}
		} else if (mouseX > 3*board.hd + 28*board.td && mouseX < 6*board.hd + 28*board.td) {
			if (mouseY > 3*board.vd + 4*board.td && mouseY < 3*board.vd + 8*board.td) {
				System.out.println("Button 10 pressed");
			} else if (mouseY > 3*board.vd + 12*board.td && mouseY < 3*board.vd + 16*board.td) {
				System.out.println("Button 11 pressed");
			} else if (mouseY > 3*board.vd + 20*board.td && mouseY < 3*board.vd + 24*board.td) {
				System.out.println("Button 12 pressed");
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		// These next few lines will take care of making the JFrame
		// the correct size, making it not resizable, making it
		// visible, etc.. It will then call start to begin game logic.
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return this.tileSS;
	}
	
}
