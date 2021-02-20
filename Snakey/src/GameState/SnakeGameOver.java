package GameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;

@SuppressWarnings("serial")
public class SnakeGameOver extends GameState {

	int currentChoice = 0;
	private String[] options = { "Restart", "Menu", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Font font;

	public SnakeGameOver(GameStateManager gsm) {
		this.gsm = gsm;
		this.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);

		titleColor = new Color(0, 128, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 28);
		font = new Font("Arial", Font.PLAIN, 12);

		init();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw((Graphics2D) g);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(titleColor);
		g.setFont(titleFont);

		// Game Over
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (GamePanel.WIDTH - metrics.stringWidth("Game Over")) / 2, GamePanel.HEIGHT / 2);

		// Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 13));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + Snake.finalScore,
				(GamePanel.WIDTH - metrics2.stringWidth("Score: " + Snake.finalScore)) / 2, g.getFont().getSize());

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.MAGENTA);
			}
			FontMetrics metrics3 = getFontMetrics(g.getFont());
			g.drawString(options[i], (GamePanel.WIDTH - metrics3.stringWidth(options[i])) / 2, 380 + i * 25);
		}

		// gameOver(g);
	}

	public void gameOver(Graphics g) {
		// Game over Text

		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (GamePanel.WIDTH - metrics1.stringWidth("Game Over")) / 2, GamePanel.HEIGHT / 2);

		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 13));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + Snake.finalScore,
				(GamePanel.WIDTH - metrics2.stringWidth("Score: " + Snake.finalScore)) / 2, g.getFont().getSize());
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.SNAKE);

		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
