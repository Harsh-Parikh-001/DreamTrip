package com.DreamTrip.UI;

import javax.swing.*;

public class HomePage extends JFrame {
	JFrame jFrame = new JFrame();
	JPanel jPanel = new JPanel();
	JLabel jLabel = new JLabel();

	HomePage() {
		jLabel.setText("Hey");
		jLabel.setBounds(20,20,200,20);
		jPanel.add(jLabel);
		jFrame.add(jPanel);
		jFrame.setSize(600,500);
		jFrame.setVisible(true);
	}
}
