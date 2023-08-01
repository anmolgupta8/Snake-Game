import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		obj.setBackground(Color.DARK_GRAY);
		gameplay.setBackground(Color.DARK_GRAY);
		obj.setBounds(10, 10, 905, 700);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
		obj.setVisible(true);
	}

}
