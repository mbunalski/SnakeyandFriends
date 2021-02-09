import javax.swing.JFrame;

public class TitleFrame extends JFrame {

	TitleFrame() {

		this.add(new TitlePanel());
		this.setTitle("Snakey");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}