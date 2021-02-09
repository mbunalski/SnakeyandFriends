import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GameFrame() {

		/*
		 * Below is same as--> GamePanel panel = new GamePanel(); this.add(panel);
		 */
		this.add(new GamePanel());
		this.setTitle("Snakey");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
