import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TitlePanel extends JPanel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;

	TitlePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.gray);
		this.setFocusable(true);
		JButton playButton = new JButton("Play");
		playButton.setBounds(500, 500, SCREEN_HEIGHT / 5, SCREEN_HEIGHT / 4);

		playButton.addActionListener(this);
		this.add(playButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new GameFrame();

	}

}