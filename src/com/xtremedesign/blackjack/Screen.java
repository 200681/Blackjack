package com.xtremedesign.blackjack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
	Thread th = new Thread(this);
	static List<Card> cards;
	boolean addcard = false;
	public List<String> possiblecards = new ArrayList<String>();
	int x = 100;
	int y = 100;
	int value = 0;
	public Screen() {
		addCard();
		addCard();
		th.start();
	}
	
	public void updateCards() {
		possiblecards.clear();
		possiblecards.add("c1");
		possiblecards.add("c2");
		possiblecards.add("c3");
		possiblecards.add("c4");
		possiblecards.add("c5");
		possiblecards.add("c6");
		possiblecards.add("c7");
		possiblecards.add("c8");
		possiblecards.add("c9");
		possiblecards.add("c10");
		possiblecards.add("cj");
		possiblecards.add("cq");
		possiblecards.add("ck");
		possiblecards.add("s1");
		possiblecards.add("s2");
		possiblecards.add("s3");
		possiblecards.add("s4");
		possiblecards.add("s5");
		possiblecards.add("s6");
		possiblecards.add("s7");
		possiblecards.add("s8");
		possiblecards.add("s9");
		possiblecards.add("s10");
		possiblecards.add("sj");
		possiblecards.add("sq");
		possiblecards.add("sk");
		possiblecards.add("h1");
		possiblecards.add("h2");
		possiblecards.add("h3");
		possiblecards.add("h4");
		possiblecards.add("h5");
		possiblecards.add("h6");
		possiblecards.add("h7");
		possiblecards.add("h8");
		possiblecards.add("h9");
		possiblecards.add("h10");
		possiblecards.add("hj");
		possiblecards.add("hq");
		possiblecards.add("hk");
		possiblecards.add("d1");
		possiblecards.add("d2");
		possiblecards.add("d3");
		possiblecards.add("d4");
		possiblecards.add("d5");
		possiblecards.add("d6");
		possiblecards.add("d7");
		possiblecards.add("d8");
		possiblecards.add("d9");
		possiblecards.add("d10");
		possiblecards.add("dj");
		possiblecards.add("dq");
		possiblecards.add("dk");
	}
	
	public synchronized void addCard() {
		Card card = new Card(x, y)
		
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
