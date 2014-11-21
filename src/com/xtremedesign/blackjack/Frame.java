package com.xtremedesign.blackjack;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

	public Frame() {
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ultimate Blackjack");
		setVisible(true);
		Screen screen = new Screen();
		screen.setLayout(new GridBagLayout());
		add(screen);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
	}

}
