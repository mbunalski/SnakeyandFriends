package GameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Main.GamePanel;

@SuppressWarnings("serial")
public class Snake extends GameState implements ActionListener {

	static final int UNIT_SIZE = 10;
	static final int GAME_UNITS = (GamePanel.WIDTH * GamePanel.HEIGHT) / UNIT_SIZE;
	static final int DELAY = 125;
	final int x[] = new int[GAME_UNITS]; // hold coordinates of body parts
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	static int applesEaten = 0;
	public static int finalScore;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;

	public Snake(GameStateManager gsm) {
		this.gsm = gsm;
		random = new Random();

		this.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);

		init();
	}

	@Override
	public void init() {
		direction = 'R';
		appleX = 0;
		appleY = 0;
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();

	}

	@Override
	public void update() {
		/*
		 * direction = 'R'; appleX = 0; appleY = 0; newApple(); running = true; timer =
		 * new Timer(DELAY, this); timer.start();
		 */

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw((Graphics2D) g);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		if (running) {
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(Color.cyan);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				g.setColor(Color.red);
				g.setFont(new Font("Ink Free", Font.BOLD, 15));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Score: " + applesEaten,
						(GamePanel.WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

			}

		} else {
			finalScore = applesEaten;
			applesEaten = 0;
			for (int i = 0; i < bodyParts; i++) {
				x[i] = 0;
				y[i] = 0;
			}
			bodyParts = 6;
			try {
				File myFile = new File("C:\\Users\\Bun\\Desktop\\College\\HighScore.txt");
				Scanner inputFile = new Scanner(myFile);
				File myFile2 = new File("C:\\Users\\Bun\\Desktop\\College\\HighScoreNames.txt");
				Scanner inputFile2 = new Scanner(myFile2);
				int[] highscores = new int[10];
				String[] highScoreNames = new String[10];
				for (int i = 0; i < 10; i++) {
					if (inputFile.hasNext()) {
						highscores[i] = inputFile.nextInt();
						highScoreNames[i] = inputFile2.nextLine();
					} else {
						break;
					}
				}

				inputFile.close();
				inputFile2.close();
				String newHighScore = null;
				for (int i = 0; i < highscores.length; i++) {
					System.out.println(highscores[i]);
				}
				if (finalScore > highscores[9]) {
					newHighScore = JOptionPane.showInputDialog("Enter your name");
					PrintWriter outputFile = new PrintWriter("C:\\Users\\Bun\\Desktop\\College\\HighScore.txt");
					PrintWriter outputFile2 = new PrintWriter("C:\\Users\\Bun\\Desktop\\College\\HighScoreNames.txt");
					int j = 0;

					for (int i = 0; i <= highscores.length; i++) {
						if (highscores[i] < finalScore) {
							outputFile.println(finalScore);
							outputFile2.println(newHighScore);
							j = i;
							break;
						} else {
							outputFile.println(highscores[i]);
							outputFile2.println(highScoreNames[i]);

						}

					}
					for (int i = j; i < 9; i++) {
						outputFile.println(highscores[i]);
						outputFile2.println(highScoreNames[i]);

					}
					outputFile.close();
					outputFile2.close();
				}
			} catch (Exception e) {

			}

			gsm.setState(GameStateManager.SNAKEGAMEOVER);
		}

	}

	public void checkCollisions() {
		// checks if head collides with body
		for (int i = 1; i < bodyParts; i++) {
			if (x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		// checks if head touches left border
		if (x[0] < 0) {
			running = false;
		}
		// checks if head touches right border
		if (x[0] > GamePanel.WIDTH) {
			running = false;
		}
		// checks if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// checks if head touches bottom border
		if (y[0] > GamePanel.HEIGHT) {
			running = false;
		}
		if (!running) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {

		gsm.setState(GameStateManager.SNAKEGAMEOVER);

	}

	public void newApple() {

		appleX = random.nextInt(GamePanel.WIDTH / UNIT_SIZE) * UNIT_SIZE;
		appleY = random.nextInt(GamePanel.HEIGHT / UNIT_SIZE) * UNIT_SIZE;

	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;

		}
	}

	public void checkApple() {
		if (appleX == x[0] && appleY == y[0]) {

			bodyParts = bodyParts + 3;
			applesEaten++;
			newApple();

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (running) {
			move();
			checkApple();
			checkCollisions();

		}
		repaint();
	}

	@Override
	public void keyPressed(int k) {

		switch (k) {
		case KeyEvent.VK_LEFT:
			if (direction != 'R') {
				direction = 'L';
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (direction != 'L') {
				direction = 'R';
			}
			break;

		case KeyEvent.VK_UP:
			if (direction != 'D') {
				direction = 'U';
			}
			break;

		case KeyEvent.VK_DOWN:
			if (direction != 'U') {
				direction = 'D';
			}
			break;

		}

	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
