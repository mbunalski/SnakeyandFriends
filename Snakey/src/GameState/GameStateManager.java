package GameState;

import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int SNAKE = 1;
	public static final int SNAKEGAMEOVER = 2;
	public static final int HIGHSCORE = 3;

	public GameStateManager() {

		gameStates = new ArrayList<GameState>();

		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Snake(this));
		gameStates.add(new SnakeGameOver(this));
		gameStates.add(new HighScore(this));

	}

	public GameStateManager(int state) {

		gameStates = new ArrayList<GameState>();

		currentState = state;
		gameStates.add(new MenuState(this));
		gameStates.add(new Snake(this));
		gameStates.add(new SnakeGameOver(this));
		gameStates.add(new HighScore(this));

	}

	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

}
