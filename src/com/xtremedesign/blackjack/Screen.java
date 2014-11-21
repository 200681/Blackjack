package com.xtremedesign.blackjack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
	Thread th = new Thread(this);
	static List<Card> cards;
	public List<String> possiblecards = new ArrayList<String>();
	public Screen() {
		addCards();
		th.start();
	}
	
	public void addCards() {
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 500, 500);
		repaint();
	}
	
	public void run() {
		while(true) {
			
		}
	}

}
