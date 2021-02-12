package GameState;

import javax.swing.JPanel;

public abstract class GameState extends JPanel {

	protected GameStateManager gsm;

	public abstract void init();

	public abstract void update();

	public abstract void draw(java.awt.Graphics2D g);

	public abstract void keyPressed(int k);

	public abstract void keyReleased(int k);

}
