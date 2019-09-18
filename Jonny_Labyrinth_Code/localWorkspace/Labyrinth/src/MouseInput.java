import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	Game game;
	
	public MouseInput(Game game) {
		this.game = game;
	}
	
	public void mouseClicked(MouseEvent e) {
		game.mouseClicked(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		game.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		game.mouseExited(e);
	}
	
	public void mousePressed(MouseEvent e) {
		game.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		game.mouseReleased(e);
	}
	
}
