package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import Main.GamePanel;
import TileMap.Background;

@SuppressWarnings("serial")
public class HighScore extends GameState {
	private Background bg;

	int currentChoice = 0;
	private String[] options = { "Menu", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Font font;

	public HighScore(GameStateManager gsm) {

		this.gsm = gsm;

		try {
			bg = new Background("/Backgrounds/star.jpg", 1);
			bg.setVector(-.1, 0);

			titleColor = new Color(0, 128, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 48);

			font = new Font("Arial", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		bg.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw((Graphics2D) g);
	}

	@Override
	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("High Scores", (GamePanel.WIDTH - metrics.stringWidth("High Scores")) / 2, g.getFont().getSize());

		try {
			File myFile = new File("C:\\Users\\Bun\\Desktop\\College\\HighScore.txt");
			Scanner inputFile = new Scanner(myFile);
			File myFile2 = new File("C:\\Users\\Bun\\Desktop\\College\\HighScoreNames.txt");
			Scanner inputFile2 = new Scanner(myFile2);
			g.setFont(font);
			metrics = getFontMetrics(g.getFont());
			for (int i = 0; i < 20; i++) {
				if (inputFile.hasNext()) {
					g.drawString(inputFile2.nextLine() + ": " + inputFile.nextLine(),
							(GamePanel.WIDTH - metrics.stringWidth("High Scores")) / 2,
							120 + g.getFont().getSize() * i);
				} else {
					break;
				}
			}

			inputFile.close();
			inputFile2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.MAGENTA);
			}
			g.drawString(options[i], (GamePanel.WIDTH - metrics.stringWidth(options[i])) / 2, 380 + i * 25);
		}

	}

	public void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if (currentChoice == 1) {
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
